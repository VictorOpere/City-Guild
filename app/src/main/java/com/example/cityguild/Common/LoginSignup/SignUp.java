package com.example.cityguild.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cityguild.R;
import com.google.android.material.textfield.TextInputLayout;


public class SignUp extends AppCompatActivity {

//    variables
    ImageView backBtn;
    Button next, login;
    TextView titleText;


//    variables

    TextInputLayout fullname, username, email,password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retailer_sign_up);

//        hooks

        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);

//        hooks for getting data

        fullname = findViewById(R.id.signup_fullname);
        username = findViewById(R.id.signup_username);
        email = findViewById(R.id.signup_email);
        password = findViewById(R.id.signup_enter_password);



    }

    public void callNextSignupScreen(View view){

        if (!validateFullName() | !validateUserName() | !validateEmail() | !validatePassword()){

            return;

        }


        Intent intent = new Intent(getApplicationContext(),SignUp2ndClass.class);

        intent.putExtra("fullname",fullname.getEditText().getText().toString().trim());
        intent.putExtra("username",username.getEditText().getText().toString().trim());
        intent.putExtra("email",email.getEditText().getText().toString().trim());
        intent.putExtra("password",password.getEditText().getText().toString().trim());


//        add transition

        Pair [] pairs = new Pair[4];

        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1] = new Pair<View,String>(next,"transition_next_btn");
        pairs[2] = new Pair<View,String>(login,"transition_login_btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp.this,pairs);

        startActivity(intent,options.toBundle());







    }

//    login function

    public void callLoginScreenFromSignUp(View view){

        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();

    }

//    validation functions

    private boolean validateFullName(){

        String value = fullname.getEditText().getText().toString().trim();

        if (value.isEmpty()){

            fullname.setError("Field Cannot Be Empty");
            return false;
        }else {

            fullname.setError(null);
            fullname.setErrorEnabled(false);
            return true;

        }

    }

    private boolean validateUserName(){

        String value = username.getEditText().getText().toString().trim();
        String checkSpaces = "\\A\\w{1,20}\\z";

        if (value.isEmpty()){

            username.setError("Field Cannot Be Empty");
            return false;
        }else if(value.length() > 20){

            username.setError("Username is Too Big");
            return false;

        }else if(!value.matches(checkSpaces)){

            username.setError("No White Spaces Allowed");
            return false;

        }else {

            username.setError(null);
            username.setErrorEnabled(false);
            return true;

        }

    }

    private boolean validateEmail(){

        String value = email.getEditText().getText().toString().trim();
        String checkEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (value.isEmpty()){

            email.setError("Field Cannot Be Empty");
            return false;
        }else if(!value.matches(checkEmail)){

            email.setError("Invalid Email!");
            return false;

        }else {

            email.setError(null);
            email.setErrorEnabled(false);
            return true;

        }

    }

    private boolean validatePassword(){

        String value = password.getEditText().getText().toString().trim();
        String checkPassword = "\\A\\w{1,20}\\z";


        if (value.isEmpty()){

            password.setError("Field Cannot Be Empty");
            return false;
        }else if(!value.matches(checkPassword)){

            password.setError("Password Should Contain 4 Characters!");
            return false;

        }else {

            password.setError(null);
            password.setErrorEnabled(false);
            return true;

        }

    }

}