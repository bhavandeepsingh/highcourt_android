package com.high.court.helpers;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.high.court.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Globals {





//    public static void CheckInternetConnection(final Activity activity) {
//        new AlertDialog.Builder(activity)
//                .setTitle(R.string.app_name)
//                .setCancelable(false)
//                .setMessage(R.string.pleasechech)
//                .setPositiveButton(R.string.cancel_b, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        activity.finish();
//                    }
//                })
//                .setNegativeButton(R.string.retry, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        Intent intent = new Intent(activity, activity.getClass());
//                        activity.finish();
//                        activity.startActivity(intent);
//                    }
//                })
//                .show();
//    }

//    public static void ExitDialog(final Activity activity) {
//        new AlertDialog.Builder(activity)
//                .setTitle(R.string.app_name)
//
//                .setMessage(R.string.areyouwant_exit)
//                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        activity.finish();
//                    }
//                })
//                .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                })
//                .show();
//    }










    public static void LoadImage(String imgurl, ImageView imageView) {

        DisplayImageOptions options;

        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.appicon)
                .showImageForEmptyUri(R.drawable.appicon)
                //.showImageOnFail(R.drawable.ic_loge)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();

        ImageLoader.getInstance()
                .displayImage(imgurl, imageView, options, new SimpleImageLoadingListener() {
                    @Override
                    public void onLoadingStarted(String imageUri, View view) {
                    }

                    @Override
                    public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
                    }

                    @Override
                    public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    }
                }, new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String imageUri, View view, int current, int total) {
                    }
                });
    }



}
