package com.example.cityguild.Common.LoginSignup;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.example.cityguild.HelperClasses.CheckInternet;
import com.example.cityguild.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.FirebaseApiNotAvailableException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SetNewPassword extends AppCompatActivity {

//    variables

    TextInputLayout confirmNewPassword, updateNewPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_new_password);

//        hooks
        confirmNewPassword = findViewById(R.id.confirm_new_password);
        updateNewPassword = findViewById(R.id.set_new_password);



    }


//    set New Password

    public void setNewPasswordBtn(View view){

        //        if connected to the internet

        CheckInternet checkInternet = new CheckInternet();

        if (!checkInternet.isConnected(this)){

            showCustomDialog();
            return;

        }


//        if (!validateNewPassword() | !validateConfirmedNewPassword()){
//
//            return;
//
//        }

        String _newPassword = confirmNewPassword.getEditText().getText().toString().trim();

        String _phoneNumber = getIntent().getStringExtra("forgotPhoneNumber");

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users");
        reference.child(_phoneNumber).child("password").setValue(_newPassword);

        startActivity(new Intent(getApplicationContext(),ForgetPasswordSuccessMessage.class));
        finish();





    }

    //    validate password

//    private boolean validateNewPassword(){
//
//        String value = updateNewPassword.getEditText().getText().toString().trim();
//        String checkPassword = "\\A\\w{1,20}\\z";
//
//
//        if (value.isEmpty()){
//
//            updateNewPassword.setError("Field Cannot Be Empty");
//            return false;
//        }else if(!value.matches(checkPassword)){
//
//            updateNewPassword.setError("Password Should Contain 4 Characters!");
//            return false;
//
//        }else {
//
//            updateNewPassword.setError(null);
//            updateNewPassword.setErrorEnabled(false);
//            return true;
//
//        }
//
//    }

//    validate new password

//    private boolean validateConfirmedNewPassword(){
//
//        String value = confirmNewPassword.getEditText().getText().toString().trim();
//        String checkPassword = "\\A\\w{1,20}\\z";
//
//
//        if (value.isEmpty()){
//
//            confirmNewPassword.setError("Field Cannot Be Empty");
//            return false;
//        }else if(!value.matches(checkPassword)){
//
//            confirmNewPassword.setError("Password Should Contain 4 Characters!");
//            return false;
//
//        }else {
//
//            confirmNewPassword.setError(null);
//            confirmNewPassword.setErrorEnabled(false);
//            return true;
//
//        }
//
//    }

    //    custom dialog

    private void showCustomDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(SetNewPassword.this);
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