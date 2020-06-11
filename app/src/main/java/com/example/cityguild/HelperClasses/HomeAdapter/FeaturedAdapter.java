package com.example.cityguild.HelperClasses.HomeAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cityguild.R;

import java.util.ArrayList;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedHolder> {

    ArrayList<FeaturedHelper> featuredLocations;

    public FeaturedAdapter(ArrayList<FeaturedHelper> featuredLocations) {
        this.featuredLocations = featuredLocations;
    }

    @NonNull
    @Override
    public FeaturedHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.featured_card_design,parent,false);

        FeaturedHolder featuredHolder = new FeaturedHolder(view);
        return featuredHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedHolder holder, int position) {

//        to get featured helper methods , getters
        FeaturedHelper featuredHelper = featuredLocations.get(position);

        holder.image.setImageResource(featuredHelper.getImage());
        holder.title.setText(featuredHelper.getTitle());
        holder.desc.setText(featuredHelper.getDescription());



    }

    @Override
    public int getItemCount() {
        return featuredLocations.size();
    }

    public static class FeaturedHolder extends RecyclerView.ViewHolder{


        ImageView image;
        TextView title, desc;


        public FeaturedHolder(@NonNull View itemView) {
            super(itemView);

            image = itemView.findViewById(R.id.featured_image);
            title = itemView.findViewById(R.id.featured_title);
            desc = itemView.findViewById(R.id.featured_description);


        }


    }


}
