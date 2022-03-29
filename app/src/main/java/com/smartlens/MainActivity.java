package com.smartlens;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smartlens.fragments.CodeSearchFragment;
import com.smartlens.fragments.ImageSearchFragment;
import com.smartlens.fragments.LiveWordSearchFragment;
import com.smartlens.fragments.WordSearchFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    final Fragment fragment1 = new ImageSearchFragment();
    final Fragment fragment2 = new WordSearchFragment();
    final Fragment fragment3 = new LiveWordSearchFragment();
    final Fragment fragment4 = new CodeSearchFragment();
    FragmentManager fm = getSupportFragmentManager();

    BottomNavigationView bottomNavigationView;
    Fragment active = fragment1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        fm.beginTransaction().add(R.id.frame_layout, fragment4, "4").hide(fragment4).commit();
        fm.beginTransaction().add(R.id.frame_layout, fragment3, "3").hide(fragment3).commit();
        fm.beginTransaction().add(R.id.frame_layout, fragment2, "2").hide(fragment2).commit();
        fm.beginTransaction().add(R.id.frame_layout, fragment1, "1").commit();


    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if (itemId == R.id.image_search) {
            fm.beginTransaction().hide(active).show(fragment1).commit();
            active = fragment1;

        } else if (itemId == R.id.word_search) {
            fm.beginTransaction().hide(active).show(fragment2).commit();
            active = fragment2;

        } else if (itemId == R.id.live_word_search) {
            fm.beginTransaction().hide(active).show(fragment3).commit();
            active = fragment3;
        } else if (itemId == R.id.code_search) {
            fm.beginTransaction().hide(active).show(fragment4).commit();
            active = fragment4;
        }

        return true;

    }
}