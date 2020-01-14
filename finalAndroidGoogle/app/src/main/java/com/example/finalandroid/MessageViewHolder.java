package com.example.finalandroid;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MessageViewHolder extends  RecyclerView.ViewHolder {

    private TextView usernameTextView;
    private TextView messageTextView;
    private TextView currentTimeTextView;
    private ImageView starImageView;


    public MessageViewHolder(View itemView, MessageAdapter.OnItemClickListener listener) {
        super(itemView);
        usernameTextView = itemView.findViewById(R.id.username);
        messageTextView = itemView.findViewById(R.id.message);
        currentTimeTextView = itemView.findViewById(R.id.time);
        starImageView  = itemView.findViewById(R.id.star);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            }
        });
    }

    public MessageViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void bindViewHolder(Message message) {
        usernameTextView.setText(message.getUsername());
        messageTextView.setText(message.getMessage());
        currentTimeTextView.setText(message.getCurrentTime());
        if(message.isFavorite()){
            starImageView.setVisibility(View.VISIBLE);
        }else{
            starImageView.setVisibility(View.INVISIBLE);
        }

    }


}
