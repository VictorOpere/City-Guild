package com.example.cityguild.Common.LoginSignup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.example.cityguild.Database.UserHelperClass;
import com.example.cityguild.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.concurrent.TimeUnit;


public class VerifyOTP extends AppCompatActivity {

//    variables

    PinView pinFromUser;
    String codeBySystem;
    String getFullName, getUsername, getEmail, getPassword,getGender, date, phoneno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);

        pinFromUser = findViewById(R.id.pin_view);

         phoneno = getIntent().getStringExtra("phonenumber");

        getFullName = getIntent().getStringExtra("fullname");
        getUsername = getIntent().getStringExtra("username");
        getEmail =getIntent().getStringExtra("email");
        getPassword = getIntent().getStringExtra("password");
        getGender = getIntent().getStringExtra("gender");
        date = getIntent().getStringExtra("date");


        sendVerificationCodeToUser(phoneno);

    }


    private void sendVerificationCodeToUser(String _phoneno) {

        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                _phoneno,        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                TaskExecutors.MAIN_THREAD,               // Activity (for callback binding)
                mCallbacks);        // OnVerificationStateChangedCallbacks
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks =
            new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

                @Override
                public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                    super.onCodeSent(s, forceResendingToken);
                    codeBySystem = s;

                }

                @Override
                public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {

                    String code = phoneAuthCredential.getSmsCode();

                    if (code!=null){

                        pinFromUser.setText(code);
                        verifyCode(code);
                    }
                }

                @Override
                public void onVerificationFailed(@NonNull FirebaseException e) {

                    Toast.makeText(VerifyOTP.this, e.getMessage(), Toast.LENGTH_SHORT).show();


                }
            };

//    verify code

    private void verifyCode(String code) {

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(codeBySystem,code);
        signInWithPhoneAuthCredential(credential);

    }

//    check credential method

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();

        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

//                            Toast.makeText(VerifyOTP.this, "Verification Is Successful!", Toast.LENGTH_SHORT).show();

                            storeNewUsersData();

                        } else {
                            // Sign in failed, display a message and update the UI

                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid

                                Toast.makeText(VerifyOTP.this, "Verification Not Completed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
    }

    private void storeNewUsersData() {

        FirebaseDatabase rootnode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootnode.getReference("Users");

//        reference.setValue("Certified Vic");

        UserHelperClass addNewUser = new UserHelperClass(getFullName,getUsername,getEmail,getPassword,getGender,date,phoneno);
        reference.child(phoneno).setValue(addNewUser);
        






    }


    public void callNextScreenFromOTP(View view){


        String code = pinFromUser.getText().toString();
        if(!code.isEmpty()){

            verifyCode(code);

        }
//        startActivity(new Intent(getApplicationContext(),SetNewPassword.class));
//        finish();



    }

    public void goToHomeFromOTP(View view){

        startActivity(new Intent(getApplicationContext(),Login.class));

    }

    

}