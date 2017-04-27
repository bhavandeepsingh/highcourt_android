package com.high.court.layouts;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.high.court.R;
import com.high.court.activities.DashboardActivity;
import com.high.court.activities.HighCourtActivity;
import com.high.court.helpers.HighCourtLoader;
import com.high.court.helpers.NetworkHelper;
import com.high.court.helpers.ToastHelper;
import com.high.court.http.models.http_interface.ResetMyPasswordInterface;
import com.high.court.http.models.http_request.ResetMyPassword;

/**
 * Created by admin on 4/26/2017.
 */

public class ResetMyPasswordLayout extends HighCourtMainLinearLayout implements View.OnClickListener, ResetMyPasswordInterface{

    EditText licence_no;
    TextView reset_my_number;

    public ResetMyPasswordLayout(Context context) {
        super(context);
    }

    public ResetMyPasswordLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ResetMyPasswordLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
    }

    private void init() {
        setLicence_no((EditText) findViewById(R.id.licencenum_edit))
        .setReset_my_number((TextView) findViewById(R.id.loginbtn));
    }

    public EditText getLicence_no() {
        return licence_no;
    }

    public ResetMyPasswordLayout setLicence_no(EditText licence_no) {
        this.licence_no = licence_no;
        return this;
    }

    public TextView getReset_my_number() {
        return reset_my_number;
    }

    public ResetMyPasswordLayout setReset_my_number(TextView reset_my_number) {
        if(reset_my_number != null) reset_my_number.setOnClickListener(this);
        this.reset_my_number = reset_my_number;
        return this;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == getReset_my_number().getId()) onClickMyResetPassword(v);
    }

    public void onClickMyResetPassword(View v) {
        if(!NetworkHelper.state()) ToastHelper.showNoNetwork(getContext());
        if(getLicence_no().getText().length() <= 0) ToastHelper.pleaseFillLicenece(getContext());
        getHighCourtLoader().start();
        ResetMyPassword.resetPassword(getLicence_no().getText().toString(), this);
    }

    @Override
    public void onResetPasswordSuccess(ResetMyPassword resetMyPassword) {
        if(resetMyPassword.is_success()){
            ToastHelper.passwordResetSuccessfully(getContext());
            getHighCourtActivity().startActivity(new Intent(getContext(), DashboardActivity.class));
            getHighCourtActivity().finish();
        }else{
            ToastHelper.showToast(resetMyPassword.getLicense_no(), getContext());
        }
        getHighCourtLoader().stop();
    }

    @Override
    public void onResetPasswordError(Throwable t)
    {
        getHighCourtLoader().stop();
        ToastHelper.showLogoutFailuer(getContext());
    }

    @Override
    public void onResetPasswordFailure(ResetMyPassword resetMyPassword) {
        getHighCourtLoader().stop();
        ToastHelper.showLogoutFailuer(getContext());
    }

}
