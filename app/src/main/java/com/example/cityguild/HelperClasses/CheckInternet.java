package com.example.cityguild.HelperClasses;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.cityguild.Common.LoginSignup.Login;

public class CheckInternet {


    //    check user intenet connection

    public boolean isConnected(Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo wifiInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo mobileInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

        if ((wifiInfo != null && wifiInfo.isConnected() || (mobileInfo != null && mobileInfo.isConnected()))){

            return true;



        }else {

            return false;
        }

    }
}
