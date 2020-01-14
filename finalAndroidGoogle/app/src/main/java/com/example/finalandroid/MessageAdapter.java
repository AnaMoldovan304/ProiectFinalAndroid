package com.example.finalandroid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageViewHolder> {

    private List<Message> messageList;
    private Context context;
    private OnItemClickListener mListener;

    public static final int DESIGN_1 = 1;
    public static final int DESIGN_2 = 2;
    public static final int DESIGN_3 = 3;


    private int chosenDesign = DESIGN_1;

    public MessageAdapter(Context context, List messageList) {
        this.context = context;
        this.messageList = messageList;
    }


    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @Override
    public MessageViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        switch (viewType) {
            case DESIGN_1:
                return new MessageViewHolder(layoutInflater.inflate(R.layout.message_item_type_1,
                        viewGroup, false), mListener);

            case DESIGN_2:
                return new MessageViewHolder(layoutInflater.inflate(R.layout.message_item_type_2,
                        viewGroup, false), mListener);
            default:
                return null;

        }
    }

    @Override
    public void onBindViewHolder(MessageViewHolder viewHolder, int position) {
        viewHolder.bindViewHolder(messageList.get(position));
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }


    @Override
    public int getItemViewType(int position) {
        if (chosenDesign != DESIGN_3) {
            return chosenDesign;
        } else {
            if (position % 2 == 0) {
                return DESIGN_1;
            } else {
                return DESIGN_2;
            }
        }
    }


    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    public void updateList() {
        notifyDataSetChanged();
    }

    private int oppositeDesign(int design) {
        if (design == DESIGN_1) {
            return DESIGN_2;
        } else if (design == DESIGN_2) {
            return DESIGN_1;
        }
        return -1;
    }

    public void applyDesign(int design) {
        if (chosenDesign == design) {
            return;
        } else {

            if (design == oppositeDesign(chosenDesign)) {
                chosenDesign = design;
                notifyDataSetChanged();
            } else {
                if (design == DESIGN_1) {
                    chosenDesign = design;
                    applyDesigns(1);
                } else if (design == DESIGN_2) {
                    chosenDesign = design;
                    applyDesigns(0);
                } else {
                    if (chosenDesign == DESIGN_1) {
                        chosenDesign = design;
                        applyDesigns(1);
                    } else {
                        chosenDesign = design;
                        applyDesigns(0);
                    }
                }
            }
        }
    }

    private void applyDesigns(int start) {
        for (int i = start; i < messageList.size(); i = i + 2) {
            notifyItemChanged(i);
        }
    }
}
