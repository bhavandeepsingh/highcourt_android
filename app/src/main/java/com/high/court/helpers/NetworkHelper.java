package com.high.court.helpers;

/**
 * Created by Akshit on 26/04/2017.
 */

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.high.court.HighCourtApplication;

/**
 * Created by bhavan on 1/4/17.
 */

public class NetworkHelper {

    public static boolean state() {
        final ConnectivityManager conMgr = (ConnectivityManager) HighCourtApplication.getHighCourtApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetwork = conMgr.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) return true;
        return false;
    }

}
