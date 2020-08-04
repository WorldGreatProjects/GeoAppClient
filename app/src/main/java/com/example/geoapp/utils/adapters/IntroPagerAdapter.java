package com.example.geoapp.utils.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.geoapp.R;
import com.example.geoapp.models.IntroPagerItem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IntroPagerAdapter extends PagerAdapter {

    private Context context;
    private List<IntroPagerItem> introScreens = new ArrayList<>();

    public IntroPagerAdapter(Context context, IntroPagerItem... introScreens){
        this.context = context;
        this.introScreens.addAll(Arrays.asList(introScreens));
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        LayoutInflater inflater = (LayoutInflater)  context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View layoutScreen       = inflater.inflate(R.layout.fragment_intro,null);

        ImageView imgView       = layoutScreen.findViewById(R.id.intro_animation);
        TextView introHeader     = layoutScreen.findViewById(R.id.intro_title);
        TextView introDescription  = layoutScreen.findViewById(R.id.intro_subtitle);

        introHeader.setText(introScreens.get(position).getIntroHeader());
        introDescription.setText(introScreens.get(position).getIntroDescription());
        imgView.setImageResource(introScreens.get(position).getIntroImg());

        container.addView(layoutScreen);
        return layoutScreen;
    }

    @Override
    public int getCount() {
        return introScreens.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }
}
