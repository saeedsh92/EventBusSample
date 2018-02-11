package com.ss.eventbussample;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
        messageEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if (i == EditorInfo.IME_ACTION_SEND) {
                    sendButton.performClick();
                    return true;
                }
                return false;
            }
        });
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

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true, priority = 1)
    public void onMessageEvent(Message message) {
        sendMessage(message);
        EventBus.getDefault().removeStickyEvent(message);
    }
}
