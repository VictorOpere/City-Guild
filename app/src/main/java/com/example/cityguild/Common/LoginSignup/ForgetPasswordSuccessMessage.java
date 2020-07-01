package com.example.cityguild.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.cityguild.R;


public class ForgetPasswordSuccessMessage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_success_message);
    }

    public void callBackLoginScreen(View view){

        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();


    }
}