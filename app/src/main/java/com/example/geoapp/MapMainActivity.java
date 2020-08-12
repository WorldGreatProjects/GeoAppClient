package com.example.geoapp;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.w3c.dom.Text;

public class MapMainActivity extends AppCompatActivity implements AccountFragment.onSomeEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener( listener );
        navView.setSelectedItemId(R.id.map);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener listener
    = item -> {
        Fragment selectedFragment = null;
        switch ( item.getItemId() ){

            case R.id.action:
                selectedFragment = new ActionFragment();
                break;
            case R.id.map:
                selectedFragment = new MapFragment();
                break;
            case R.id.account:
                selectedFragment = new AccountFragment(null, 0, 10, 10);
                break;
        }
        if (selectedFragment == null){
            selectedFragment = new MapFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
        return true;
    };

    public void someEvent(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).addToBackStack("").commit();
    }


}
