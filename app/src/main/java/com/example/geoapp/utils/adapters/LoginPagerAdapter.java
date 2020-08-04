package com.example.geoapp.utils.adapters;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.geoapp.fragments.LoginFragment;
import com.example.geoapp.fragments.SignUpFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LoginPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments = new ArrayList<>();

    public LoginPagerAdapter(FragmentManager fm, Fragment ... items) {
        super(fm);
        fragments.addAll(Arrays.asList(items));
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return this.fragments.get(position);
    }

    @Override
    public int getCount() {
        return this.fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (this.fragments.get(position) instanceof LoginFragment) ?
                 LoginFragment.getTabHeader() : SignUpFragment.getTabHeader();
    }
}
