package com.example.cityguild.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cityguild.HelperClasses.CheckInternet;
import com.example.cityguild.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.hbb20.CountryCodePicker;


public class ForgetPassword extends AppCompatActivity {

    private CountryCodePicker countryCodePicker;
    private Button nxtBtn;
    private TextInputLayout phoneNumberTextfield;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);


//        hooks
        countryCodePicker = findViewById(R.id.forget_country_code_picker);
        phoneNumberTextfield = findViewById(R.id.forget_phone_number);


    }

    public void callNextForgotPasswordScreen(View view){

        CheckInternet checkInternet = new CheckInternet();

        if (!checkInternet.isConnected(this)){

            showCustomDialog();
            return;

        }



//        get data

        String _phoneNumber = phoneNumberTextfield.getEditText().getText().toString().trim();
        final String _completePhoneNumber = "+"+countryCodePicker.getFullNumber()+_phoneNumber;

        Intent intent = new Intent(getApplicationContext(),SetNewPassword.class);
        intent.putExtra("forgotPhoneNumber",_completePhoneNumber);
        startActivity(intent);

        //        database query


    }




    //    custom dialog

    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(ForgetPassword.this);
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

}