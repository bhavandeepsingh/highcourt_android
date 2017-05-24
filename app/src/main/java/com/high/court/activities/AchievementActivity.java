package com.high.court.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.helpers.ImageHelper;
import com.high.court.http.models.AchievementModel;

public class AchievementActivity extends HighCourtActivity {

    Context context = AchievementActivity.this;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_achievement);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Achievements");
        AchievementModel achievementModel = HighCourtApplication.getAchievementModel();
        if(achievementModel != null && achievementModel.getAchievement() != null){
            AchievementModel.Achievement achievement = achievementModel.getAchievement();
            ImageHelper.loadImage(achievement.getImage(), (ImageView) findViewById(R.id.achiv_profile_pic));
            ((TextView) findViewById(R.id.achievment_name)).setText(achievement.getTitle());
            ((WebView) findViewById(R.id.achievmenttext)).loadData(achievement.getDescription(), "text/html; charset=UTF-8", null);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home){
            onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }

}
