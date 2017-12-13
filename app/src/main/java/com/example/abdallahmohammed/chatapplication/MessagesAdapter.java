package com.example.abdallahmohammed.chatapplication;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.abdallahmohammed.chatapplication.model.MessagesList;

import java.util.ArrayList;

/**
 * Created by Abdallah Mohammed on 12/12/2017.
 */

public class MessagesAdapter extends RecyclerView.Adapter<MessagesAdapter.MessagesViewHolder> {
    Activity activity ;
    ArrayList<MessagesList> messagesList ;

    public MessagesAdapter(Activity activity, ArrayList<MessagesList> messagesList) {
        this.activity = activity;
        this.messagesList = messagesList;
    }

    @Override
    public MessagesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.message_card_send,parent,false);
        return new MessagesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MessagesViewHolder holder, int position) {
        holder.messageTextView.setText(messagesList.get(position).getMessage());

    }

    @Override
    public int getItemCount() {
        return messagesList==null ? 0 :messagesList.size();
    }

    public class MessagesViewHolder extends RecyclerView.ViewHolder{

        TextView messageTextView ;
        public MessagesViewHolder(View itemView) {
            super(itemView);
            messageTextView = itemView.findViewById(R.id.msg_TextView);

        }
    }
}
