
package com.high.court.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.high.court.R;
import com.high.court.helpers.UserHelper;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends HighCourtActivity {


    @BindView(R.id.loginscreen)
    TextView loginscreen;

    @BindView(R.id.forgotpassword)
    TextView forgotpassword;

    @BindView(R.id.dashboard)
    TextView dashboard;

    @BindView(R.id.honblejudges)
    TextView honblejudges;


    @BindView(R.id.executivecommittee)
    TextView executivecommittee;

    @BindView(R.id.memberdirectory)
    TextView memberdirectory;


    @BindView(R.id.nitif)
    TextView nitif;

    @BindView(R.id.displayboard)
    TextView displayboard;

    @BindView(R.id.subscription)
    TextView subscription;

    @BindView(R.id.calender)
    TextView calender;

    @BindView(R.id.roster)
    TextView roster;

    @BindView(R.id.profile)
    TextView profile;

    @BindView(R.id.exicutive)
    TextView exicutive;

    @BindView(R.id.changepassword)
    TextView changepassword;

    @BindView(R.id.honabledetail)
    TextView honabledetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(UserHelper.getState()) setContentView(R.layout.activity_main);
        else setContentView(R.layout.activity_login);
    }

    @OnClick(R.id.loginscreen)
    void onLoginClick(){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
    }

    @OnClick(R.id.forgotpassword)
    void onForgotPassword(){
        Intent intent = new Intent(MainActivity.this, ForgotPasswordActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.dashboard)
    void onDashboard(){
        Intent intent = new Intent(MainActivity.this, DashboardActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.honblejudges)
    void onHonblejudges(){
        Intent intent = new Intent(MainActivity.this, HonbleJudgesActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.executivecommittee)
    void onExecutiveCommittee(){
        Intent intent = new Intent(MainActivity.this, ExicutiveCommettieeActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.memberdirectory)
    void onMemberDirectory(){
        Intent intent = new Intent(MainActivity.this, MemberDirectoryActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.nitif)
    void onNitif(){
        Intent intent = new Intent(MainActivity.this, NoificationActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.displayboard)
    void onDisplayBoard(){
        Intent intent = new Intent(MainActivity.this, DisplayBoardActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.subscription)
    void onSubscription(){
        Intent intent = new Intent(MainActivity.this, MySubscriptionActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.calender)
    void onCalender(){
        Intent intent = new Intent(MainActivity.this, CalenderActivity.class);
        startActivity(intent);
    }

     @OnClick(R.id.roster)
     void onRoster(){
        Intent intent = new Intent(MainActivity.this, RosterActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.profile)
    void onProfile(){
        Intent intent = new Intent(MainActivity.this, ProfileActivity2.class);
        startActivity(intent);
    }

    @OnClick(R.id.exicutive)
    void onExicutive(){
        Intent intent = new Intent(MainActivity.this, ExicutiveMemberDetail.class);
        startActivity(intent);
    }

    @OnClick(R.id.changepassword)
    void onChangePassword(){
        Intent intent = new Intent(MainActivity.this, ChangePassword.class);
        startActivity(intent);
    }

    @OnClick(R.id.honabledetail)
    void onHonableDetail(){
        Intent intent = new Intent(MainActivity.this, HonableMemberDetail.class);
        startActivity(intent);
    }

}
