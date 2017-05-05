package com.high.court.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.helpers.ImageHelper;
import com.high.court.http.models.JudgesModel;

public class HonableMemberDetail extends AppCompatActivity {

    public static String PROFILE_LIST_JUDGE_INDEX = "PROFILE_LIST_JUDGE_INDEX";

    JudgesModel.Judge judge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honable_member_detail);

        if(getIntent().hasExtra(PROFILE_LIST_JUDGE_INDEX)){
            judge = HighCourtApplication.getJudgesModel().getJudgeList().get(Integer.parseInt(getIntent().getStringExtra(PROFILE_LIST_JUDGE_INDEX)));
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getJudge().getName());

        if((ImageView) findViewById(R.id.judge_profile_pic) != null) ImageHelper.loadImage(getJudge().getImage_src(), (ImageView) findViewById(R.id.judge_profile_pic));
        if((TextView) findViewById(R.id.judge_landine) != null) ((TextView) findViewById(R.id.judge_landine)).setText(getJudge().getLandline());
        if((TextView) findViewById(R.id.judge_ext) != null) ((TextView) findViewById(R.id.judge_ext)).setText(getJudge().getExt_no());
        if((TextView) findViewById(R.id.judge_address) != null) ((TextView) findViewById(R.id.judge_address)).setText(getJudge().getAddress());
        if((TextView) findViewById(R.id.judge_dob) != null) ((TextView) findViewById(R.id.judge_dob)).setText(getJudge().getDob());
        if((TextView) findViewById(R.id.judge_apoint_ment) != null) ((TextView) findViewById(R.id.judge_apoint_ment)).setText(getJudge().getDate_of_appointment());
        if((TextView) findViewById(R.id.judge_retirement_date) != null) ((TextView) findViewById(R.id.judge_retirement_date)).setText(getJudge().getDate_of_retirement());
        if((TextView) findViewById(R.id.judge_bio) != null) ((TextView) findViewById(R.id.judge_bio)).setText(getJudge().getBio_graphy());
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

    public JudgesModel.Judge getJudge() {
        return judge;
    }
}
