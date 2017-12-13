package com.example.abdallahmohammed.chatapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.abdallahmohammed.chatapplication.model.LoginData;
import com.example.abdallahmohammed.chatapplication.model.LoginResult;
import com.example.abdallahmohammed.chatapplication.rest.ApiInterfaceLogin;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText userEmailEditText, userPasswordEditText;
    Button loginButton;
    String userEmail, userPassword;
    ProgressDialog progressDialog;
    //    LoginData userLoginData ;
    public static final String BASE_URL = "http://foodservice.4rera.com/api/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userEmailEditText = findViewById(R.id.user_email_EditText);
        userPasswordEditText = findViewById(R.id.user_password_EditText);
        loginButton = findViewById(R.id.login_Button);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading..");

        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create()).build();

        final ApiInterfaceLogin service = retrofit.create(ApiInterfaceLogin.class);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                if (v.getId() == R.id.login_Button) {
                    userEmail = userEmailEditText.getText().toString();
                    userPassword = userPasswordEditText.getText().toString();
                    retrofit2.Call<LoginResult> call = service.getStatus(new LoginData(userEmail, userPassword, ""));
                    call.enqueue(new Callback<LoginResult>() {
                        @Override
                        public void onResponse(retrofit2.Call<LoginResult> call, Response<LoginResult> response) {
                            if (response.isSuccessful()) {
                                if (response.body().getSuccess() != null) {
                                    if(response.body().getSuccess().equals("true")) {
                                        //chatStart(response.body().getUser().getId(),response.body().getUser().getName());
                                        //Send User data to firebase (for all users who will login)
                                        sendUserToFireBase(response.body().getUser().getName(), response.body().getUser().getId());
                                        chatList(response.body().getUser().getId(), response.body().getUser().getName());
                                        progressDialog.dismiss();
                                    }else {
                                        Toast.makeText(MainActivity.this, "Your Account Not Registered", Toast.LENGTH_LONG).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(retrofit2.Call<LoginResult> call, Throwable t) {
                            Log.v("error", t.getMessage().toString());

                        }
                    });


                }
            }
        });
    }

    private void sendUserToFireBase(String usrName , String usrId) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference("users");
        databaseReference.child(usrName).setValue(usrId);
    }

    private void chatStart(String id, String name) {
        Intent intent = new Intent(MainActivity.this, ChatActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        startActivity(intent);
    }

    private void chatList(String id , String name){
        Intent intent = new Intent(MainActivity.this, ChatList.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        startActivity(intent);

    }
}
