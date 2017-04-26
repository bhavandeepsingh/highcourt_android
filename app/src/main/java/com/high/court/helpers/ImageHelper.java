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

    public static void loadImage(String imgurl, ImageView imageView) {
        ImageLoader.getInstance().displayImage(imgurl, imageView, getDisplayImage(), null, null);
    }

    public static void postImageLoad(String imgurl, ImageView imageView) {

        ImageLoader.getInstance().displayImage(imgurl, imageView, getDisplayImage(), new ImageLoadingListener() {

            @Override
            public void onLoadingStarted(String imageUri, View view) {

            }

            @Override
            public void onLoadingFailed(String imageUri, View view, FailReason failReason) {

            }

            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
            }

            @Override
            public void onLoadingCancelled(String imageUri, View view) {

            }
        });
    }

    public static DisplayImageOptions getDisplayImage() {
        return new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.logo_background)
                .showImageForEmptyUri(R.drawable.logo_background)
                .showImageOnFail(R.drawable.logo_background)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .considerExifParams(true)
                .bitmapConfig(Bitmap.Config.RGB_565)
                .build();
    }

}
