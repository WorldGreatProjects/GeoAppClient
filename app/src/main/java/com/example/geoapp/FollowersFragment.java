package com.example.geoapp;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.geoapp.models.Subscriber;
import com.example.geoapp.utils.adapters.SubscribersAdapter;

import java.util.ArrayList;
import java.util.List;

public class FollowersFragment extends Fragment {

    public FollowersFragment() {
        // Required empty public constructor
    }

    List<Subscriber> myFollowersArray = new ArrayList<>();

    AccountFragment.onSomeEventListener someEventListener;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            someEventListener = (AccountFragment.onSomeEventListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onSomeEventListener");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_subscribers, container, false);

        setInitialData();


        RecyclerView recyclerView = v.findViewById(R.id.subs_recycle_view);

        SubscribersAdapter mFollowersAdapter = new SubscribersAdapter(this.getContext(), myFollowersArray, false);

        //В КОНСТРУКТОРЕ ФРАГМЕНТА НАДО ОТПРАВЛЯТЬ ОБЪЕКТ USER
        mFollowersAdapter.SetOnItemClickListener((view, position) -> someEventListener.someEvent(new AccountFragment(myFollowersArray.get(position).username,
                                                                                                                     myFollowersArray.get(position).accountImg,
                                                                                                                     myFollowersArray.get(position).subCount,
                                                                                                                     myFollowersArray.get(position).folCount)));

        recyclerView.setAdapter(mFollowersAdapter);

        return v;
    }


    private void setInitialData(){
        myFollowersArray.add(new Subscriber(R.drawable.test, "LehaWolf", 57, 133));
        myFollowersArray.add(new Subscriber(R.drawable.test2, "Danya", 181, 29));
        myFollowersArray.add(new Subscriber(R.drawable.test2, "NAGIBATOR228", 1, 35568));
        myFollowersArray.add(new Subscriber(R.drawable.test, "MaMiNlYuBiMcHiCk", 100, 100));
        myFollowersArray.add(new Subscriber(R.drawable.test2, "Kostyan1337", 100, 100));
        myFollowersArray.add(new Subscriber(R.drawable.test, "Lesha_Sun", 100, 100));
        myFollowersArray.add(new Subscriber(R.drawable.test2, "KonstantinEvgenyevich", 100, 100));
        myFollowersArray.add(new Subscriber(R.drawable.test2, "MaTsOnY", 100, 100));
        myFollowersArray.add(new Subscriber(R.drawable.test2, "Kam(AZ)SHOT", 10000000, 10000000));
        myFollowersArray.add(new Subscriber(R.drawable.test, "CapitanDanil", 100, 100));
    }
}