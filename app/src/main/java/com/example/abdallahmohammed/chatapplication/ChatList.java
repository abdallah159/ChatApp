package com.example.abdallahmohammed.chatapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.abdallahmohammed.chatapplication.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatList extends AppCompatActivity {
    ArrayList<User> users ;
    User user ;
    RecyclerView usersRecyclerView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_list);

        usersRecyclerView = findViewById(R.id.users_RecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        usersRecyclerView.setLayoutManager(linearLayoutManager);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference("users");
        users = new ArrayList<>();

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot userData : dataSnapshot.getChildren()){
                    user = new User(userData.getValue().toString(),userData.getKey().toString(),"","");
                    users.add(user);
                }

                usersRecyclerView.setAdapter(new UsersAdapter(ChatList.this,users));


//                Toast.makeText(ChatList.this, ""+users.get(0).getName()+users.get(0).getId(), Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        usersRecyclerView.addOnItemTouchListener(new RecyclerViewItemClickListener(getApplicationContext(), new RecyclerViewItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                TextView uName = view.findViewById(R.id.user_name_TextView);
                TextView uID = view.findViewById(R.id.user_id_TextView);
                chatStart(uID.getText().toString(),uName.getText().toString());

            }
        }));
    }

    private void chatStart(String id, String name) {
        Intent intent = new Intent(ChatList.this, ChatActivity.class);
        intent.putExtra("id",id);
        intent.putExtra("name",name);
        startActivity(intent);
    }

}
