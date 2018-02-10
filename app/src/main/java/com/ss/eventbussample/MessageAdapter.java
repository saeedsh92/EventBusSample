package com.ss.eventbussample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 2/10/18.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageViewHolder> {
    private static final int VIEW_TYPE_CURRENT_USER_MESSAGE = 1;
    private Context context;
    private List<Message> messages = new ArrayList<>();

    public MessageAdapter(Context context) {

        this.context = context;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MessageViewHolder(viewType == 0 ? LayoutInflater.from(context).inflate(R.layout.item_message, parent, false) :
                LayoutInflater.from(context).inflate(R.layout.item_current_user_message, parent, false));
    }

    @Override
    public void onBindViewHolder(MessageViewHolder holder, int position) {
        holder.bindMessage(messages.get(position));
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    public void addMessage(Message message) {
        messages.add(message);
        notifyItemInserted(messages.size() - 1);
    }

    @Override
    public int getItemViewType(int position) {
        if (messages.get(position).isSendByCurrentUser())
            return VIEW_TYPE_CURRENT_USER_MESSAGE;
        else
            return 0;
    }

    public class MessageViewHolder extends RecyclerView.ViewHolder {
        private TextView contentTV;
        private TextView dateTv;
        private TextView fullNameTv;

        public MessageViewHolder(View itemView) {
            super(itemView);
            contentTV=itemView.findViewById(R.id.tv_message_content);
            dateTv=itemView.findViewById(R.id.tv_message_date);
            fullNameTv=itemView.findViewById(R.id.tv_message_fullname);
        }

        public void bindMessage(Message message){
            contentTV.setText(message.getContent());
            dateTv.setText(message.getDate());
            if (message.isSendByCurrentUser()){
                fullNameTv.setVisibility(View.GONE);
            }else {
                fullNameTv.setVisibility(View.VISIBLE);
                fullNameTv.setText(message.getFullName());
            }

        }
    }
}
