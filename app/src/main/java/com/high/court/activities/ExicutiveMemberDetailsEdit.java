package com.high.court.activities;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.http.models.ProfileModel;
import com.high.court.layouts.ExicutiveMemberDetailLayout;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

public class ExicutiveMemberDetailsEdit extends HighCourtActivity implements OnMapReadyCallback {

    Context context = ExicutiveMemberDetailsEdit.this;
    ImageView pickimage;
    private Uri mCropImageUri;
    public static String PROFILE_INDEX_KEY = "PROFILE_INDEX_KEY";

    private GoogleMap mMap;
    View maplayer;

    Double latval = 30.7573008;
    Double longval = 76.8043739;

    Double lcurrent_atval = 30.873194;
    Double lcurrent_longval = 75.8534603;

    String profile_file_uri;

    String currentlocation_url = "http://maps.google.com/maps?saddr=" + lcurrent_atval + "," + lcurrent_longval + "&daddr=" + latval + "," + longval;
    ExicutiveMemberDetailLayout exicutiveMemberDetailLayout;

    ImageView profile_pic_image_view;
    public static String dirPath;
    public static String imagedirectry;

    CropImageView cropImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exicutive_member_detail_edit);

        ProfileModel profileModel = null;

        if (getIntent().hasExtra(PROFILE_INDEX_KEY)) {
            profileModel = HighCourtApplication.getProfileModels().get(Integer.parseInt(String.valueOf(getIntent().getExtras().get(PROFILE_INDEX_KEY))));
        } else {
            profileModel = ProfileModel.getLoginUserProfile();
        }

        exicutiveMemberDetailLayout = (ExicutiveMemberDetailLayout) findViewById(R.id.exicutive_member_layout);
        exicutiveMemberDetailLayout.setProfile(profileModel);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(profileModel.getName());

        pickimage = (ImageView) findViewById(R.id.pickimage);

        maplayer = (View) findViewById(R.id.maplayer);

        pickimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropImage.startPickImageActivity(ExicutiveMemberDetailsEdit.this);
            }
        });

        maplayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(currentlocation_url));
                startActivity(intent);

            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        CameraUpdate zoom = CameraUpdateFactory.zoomTo(15);
        LatLng sydney = new LatLng(latval, longval);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Punjab and Haryana High Court"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        mMap.animateCamera(zoom);
    }


    @Override
    @SuppressLint("NewApi")
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle result of pick image chooser
        if (requestCode == CropImage.PICK_IMAGE_CHOOSER_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = CropImage.getPickImageResultUri(this, data);
            // For API >= 23 we need to check specifically that we have permissions to read external storage.
            if (CropImage.isReadExternalStoragePermissionsRequired(this, imageUri)) {
                // request permissions and handle the result in onRequestPermissionsResult()
                mCropImageUri = imageUri;
                requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
            } else {
                // no permissions required or already grunted, can start crop image activity
                startCropImageActivity(imageUri);
            }
        }
        // handle result of CropImageActivity
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                cropImageView = (CropImageView) findViewById(R.id.cropImageView);
                cropImageView.setImageUriAsync(result.getUri());
                //loadBitmap(String.valueOf(result.getUri()));
                mCropImageUri = result.getUri();
                profile_pic_image_view = ((ImageView) findViewById(R.id.quick_start_cropped_image));
                profile_pic_image_view.setImageURI(result.getUri());
                //Toast.makeText(this, "Cropping successful, Sample: " + result.getSampleSize(), Toast.LENGTH_LONG).show();
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                //Toast.makeText(this, "Cropping failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (mCropImageUri != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            // required permissions granted, start crop image activity
            startCropImageActivity(mCropImageUri);
        } else {
            //Toast.makeText(this, "Cancelling, required permissions are not granted", Toast.LENGTH_LONG).show();
        }
        if(requestCode == 231 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
            exicutiveMemberDetailLayout.onGrantPermission();
        }
    }

    /**
     * Start crop image activity for the given image.
     */
    private void startCropImageActivity(Uri imageUri) {
        CropImage.activity(imageUri)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setMaxCropResultSize(1024, 1024)
                .setFixAspectRatio(true)
                .setMultiTouchEnabled(true)
                .start(this);
    }

    public CropImageView getCropImageView() {
        return cropImageView;
    }
}
