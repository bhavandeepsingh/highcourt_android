package com.high.court.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.high.court.R;

import butterknife.BindView;
import butterknife.OnClick;

public class ProfileActivity2 extends HighCourtActivity {

    Button logoutbtn;

    @BindView(R.id.pickimage)
    ImageView pickimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Rishab Jain");

        logoutbtn = (Button) findViewById(R.id.logoutbtn);


    }

    @OnClick(R.id.pickimage)
    void Click_pickimage() {
        Toast.makeText(this, "dddd", Toast.LENGTH_SHORT).show();
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


}
