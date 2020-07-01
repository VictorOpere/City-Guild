package com.example.cityguild.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.cityguild.HelperClasses.CheckInternet;
import com.example.cityguild.LocationOwner.RetailerDashboard;
import com.example.cityguild.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;


public class Login extends AppCompatActivity {

    CountryCodePicker countryCodePicker;
    TextInputLayout phoneNumber,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_retailer_login);

//        hooks

        countryCodePicker = findViewById(R.id.login_country_code_picker);
        phoneNumber = findViewById(R.id.login_phone_number);
        password = findViewById(R.id.login_password);


    }


    public void letTheUserLoginIn(View view){

//        if connected to the internet

        CheckInternet checkInternet = new CheckInternet();

        if (!checkInternet.isConnected(this)){

            showCustomDialog();
            return;

        }



        if (!validatePassword()){

            return;



        }

        //            get data

        String _phoneNumber = phoneNumber.getEditText().getText().toString().trim();
        final String _password = password.getEditText().getText().toString().trim();

        final String _completePhoneNumber = "+"+countryCodePicker.getFullNumber()+_phoneNumber;


//        database query

        Query checkUser = FirebaseDatabase.getInstance().getReference("Users").orderByChild("phonenumber").equalTo(_completePhoneNumber);

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()){
                    phoneNumber.setError(null);
                    phoneNumber.setErrorEnabled(false);


                    String systemPassword = dataSnapshot.child(_completePhoneNumber).child("password").getValue(String.class);
                    if (systemPassword.equals(_password)){

                        phoneNumber.setError(null);
                        phoneNumber.setErrorEnabled(false);


                        Toast.makeText(Login.this, "it works", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), RetailerDashboard.class));
                        finish();


                    }else {

                        Toast.makeText(Login.this, "Password Does Not Match!", Toast.LENGTH_SHORT).show();


                    }
                }else {

                    Toast.makeText(Login.this, "No Such User Exists!", Toast.LENGTH_SHORT).show();


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

                Toast.makeText(Login.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });






    }

//    custom dialog

    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setMessage("Please Connect To The Internet To Proceed Further")
                .setCancelable(false)
                .setPositiveButton("Connect", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(Settings.ACTION_WIFI_SETTINGS));

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        startActivity(new Intent(getApplicationContext(),RetailerStartUpScreen.class));
                        finish();

                    }
                });
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

//    function to call the Forgot Password Screen

    public void callForgotPasswordScreen(View view){

        startActivity(new Intent(getApplicationContext(),ForgetPassword.class));

    }
}