package com.example.geoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.ToxicBakery.viewpager.transforms.AccordionTransformer;
import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.ToxicBakery.viewpager.transforms.FlipVerticalTransformer;
import com.ToxicBakery.viewpager.transforms.RotateUpTransformer;
import com.ToxicBakery.viewpager.transforms.StackTransformer;
import com.ToxicBakery.viewpager.transforms.TabletTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomInTransformer;
import com.ToxicBakery.viewpager.transforms.ZoomOutSlideTransformer;
import com.example.geoapp.models.IntroPagerItem;
import com.example.geoapp.utils.adapters.IntroPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class IntroActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Button btnNext;
    private Button btnSkip;
    private int PAGES_QUANTITY = 3;

    //TODO заменить на нормальные строковые ресурсы при переходе к чистовику
    private String Header = "Test Header";
    private String Descrip = "Test description : some random description about out application";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        tabLayout = findViewById(R.id.intro_tab_layout);
        viewPager = findViewById(R.id.intro_view_pager);
        btnNext   = findViewById(R.id.btn_next);
        btnSkip   = findViewById(R.id.btn_skip);
        IntroPagerAdapter pagerAdapter = new IntroPagerAdapter(this,
                new IntroPagerItem(Header, Descrip, R.drawable.test2),
                new IntroPagerItem(Header + "1", Descrip, R.drawable.test2),
                new IntroPagerItem(Header + "2", Descrip, R.drawable.test2));

        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(true, new ZoomOutSlideTransformer());
        btnNext.setOnClickListener((view) -> {
            int position = viewPager.getCurrentItem();
            if ( position == pagerAdapter.getCount() - 1 ){
                startActivity( new Intent(IntroActivity.this,MainActivity.class) );
            }
            else viewPager.setCurrentItem(++position);
        });

        btnSkip.setOnClickListener(v -> startActivity( new Intent(IntroActivity.this, MainActivity.class)));

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                btnNext.setText((position < pagerAdapter.getCount() - 1) ? "next" : "start now" );
            }
            @Override
            public void onPageScrollStateChanged(int state) {}
        });

    }
}
