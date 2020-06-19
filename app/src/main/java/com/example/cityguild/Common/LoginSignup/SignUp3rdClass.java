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




    }

//    call Verify OTP screen

    public void callVerifyOTPScreen(View view){

//        if (!validatePhoneNumber()){
//
//            return;
//        }

        Intent intent = new Intent(getApplicationContext(),VerifyOTP.class);
        String getFullName = intent.getStringExtra("fullname");
        String getUsername = intent.getStringExtra("username");
        String getEmail = intent.getStringExtra("email");
        String getPassword = intent.getStringExtra("password");
        String getGender = intent.getStringExtra("gender");
        String date = intent.getStringExtra("date");


        String getUserEnteredPhoneNumber = phoneNumber.getEditText().getText().toString().trim();

//        get phone number

        String phoneNo = "+" + countryCodePicker.getFullNumber()+getUserEnteredPhoneNumber;

        intent.putExtra("fullname",getFullName);
        intent.putExtra("username",getUsername);
        intent.putExtra("email",getEmail);
        intent.putExtra("password",getPassword);

        intent.putExtra("gender",getGender);
        intent.putExtra("date",date);
        intent.putExtra("phonenumber",phoneNo);






        startActivity(intent);



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
        String checkPhoneNumber = "\\A\\w{1,20}\\z";

        if (value.isEmpty()){

            phoneNumber.setError("Field Cannot Be Empty");
            return false;
        }else if(!value.matches(checkPhoneNumber)){

            phoneNumber.setError("Invalid Phone Number!");
            return false;

        }else {

            phoneNumber.setError(null);
            phoneNumber.setErrorEnabled(false);
            return true;

        }

    }

}