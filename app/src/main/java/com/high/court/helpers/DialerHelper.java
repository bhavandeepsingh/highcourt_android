package com.high.court.helpers;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;

/**
 * Created by gurpreetsingh on 28/04/17.
 */

public class DialerHelper {


    public static void dial(Context context, String phone) {
        String[] phone_numbers = phone.split(",");

        if(phone_numbers != null && phone_numbers.length == 1){
            HighCourtApplication.getHighCourtActivity(context).startActivity(getDialerIntent(phone));
        }
        else if (phone_numbers  != null && phone_numbers.length <= 3) {
            String phone_1 = null, phone_2 = null, phone_3 = null;
            if(phone_numbers.length >= 1) phone_1 = phone_numbers[0];
            if(phone_numbers.length >= 2) phone_2 = phone_numbers[1];
            if(phone_numbers.length >= 3) phone_3 = phone_numbers[3];
            phoneCallDialog(phone_1, phone_2, phone_3, context);
        }
    }

    private static Intent getDialerIntent(String phone) {
        return new Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:"+phone));

    }

    public static void phoneCallDialog(final String phonenum1, final String phonenum2, final String phonenum3, final Context context) {

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_phone);
        TextView ponenumber_val1, ponenumber_val2, ponenumber_val3;
        LinearLayout phonelayer1, phonelayer2, phonelayer3;
        Button callButton1, callButton2, callButton3;

        phonelayer1 = (LinearLayout) dialog.findViewById(R.id.phonelayer);
        phonelayer2 = (LinearLayout) dialog.findViewById(R.id.phonelayer2);
        phonelayer3 = (LinearLayout) dialog.findViewById(R.id.phonelayer3);
        ponenumber_val1 = (TextView) dialog.findViewById(R.id.ponenumber_val);
        ponenumber_val2 = (TextView) dialog.findViewById(R.id.ponenumber_val2);
        ponenumber_val3 = (TextView) dialog.findViewById(R.id.ponenumber_val3);
        callButton1 = (Button) dialog.findViewById(R.id.call1);
        callButton2 = (Button) dialog.findViewById(R.id.call2);
        callButton3 = (Button) dialog.findViewById(R.id.call3);

        if (phonenum1 != null){
            phonelayer1.setVisibility(View.VISIBLE);
            ponenumber_val1.setText(phonenum1);
            callButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialerHelper.dial(context, phonenum1);
                    dialog.dismiss();
                }
            });
        }

        if (phonenum2 != null){
            phonelayer2.setVisibility(View.VISIBLE);
            ponenumber_val2.setText(phonenum2);
            callButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialerHelper.dial(context, phonenum2);
                    dialog.dismiss();
                }
            });
        }

        if (phonenum3 != null){
            phonelayer3.setVisibility(View.VISIBLE);
            ponenumber_val3.setText(phonenum3);
            callButton3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialerHelper.dial(context, phonenum3);
                    dialog.dismiss();
                }
            });
        }

        dialog.show();

    }

}
