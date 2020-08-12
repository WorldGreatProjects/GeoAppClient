package com.example.geoapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {

    //ИСПРАВИТЬ, ЧТОБЫ В КОНСТРУКТОР ПРИХОДИЛ ОБЪЕКТ USER
    public String username = null;
    public int accountImg = 0;
    public Integer subCount = 0;
    public Integer folCount = 0;

    public AccountFragment(String username, int accountImg, Integer subCount, Integer folCount) {
        this.username = username;
        this.accountImg = accountImg;
        this.subCount = subCount;
        this.folCount = folCount;
    }

    public interface onSomeEventListener {
        public void someEvent(Fragment fragment);
    }

    onSomeEventListener someEventListener;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            someEventListener = (onSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_account, container, false);

        TextView tv_username = view.findViewById(R.id.tv_nickname);
        if (this.username != null ){
            tv_username.setText(this.username);
        }

        TextView tv_sub = view.findViewById(R.id.tv_subs);
        if (subCount > 1000000){
            subCount/=1000000;
            tv_sub.setText(subCount.toString() + "M\nSubscribers");
        }
        else if(subCount > 1000 && subCount < 1000000){
            subCount /= 1000;
            tv_sub.setText(subCount.toString() + "K\nSubscribers");
        }
        else{
            tv_sub.setText(subCount.toString() + "\nSubscribers");
        }

        TextView tv_followers = view.findViewById(R.id.tv_followers);
        if (folCount > 1000000){
            folCount/=1000000;
            tv_followers.setText(folCount.toString() + "M\nFollowers");
        }
        else if(folCount > 1000 && folCount < 1000000){
            folCount /= 1000;
            tv_followers.setText(folCount.toString() + "K\nFollowers");
        }
        else{
            tv_followers.setText(folCount.toString() + "\nFollowers");
        }

        TextView tv_realName = view.findViewById(R.id.tv_real_name);
        TextView tv_desc = view.findViewById(R.id.tv_desc);

        CircleImageView circleImageView = view.findViewById(R.id.profile_image);
        if (this.accountImg != 0){
            circleImageView.setImageResource(this.accountImg);
        }

        tv_sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someEventListener.someEvent(new SubscribersFragment());
            }
        });

        tv_followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                someEventListener.someEvent(new FollowersFragment());
            }
        });

        return view;
    }
}
