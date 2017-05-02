package com.high.court.helpers;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.high.court.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;

/**
 * Created by bhavan on 1/20/17.
 */

public class ImageHelper {


    static int onLoading = R.drawable.logo_background;
    static int forEmptyUri = R.drawable.logo_background;
    static int onFailImage = R.drawable.logo_background;

    public static void loadImage(String imgurl, ImageView imageView) {
        ImageLoader.getInstance().displayImage(imgurl, imageView, getDisplayImage(), null, null);
    }

    public static DisplayImageOptions getDisplayImage() {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(getOnLoading())
                .showImageForEmptyUri(getForEmptyUri())
                .showImageOnFail(getOnFailImage())
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

    public static int getOnLoading() {
        return onLoading;
    }

    public static void setOnLoading(int onLoading) {
        ImageHelper.onLoading = onLoading;
    }

    public static int getForEmptyUri() {
        return forEmptyUri;
    }

    public static void setForEmptyUri(int forEmptyUri) {
        ImageHelper.forEmptyUri = forEmptyUri;
    }

    public static int getOnFailImage() {
        return onFailImage;
    }

    public static void setOnFailImage(int onFailImage) {
        ImageHelper.onFailImage = onFailImage;
    }
}
