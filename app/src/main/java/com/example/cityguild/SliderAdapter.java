package com.example.cityguild;

import android.content.Context;

import androidx.viewpager.widget.PagerAdapter;

public class SliderAdapter extends PagerAdapter {

    Context context;

    public SliderAdapter(Context context){

        this.context = context;

    }

    int images[] = {

            R.drawable.search_place,
            R.drawable.add_missing_place,
            R.drawable.sit_back_and_relax,


    };

    int headings[] = {

            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title,

    };

    

}
