package com.high.court.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.high.court.R;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.bitmap;
import static java.security.AccessController.getContext;

public class ExicutiveMemberDetail extends AppCompatActivity {


    Context context = ExicutiveMemberDetail.this;
    Button logoutbtn;
    @BindView(R.id.pickimage)
    ImageView pickimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exicutive_member_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Harpreet Singh Brar");

        logoutbtn = (Button) findViewById(R.id.logoutbtn);
        pickimage = (ImageView) findViewById(R.id.pickimage);


        pickimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imagePicker();
            }
        });


    }






    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {
                CircleImageView  quick_start_cropped_image = (CircleImageView) findViewById(R.id.quick_start_cropped_image);
                quick_start_cropped_image.setImageURI(result.getUri());
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Toast.makeText(this, "Failed: " + result.getError(), Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_profilee, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        if (id == R.id.action_edit_profile) {
            logoutbtn.setVisibility(View.VISIBLE);
        }
        return super.onOptionsItemSelected(item);
    }



    void imagePicker(){
        CropImage.activity(null)
                .setGuidelines(CropImageView.Guidelines.ON)
                .setRequestedSize(1000,1000)
                .setFixAspectRatio(true)
                .start(ExicutiveMemberDetail.this);
    }


}
