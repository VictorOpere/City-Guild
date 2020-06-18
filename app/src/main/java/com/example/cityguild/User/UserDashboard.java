package com.example.cityguild.User;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.cityguild.Common.LoginSignup.Login;
import com.example.cityguild.Common.LoginSignup.RetailerStartUpScreen;
import com.example.cityguild.HelperClasses.HomeAdapter.CategoriesAdapter;
import com.example.cityguild.HelperClasses.HomeAdapter.CategoriesHelperClass;
import com.example.cityguild.HelperClasses.HomeAdapter.FeaturedAdapter;
import com.example.cityguild.HelperClasses.HomeAdapter.FeaturedHelper;
import com.example.cityguild.HelperClasses.HomeAdapter.MostViewedAdapter;
import com.example.cityguild.HelperClasses.HomeAdapter.MostViewedHelperClass;
import com.example.cityguild.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class UserDashboard extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

//    variables

    RecyclerView featuredRecycler, mostViewedRecycler, categoriesRecycler;
    RecyclerView.Adapter adapter;
    ImageView menuIcon;

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

//        menu icon hook
        menuIcon = (ImageView) findViewById(R.id.menu_icon);




//        menu icon to open

        navigationDrawer();







        featuredRecycler();
        mostViewedRecycler();
        categoriesRecycler();





    }

//    navigation drawer functions

    private void navigationDrawer() {

        //        navigation drawer

        navigationView.bringToFront();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);

//        menu icon

        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(drawerLayout.isDrawerVisible(GravityCompat.START)){

                    drawerLayout.closeDrawer(GravityCompat.START);


                }else{

                    drawerLayout.openDrawer(GravityCompat.START);

                }

            }
        });


        animateNavigationDrawer();

    }

    private void animateNavigationDrawer() {

        drawerLayout.setScrimColor(Color.parseColor("#FCF3CF"));
    };

//    normal functions

    public void callRetailerScreen(View view){

        startActivity(new Intent(getApplicationContext(), RetailerStartUpScreen.class));





    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerVisible(GravityCompat.START)){

            drawerLayout.closeDrawer(GravityCompat.START);
        }else{

            super.onBackPressed();

        }

    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.all_categories:
                Intent intent = new Intent(getApplicationContext(), AllCategories.class);
                startActivity(intent);
                break;

            case R.id.nav_login:
                startActivity(new Intent(getApplicationContext(), Login.class));
                break;


        }
        return true;
    }


//    recycler views functions


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