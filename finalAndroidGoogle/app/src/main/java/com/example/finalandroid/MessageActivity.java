package com.example.finalandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MessageActivity extends AppCompatActivity {

    private List<Message> messageList;

    private RecyclerView mRecyclerView;
    private MessageAdapter messageAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private Button sendButton;
    private EditText messageEditText;
    private TextView usernameTextView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.messages_layout);

        messageList = new ArrayList<>();

        buildRecyclerView();

        sendButton = findViewById(R.id.send);
        messageEditText = findViewById(R.id.insertedMessage);
        usernameTextView = findViewById(R.id.username);
        final String username = usernameTextView.getText().toString();

        CountDownTimer progressTimer = new CountDownTimer(1200000, 3000) {
            @Override
            public void onTick(long millisUntilFinished) {
                insertItem(null, null);
            }

            @Override
            public void onFinish() {

            }
        }.start();


        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String insertedMessageText = messageEditText.getText().toString();
                messageEditText.setText("");
                insertItem(username, insertedMessageText);
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.design_meniu, menu);
        return true;
    }


    public void insertItem(String username, String insertedMessageText) {

        Message message;
        if(username==null && insertedMessageText==null){
            message = new Message();
        }else{
            message = new Message(username, insertedMessageText);
        }

        messageList.add(0, message);
        messageAdapter.updateList();
    }

    public void changeItem(int position) {
        messageAdapter.getMessageList().get(position).changeFavorite();
        messageAdapter.notifyItemChanged(position);
    }


    public void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(this);
        messageAdapter = new MessageAdapter(this, messageList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(messageAdapter);

        messageAdapter.setOnItemClickListener(new MessageAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                changeItem(position);
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.design1) {
            messageAdapter.applyDesign(MessageAdapter.DESIGN_1);
            return true;
        } else if (id == R.id.design2) {
            messageAdapter.applyDesign(MessageAdapter.DESIGN_2);
            return true;
        } else if (id == R.id.bothDesigns) {
            messageAdapter.applyDesign(MessageAdapter.DESIGN_3);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
