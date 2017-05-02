package com.high.court.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.helpers.HighCourtLoader;
import com.high.court.helpers.NetworkHelper;
import com.high.court.helpers.ToastHelper;
import com.high.court.helpers.UserHelper;
import com.high.court.http.RestAdapter;
import com.high.court.http.models.BloodGroupsModel;
import com.high.court.http.models.ProfileModel;
import com.high.court.http.models.http_interface.BloodGroupInterface;
import com.high.court.layouts.ExicutiveMemberDetailLayout;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExicutiveMemberDetail extends HighCourtActivity implements OnMapReadyCallback, BloodGroupInterface {

    Context context = ExicutiveMemberDetail.this;
    Button logoutbtn;
    ImageView pickimage;

    HighCourtLoader highCourtLoader;

    CropImage.ActivityResult result;

    public static String PROFILE_INDEX_KEY = "PROFILE_INDEX_KEY";

    private GoogleMap mMap;
    View maplayer;

    Double latval = 30.7573008;
    Double longval = 76.8043739;

    Double lcurrent_atval = 30.873194;
    Double lcurrent_longval = 75.8534603;

    String currentlocation_url= "http://maps.google.com/maps?saddr=" + lcurrent_atval+","+lcurrent_longval+"&daddr="+latval+","+ longval;
    ExicutiveMemberDetailLayout exicutiveMemberDetailLayout;

    boolean edit_status = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exicutive_member_detail);

        ProfileModel profileModel = null;

        if(getIntent().hasExtra(PROFILE_INDEX_KEY)) {
            profileModel = HighCourtApplication.getProfileModels().get(Integer.parseInt(String.valueOf(getIntent().getExtras().get(PROFILE_INDEX_KEY))));
        }
        else{
            edit_status = true;
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
                imagePicker();
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                CircleImageView  quick_start_cropped_image = (CircleImageView) findViewById(R.id.quick_start_cropped_image);
                quick_start_cropped_image.setImageResource(0);
                quick_start_cropped_image.setImageURI(result.getUri());

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       if (UserHelper.getLoginId()==exicutiveMemberDetailLayout.getProfileModel().getUser_id() && edit_status){
           getMenuInflater().inflate(R.menu.menu_profilee, menu);
       }
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }if (id == R.id.action_edit_profile) {
            if(!NetworkHelper.state()){
                ToastHelper.showNoNetwork(context);
            }else{
                getHighCourtLoader().start();
                BloodGroupsModel.getBloodGroupList(this);
            }
        }
        return super.onOptionsItemSelected(item);
    }


    void imagePicker() {
        CropImage.activity(null)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setRequestedSize(1000, 1000)
                .setFixAspectRatio(true)
                .start(ExicutiveMemberDetail.this);
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

    public HighCourtLoader getHighCourtLoader() {
        if(highCourtLoader == null) highCourtLoader = HighCourtLoader.init(context);
        return highCourtLoader;
    }

    @Override
    public void onBloodGroupSuccess(BloodGroupsModel bloodGroupsModel) {
        getHighCourtLoader().stop();
        HighCourtApplication.setBloodGroupsModel(bloodGroupsModel);
        if(getIntent().hasExtra(PROFILE_INDEX_KEY)) {
            context.startActivity(new Intent(context, ExicutiveMemberDetailsEdit.class).putExtra(PROFILE_INDEX_KEY, getIntent().getExtras().getString(PROFILE_INDEX_KEY)));
        }else{
            context.startActivity(new Intent(context, ExicutiveMemberDetailsEdit.class));
        }
        finish();
    }

    @Override
    public void onBloodGroupFailur(Throwable t) {
        getHighCourtLoader().stop();
        ToastHelper.showToast(t.getMessage(), context);
    }


}
