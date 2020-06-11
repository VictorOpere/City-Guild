package com.example.cityguild.User;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.WindowManager;

import com.example.cityguild.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.cityguild.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.cityguild.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.cityguild.HelperClasses.HomeAdapter.FeaturedHelper;
import com.example.cityguild.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.cityguild.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.cityguild.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity {

//    variables

    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;

//    drawer variables

    DrawerLayout drawerLayout;
    NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_user_dashboard);


//        recycler hooks

        featuredRecycler = (RecyclerView) findViewById(R.id.featured_recycler);
        mostViewedRecycler = (RecyclerView) findViewById(R.id.most_viewed_recycler);
        categoriesRecycler = (RecyclerView) findViewById(R.id.categories_recycler);


//        drawer layout hooks
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.navigation_layout);
        





        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();





    }

    private void categoriesRecycler() {

        ArrayList<CategoriesHelperClass> categoriesHelperClasses = new ArrayList<>();
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.sit_back_and_relax, "Education","This is A Fast Food Chain"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.sit_back_and_relax, "HOSPITAL","This is A Fast Food Chain"));
        categoriesHelperClasses.add(new CategoriesHelperClass( R.drawable.sit_back_and_relax, "Restaurant","This is A Fast Food Chain"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.sit_back_and_relax, "Shopping","This is A Fast Food Chain"));
        categoriesHelperClasses.add(new CategoriesHelperClass(R.drawable.sit_back_and_relax, "Transport","This is A Fast Food Chain"));


        categoriesRecycler.setHasFixedSize(true);
        adapter = new CategoriesAdapter(categoriesHelperClasses);
        categoriesRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        categoriesRecycler.setAdapter(adapter);


    }

    private void mostViewedRecycler() {

        ArrayList<MostViewedHelperClass> mostViewedHelperClasses = new ArrayList<>();
        mostViewedHelperClasses.add(new MostViewedHelperClass( R.drawable.sit_back_and_relax, "Education","This is A Fast Food Chain"));
        mostViewedHelperClasses.add(new MostViewedHelperClass(R.drawable.sit_back_and_relax, "HOSPITAL","This is A Fast Food Chain"));
        mostViewedHelperClasses.add(new MostViewedHelperClass( R.drawable.sit_back_and_relax, "Restaurant","This is A Fast Food Chain"));
        mostViewedHelperClasses.add(new MostViewedHelperClass(R.drawable.sit_back_and_relax, "Shopping","This is A Fast Food Chain"));
        mostViewedHelperClasses.add(new MostViewedHelperClass(R.drawable.sit_back_and_relax, "Transport","This is A Fast Food Chain"));


        mostViewedRecycler.setHasFixedSize(true);
        adapter = new MostViewedAdapter(mostViewedHelperClasses);
        mostViewedRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        mostViewedRecycler.setAdapter(adapter);

    }

    private void featuredRecycler() {

        featuredRecycler.setHasFixedSize(true);
        featuredRecycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));

        ArrayList<FeaturedHelper> featuredLocations = new ArrayList<>();

        featuredLocations.add(new FeaturedHelper(R.drawable.add_missing_place,"KFC Shop","This is A Prime Fast Food Joint"));

        featuredLocations.add(new FeaturedHelper(R.drawable.sit_back_and_relax,"Burger King","This is A Prime Fast Food Joint"));

        featuredLocations.add(new FeaturedHelper(R.drawable.sit_back_and_relax,"Little Mary","This is A Prime Fast Food Joint"));

        adapter = new FeaturedAdapter(featuredLocations);
        featuredRecycler.setAdapter(adapter);


    }
}