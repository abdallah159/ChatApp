package com.example.abdallahmohammed.chatapplication;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdallahmohammed.chatapplication.model.User;

import java.util.ArrayList;

/**
 * Created by Abdallah Mohammed on 12/13/2017.
 */

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {
    Activity activity ;
    ArrayList<User>users ;

    public UsersAdapter(Activity activity, ArrayList<User> users) {
        this.activity = activity;
        this.users = users;
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder {
        TextView userName , userId ;
        public UsersViewHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.user_name_TextView);
            userId = itemView.findViewById(R.id.user_id_TextView);
        }
    }
    @Override
    public UsersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.chat_user,parent,false);
        return new UsersViewHolder(view);
    }

    @Override
    public void onBindViewHolder(UsersViewHolder holder, int position) {
        holder.userName.setText(users.get(position).getName());
        holder.userId.setText(users.get(position).getId());

    }

    @Override
    public int getItemCount() {
        return users==null ? 0 :users.size();
    }

}
