package com.high.court.helpers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.high.court.HighCourtApplication;
import com.high.court.activities.HighCourtActivity;

/**
 * Created by gurpreetsingh on 28/04/17.
 */

public class DialerHelper {


    public static void dial(Context context,String phone){
        HighCourtApplication.getHighCourtActivity(context).startActivity(getDialerIntent(phone));
    }

    private static Intent getDialerIntent(String phone) {
        return new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:"+phone));

    }

}
