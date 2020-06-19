package com.example.cityguild.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.cityguild.R;
import com.google.android.material.textfield.TextInputLayout;
import com.hbb20.CountryCodePicker;


public class SignUp3rdClass extends AppCompatActivity {

//    variables
    ScrollView scrollView;
    TextInputLayout phoneNumber;
    CountryCodePicker countryCodePicker;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up3rd_class);

//        hooks

        scrollView = findViewById(R.id.signup_3rd_scrollview);
        phoneNumber = findViewById(R.id.signup_phone_number);
        countryCodePicker = findViewById(R.id.country_code_picker);


        Intent intent = new Intent();
        String getFullName = intent.getStringExtra("fullname");
        String getUsername = intent.getStringExtra("username");
        String getEmail = intent.getStringExtra("email");
        String getPassword = intent.getStringExtra("password");
        String getGender = intent.getStringExtra("gender");
        String date = intent.getStringExtra("date");

    }

//    call Verify OTP screen

    public void callVerifyOTPScreen(){

        if (!validatePhoneNumber()){

            return;
        }

        startActivity(new Intent(getApplicationContext(),VerifyOTP.class));
        finish();


    }

    //    login function

    public void callLoginScreenFromSignUp(View view){

        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();

    }

//    validate phone number

    private boolean validatePhoneNumber(){

        String value = phoneNumber.getEditText().getText().toString().trim();
        String checkEmail = "[0-9]";

        if (value.isEmpty()){

            phoneNumber.setError("Field Cannot Be Empty");
            return false;
        }else if(!value.matches(checkEmail)){

            phoneNumber.setError("Invalid Email!");
            return false;

        }else {

            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;

        }

    }

}