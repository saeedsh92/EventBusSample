package com.ss.eventbussample;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Calendar;

public class ChatActivity extends BaseActivity {
    private RecyclerView messageRv;
    private MessageAdapter messageAdapter;
    private EditText messageEt;
    private FloatingActionButton sendButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setupViews();
    }

    private void setupViews() {
        Toolbar toolbar= (Toolbar) findViewById(R.id.toolbar_chat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        messageRv= (RecyclerView) findViewById(R.id.rv_chat);
        messageRv.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        messageAdapter=new MessageAdapter(this);
        messageRv.setAdapter(messageAdapter);
        messageEt= (EditText) findViewById(R.id.et_chat_inputMessage);
        sendButton= (FloatingActionButton) findViewById(R.id.fab_chat_sendMessage);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageEt.length()>0){
                    Message message=new Message();
                    message.setDate(Calendar.getInstance().getTime().toString());
                    message.setSendByCurrentUser(true);
                    message.setContent(messageEt.getText().toString());
                    sendMessage(message);
                    messageEt.setText(null);
                }else {
                    messageEt.setError("Write your message first");
                }
            }
        });
    }

    private void sendMessage(Message message) {
        messageAdapter.addMessage(message);
        messageRv.smoothScrollToPosition(messageAdapter.getItemCount()-1);
    }
}
