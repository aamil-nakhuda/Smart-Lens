package com.smartlens;


import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.smartlens.fragments.CodeSearchFragment;
import com.smartlens.fragments.ImageSearchFragment;
import com.smartlens.fragments.LiveWordSearchFragment;
import com.smartlens.fragments.WordSearchFragment;


public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation_bar);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.image_search);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.image_search:
                ImageSearchFragment imageSearchFragment = new ImageSearchFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, imageSearchFragment).commit();
                return true;
            case R.id.word_search:
                WordSearchFragment wordSearchFragment = new WordSearchFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, wordSearchFragment).commit();
                return true;

            case R.id.live_word_search:
                LiveWordSearchFragment liveWordSearchFragment = new LiveWordSearchFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, liveWordSearchFragment).commit();
                return true;
            case R.id.code_search:
                CodeSearchFragment codeSearchFragment = new CodeSearchFragment();
                getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, codeSearchFragment).commit();
                return true;
        }
        return false;
    }
}