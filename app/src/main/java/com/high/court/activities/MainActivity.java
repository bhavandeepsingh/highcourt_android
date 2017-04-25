
package com.high.court.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.high.court.R;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView loginscreen = (TextView) findViewById(R.id.loginscreen);
        TextView forgotpassword = (TextView) findViewById(R.id.forgotpassword);
        TextView dashboard = (TextView) findViewById(R.id.dashboard);
        TextView honblejudges = (TextView) findViewById(R.id.honblejudges);
        TextView executivecommittee = (TextView) findViewById(R.id.executivecommittee);
        TextView memberdirectory = (TextView) findViewById(R.id.memberdirectory);
        TextView nitif = (TextView) findViewById(R.id.nitif);
        TextView displayboard = (TextView) findViewById(R.id.displayboard);
        TextView subscription = (TextView) findViewById(R.id.subscription);
        TextView calender = (TextView) findViewById(R.id.calender);
        TextView roster = (TextView) findViewById(R.id.roster);
        TextView profile = (TextView) findViewById(R.id.profile);
        TextView exicutive = (TextView) findViewById(R.id.exicutive);
        TextView changepassword = (TextView) findViewById(R.id.changepassword);
        TextView honabledetail = (TextView) findViewById(R.id.honabledetail);

        loginscreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });
        dashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
                startActivity(intent);
            }
        });
        honblejudges.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HonbleJudgesActivity.class);
                startActivity(intent);
            }
        });
        executivecommittee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExicutiveCommettieeActivity.class);
                startActivity(intent);
            }
        });
        memberdirectory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MemberDirectoryActivity.class);
                startActivity(intent);
            }
        });
        nitif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NoificationActivity.class);
                startActivity(intent);
            }
        });
        displayboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DisplayBoardActivity.class);
                startActivity(intent);
            }
        });
        subscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MySubscriptionActivity.class);
                startActivity(intent);
            }
        });
        calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CalenderActivity.class);
                startActivity(intent);
            }
        });
        roster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RosterActivity.class);
                startActivity(intent);
            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity2.class);
                startActivity(intent);
            }
        });
        exicutive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExicutiveMemberDetail.class);
                startActivity(intent);
            }
        });
        changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ChangePassword.class);
                startActivity(intent);
            }
        });
        honabledetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HonableMemberDetail.class);
                startActivity(intent);
            }
        });


    }
}
