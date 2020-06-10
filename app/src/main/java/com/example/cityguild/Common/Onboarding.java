package com.example.cityguild.Common;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.example.cityguild.HelperClasses.SliderAdapter;
import com.example.cityguild.R;

public class Onboarding extends AppCompatActivity {

    ViewPager viewPager;
    LinearLayout dots;

    SliderAdapter sliderAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

//        hooks

        viewPager = findViewById(R.id.slider);
        dots = findViewById(R.id.dots);

//        call adapter

        sliderAdapter = new SliderAdapter(getApplicationContext());

        viewPager.setAdapter(sliderAdapter);




    }
}