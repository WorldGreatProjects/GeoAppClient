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

public class SubscribersFragment extends Fragment {

    public SubscribersFragment() {
        // Required empty public constructor
    }

    List<Subscriber> mySubscribersArray = new ArrayList<>();

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

        SubscribersAdapter mSubscribersAdapter = new SubscribersAdapter(this.getContext(), mySubscribersArray, true);

        //В КОНСТРУКТОРЕ ФРАГМЕНТА НАДО ОТПРАВЛЯТЬ ОБЪЕКТ USER
        mSubscribersAdapter.SetOnItemClickListener((view, position) -> someEventListener.someEvent(new AccountFragment(mySubscribersArray.get(position).username,
                                                                                                                       mySubscribersArray.get(position).accountImg,
                                                                                                                       mySubscribersArray.get(position).subCount,
                                                                                                                       mySubscribersArray.get(position).folCount)));

        recyclerView.setAdapter(mSubscribersAdapter);

        return v;
    }


    private void setInitialData(){
        mySubscribersArray.add(new Subscriber(R.drawable.test, "LehaWolf", 57, 133));
        mySubscribersArray.add(new Subscriber(R.drawable.test2, "Danya", 181, 29));
        mySubscribersArray.add(new Subscriber(R.drawable.test2, "NAGIBATOR228", 1, 35568));
        mySubscribersArray.add(new Subscriber(R.drawable.test, "MaMiNlYuBiMcHiCk", 100, 100));
        mySubscribersArray.add(new Subscriber(R.drawable.test2, "Kostyan1337", 100, 100));
        mySubscribersArray.add(new Subscriber(R.drawable.test, "Lesha_Sun", 100, 100));
        mySubscribersArray.add(new Subscriber(R.drawable.test2, "KonstantinEvgenyevich", 100, 100));
        mySubscribersArray.add(new Subscriber(R.drawable.test2, "MaTsOnY", 100, 100));
        mySubscribersArray.add(new Subscriber(R.drawable.test2, "Kam(AZ)SHOT", 10000000, 10000000));
        mySubscribersArray.add(new Subscriber(R.drawable.test, "CapitanDanil", 100, 100));
    }

}