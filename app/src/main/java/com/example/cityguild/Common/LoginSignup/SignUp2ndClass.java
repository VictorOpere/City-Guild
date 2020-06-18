package com.example.cityguild.Common.LoginSignup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cityguild.R;

import java.util.Calendar;


public class SignUp2ndClass extends AppCompatActivity {

    //    variables
    ImageView backBtn;
    Button next, login;
    TextView titleText;
    RadioGroup radioGroup;
    RadioButton selectedGender;
    DatePicker datePicker;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2nd_class);

        //        hooks

        backBtn = findViewById(R.id.signup_back_button);
        next = findViewById(R.id.signup_next_button);
        login = findViewById(R.id.signup_login_button);
        titleText = findViewById(R.id.signup_title_text);
        radioGroup = findViewById(R.id.radio_group);
        datePicker = findViewById(R.id.date_picker);


    }

    public void callNextSignupScreen(View view){

        if (!validateGender() | !validateAge()){

            return;

        }

        selectedGender  = findViewById(radioGroup.getCheckedRadioButtonId());
        String _gender = selectedGender.getText().toString();

        int day = datePicker.getDayOfMonth();
        int month = datePicker.getMonth();
        int year = datePicker.getYear();

        String date = day+"/"+month+"/"+year;


        Intent intent = new Intent(getApplicationContext(),SignUp3rdClass.class);


        String getFullName = intent.getStringExtra("fullname");
        String getUsername = intent.getStringExtra("username");
        String getEmail = intent.getStringExtra("email");
        String getPassword = intent.getStringExtra("password");

        intent.putExtra("fullname",getFullName);
        intent.putExtra("username",getUsername);
        intent.putExtra("email",getEmail);
        intent.putExtra("password",getPassword);

        intent.putExtra("gender",_gender);
        intent.putExtra("date",date);




//        add transition

        Pair[] pairs = new Pair[4];

        pairs[0] = new Pair<View,String>(backBtn,"transition_back_arrow_btn");
        pairs[1] = new Pair<View,String>(next,"transition_next_btn");
        pairs[2] = new Pair<View,String>(login,"transition_login_btn");
        pairs[3] = new Pair<View,String>(titleText,"transition_title_text");

        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(SignUp2ndClass.this,pairs);

        startActivity(intent,options.toBundle());







    }

    //    login function

    public void callLoginScreenFromSignUp(View view){

        Intent intent = new Intent(getApplicationContext(),Login.class);
        startActivity(intent);
        finish();

    }


    private boolean validateGender(){

        if (radioGroup.getCheckedRadioButtonId()== -1){

            Toast.makeText(this, "Please Select Gender", Toast.LENGTH_SHORT).show();
            return false;
        }else {

            return true;
        }
    }

    private boolean validateAge(){

        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        int userAge = datePicker.getYear();
        int isAgeValid = currentYear - userAge;

        if(isAgeValid < 16){

            Toast.makeText(this, "You Are Not Eligible To Apply", Toast.LENGTH_SHORT).show();
            return false;
            
        }else {

            return true;
        }
    }
}