package com.example.cityguild.HelperClasses;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.example.cityguild.R;

public class SliderAdapter extends PagerAdapter {

    Context context;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context){

        this.context = context;

    }

    int images[] = {

            R.drawable.search_place,
            R.drawable.add_missing_place,
            R.drawable.sit_back_and_relax


    };

    int headings[] = {

            R.string.first_slide_title,
            R.string.second_slide_title,
            R.string.third_slide_title

    };

    int descriptions[] = {

            R.string.first_slide_desc,
            R.string.second_slide_desc,
            R.string.third_slide_desc

    };


    @Override
    public int getCount() {
        return headings.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slides_layout, container, false);

//        hooks from design view


        ImageView imageView = view.findViewById(R.id.sliderImage);
        TextView headingtextView = view.findViewById(R.id.sliderText);
        TextView descView = view.findViewById(R.id.sliderDescription);

        imageView.setImageResource(images[position]);
        headingtextView.setText(images[position]);
        descView.setText(images[position]);

        container.addView(view);



        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);

    }
}
