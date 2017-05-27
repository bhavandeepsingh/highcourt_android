package com.high.court.layouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.high.court.R;
import com.high.court.activities.DashboardActivity;
import com.high.court.activities.ForgotPasswordActivity;
import com.high.court.activities.HighCourtActivity;
import com.high.court.helpers.NetworkHelper;
import com.high.court.helpers.ToastHelper;
import com.high.court.helpers.UserHelper;
import com.high.court.http.models.UserLoginModel;
import com.high.court.http.models.http_interface.HighCourtLoginInterface;


/**
 * Created by admin on 4/25/2017.
 */

public class HighCourtLogin extends HighCourtMainLinearLayout implements View.OnClickListener, HighCourtLoginInterface {

    EditText licence_tex;

    EditText password;

    TextView sign_in;

    TextView forgot_password;

    public HighCourtLogin(Context context) {
        super(context);
    }

    public HighCourtLogin(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public HighCourtLogin(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    public void init(){
        super.init();
        setLicence_tex((EditText) findViewById(R.id.licencenum_edit))
        .setPassword((EditText) findViewById(R.id.password_edit))
        .setSign_in((TextView) findViewById(R.id.loginbtn))
        .setForgot_password((TextView) findViewById(R.id.forgotpassword_btn));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInitiate()) init();
    }

    public EditText getLicence_tex() {
        return licence_tex;
    }

    public HighCourtLogin setLicence_tex(EditText licence_tex) {
        this.licence_tex = licence_tex;
        return this;
    }

    public EditText getPassword() {
        return password;
    }

    public HighCourtLogin setPassword(EditText password) {
        this.password = password;
        return this;
    }

    public TextView getSign_in() {
        return sign_in;
    }

    public HighCourtLogin setSign_in(TextView sign_in) {
        this.sign_in = sign_in;
        if(this.sign_in != null) this.sign_in.setOnClickListener(this);
        return this;
    }

    public TextView getForgot_password() {
        return forgot_password;
    }

    public HighCourtLogin setForgot_password(TextView forgot_password) {
        this.forgot_password = forgot_password;
        if(this.forgot_password != null){
            this.forgot_password.setOnClickListener(this);
        }
        return this;}


    void onForgotPasswordClick(View v){
        ((HighCourtActivity) getContext()).startActivity(new Intent(getContext(), ForgotPasswordActivity.class));

    }

    void onSignInClick(View v){

        if(!NetworkHelper.state()){
            ToastHelper.showNoNetwork(getContext());
            return;
        }

        if(getLicence_tex().getText().length() <= 0){
            ToastHelper.pleaseFillLicenece(getContext());
            return;
        }

        if(getPassword().getText().length() <= 0){
            ToastHelper.pleaseFillPassword(getContext());
            return;
        }

        getHighCourtLoader().start();

        UserLoginModel.login(getLicence_tex().getText().toString(), getPassword().getText().toString(), this);
    }

    @Override
    public void onClick(View v) {
        if(getForgot_password().getId() == v.getId()) onForgotPasswordClick(v);
        else if(getSign_in().getId() == v.getId()) onSignInClick(v);
    }


    @Override
    public void onLoginSuccess(UserLoginModel userLoginModel) {
        getHighCourtLoader().stop();
        if(userLoginModel != null && userLoginModel.is_success()){
            if(UserHelper.login(userLoginModel)){
                ((HighCourtActivity) getContext()).startActivity(new Intent(getContext(), DashboardActivity.class));
                ((HighCourtActivity) getContext()).finish();
            }
        }else {
            Toast.makeText(getContext(), userLoginModel.getError(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onLoginError(Throwable t){
        getHighCourtLoader().stop();
        ToastHelper.loginCreditionalFailur(getContext());
    }

    @Override
    public void onLoginFailure(UserLoginModel userLoginModel) {
        getHighCourtLoader().stop();
        ToastHelper.loginCreditionalFailur(getContext());
    }
}
