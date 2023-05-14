package com.example.hse2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.TextView;

import com.example.hse2.databinding.ActivityHseMainBinding;
import com.example.hse2.databinding.ActivityMainBinding;

public class HSE_activity_main extends AppCompatActivity {

    ActivityHseMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHseMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        String login = intent.getStringExtra("login");
        ExpensesFragment fragment1 = new ExpensesFragment();
        fragment1.setLogin(login);
        replaceFragment(fragment1);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            if (item.getItemId() == R.id.wastes){

                ExpensesFragment fragment = new ExpensesFragment();
                fragment.setLogin(login);
                replaceFragment(fragment);


            }

            if (item.getItemId() == R.id.settings){
                SettingsFragment fragment = (new SettingsFragment());
                fragment.setLogin(login);
                replaceFragment(fragment);
            }

            if (item.getItemId() == R.id.statistic){
                StatisticFragment fragment = (new StatisticFragment());
                fragment.setLogin(login);
                replaceFragment(fragment);
            }
            return true;
        });
    }

    private void replaceFragment(Fragment fragment){

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}