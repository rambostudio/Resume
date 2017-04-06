package com.harit.chathep.resume.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.inthecheesefactory.thecheeselibrary.manager.Contextor;

/**
 * Created by rambo on 6/4/2560.
 */

public class NetworkStateUtil {
    public static boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) Contextor.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
