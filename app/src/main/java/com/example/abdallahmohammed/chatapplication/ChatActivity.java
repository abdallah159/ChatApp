package com.example.abdallahmohammed.chatapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.abdallahmohammed.chatapplication.model.MessagesList;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class ChatActivity extends AppCompatActivity {

    private String userID, userName;
    EditText messageEditText;
    ImageButton sendMessageButton;
    HashMap<String,String> msgMap ;
    ArrayList<MessagesList> messagesLists ;
    RecyclerView messagesRecyclerView ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Bundle bundle = getIntent().getExtras();
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference("messages");
        messagesLists = new ArrayList<>();
//        Long d = new Date().getTime();

        messagesRecyclerView = findViewById(R.id.messages_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        messagesRecyclerView.setLayoutManager(linearLayoutManager);


        userID = bundle.getString("id");
        userName = bundle.getString("name");
//        Toast.makeText(this, "" + userID + userName, Toast.LENGTH_SHORT).show();

        messageEditText = findViewById(R.id.message_EditText);
        sendMessageButton = findViewById(R.id.send_msg_Button);
        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child(userName + "-" + userID).child(Calendar.getInstance().getTime().toString()).setValue(messageEditText.getText().toString());
                messagesLists.clear();
                messageEditText.setText("");

            }
        });

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                messagesLists.clear();
                if (dataSnapshot.exists()) {
                    Log.v("+++++", dataSnapshot.getValue().toString());

                    for (DataSnapshot msg : dataSnapshot.child(userName + "-" + userID).getChildren()) {

                        MessagesList messagesList = new MessagesList(msg.getValue().toString(), msg.getKey().toString());

//                    Toast.makeText(ChatActivity.this, ""(),""+messagesList.getDate().toString(),Toast.LENGTH_SHORT).show();
                        messagesLists.add(messagesList);
                    }

                    MessagesAdapter messagesAdapter = new MessagesAdapter(ChatActivity.this, messagesLists);
                    messagesRecyclerView.setAdapter(messagesAdapter);

//                Toast.makeText(ChatActivity.this, ""+messagesLists.size(), Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
}
