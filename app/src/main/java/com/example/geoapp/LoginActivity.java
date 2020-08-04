package com.example.geoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.ToxicBakery.viewpager.transforms.CubeInTransformer;
import com.example.geoapp.fragments.LoginFragment;
import com.example.geoapp.fragments.SignUpFragment;
import com.example.geoapp.utils.adapters.LoginPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class LoginActivity extends AppCompatActivity {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private LoginFragment loginFragment;
    private SignUpFragment signUpFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        tabLayout = findViewById(R.id.login_tab_layout);
        viewPager = findViewById(R.id.login_view_pager);

        loginFragment = new LoginFragment();
        signUpFragment = new SignUpFragment();

        FragmentManager fragmentManager = getSupportFragmentManager();
        LoginPagerAdapter loginPagerAdapter = new LoginPagerAdapter(fragmentManager,
                loginFragment,
                signUpFragment );
        viewPager.setAdapter( loginPagerAdapter );
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setPageTransformer(true, new CubeInTransformer());
    }
}
