package com.high.court.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.high.court.R;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends HighCourtActivity {

    @BindView(R.id.licencenum_edit)
    EditText licencenum_edit;

    @BindView(R.id.password_edit)
    EditText password_edit;

    @BindView(R.id.loginbtn)
    Button loginbtn;

    @BindView(R.id.forgotpassword_btn)
    Button forgotpassword_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        findViewById(R.id.forgotpassword_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(intent);
            }
        });

    }


    @OnClick(R.id.licencenum_edit)
    void licencenum_edit_Click() {
        Toast.makeText(this, "eeee", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.password_edit)
    void password_edit_Click() {
        Toast.makeText(this, "eeee", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.loginbtn)
    void loginbtn_Click() {
        Toast.makeText(this, "eeee", Toast.LENGTH_SHORT).show();

    }

    @OnClick(R.id.forgotpassword_btn)
    void forgotpassword_btn_Click() {
        Toast.makeText(this, "Forgot password", Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


}
