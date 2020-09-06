package com.example.geoapp.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.geoapp.AccountFragment;
import com.example.geoapp.R;
import com.example.geoapp.SubscribersFragment;
import com.example.geoapp.models.Subscriber;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class SubscribersAdapter extends RecyclerView.Adapter<SubscribersAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Subscriber> subscribers;
    private Boolean isSubscribersFragment;
    OnItemClickListener mItemClickListener;

    public SubscribersAdapter(Context context, List<Subscriber> subscribers, Boolean isSubscribersfragment) {
        this.subscribers = subscribers;
        this.inflater = LayoutInflater.from(context);
        this.isSubscribersFragment = isSubscribersfragment;
    }



    @NotNull
    @Override
    public SubscribersAdapter.ViewHolder onCreateViewHolder(@NotNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.item_subscriber, parent, false);

        if (!this.isSubscribersFragment){
            Button followBtn = view.findViewById(R.id.unfollow_btn);
            followBtn.setText("subscribe");
        }

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SubscribersAdapter.ViewHolder holder, int position) {
        Subscriber subscriber = subscribers.get(position);

        holder.accountImg.setImageResource(subscriber.accountImg);
        holder.username.setText(subscriber.username);
    }

    @Override
    public int getItemCount() {
        return subscribers.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        final CircleImageView accountImg;
        final TextView username;

        ViewHolder(View view){
            super(view);
            view.setOnClickListener(this);
            accountImg = (CircleImageView) view.findViewById(R.id.account_img);
            username = (TextView) view.findViewById(R.id.username);
        }

        @Override
        public void onClick(View v) {
            if (mItemClickListener != null) {
                mItemClickListener.onItemClick(v, getAdapterPosition());
            }
        }
    }

    public interface OnItemClickListener {
        public void onItemClick(View view , int position);
    }

    public void SetOnItemClickListener(final OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }
    
}