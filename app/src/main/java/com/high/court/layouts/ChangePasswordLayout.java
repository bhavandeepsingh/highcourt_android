package com.high.court.layouts;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.high.court.R;
import com.high.court.helpers.NetworkHelper;
import com.high.court.helpers.ToastHelper;
import com.high.court.http.models.http_interface.ChangePasswordModel;
import com.high.court.http.models.http_interface.HighCourtChangePasswordInterface;

/**
 * Created by Akshit on 27/04/2017.
 */

public class ChangePasswordLayout extends HighCourtMainLinearLayout implements View.OnClickListener, HighCourtChangePasswordInterface {

    EditText old_password, new_password, confirm_password;

    TextView change_password;

    public ChangePasswordLayout(@NonNull Context context) {
        super(context);
    }

    public ChangePasswordLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ChangePasswordLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        init();
    }

    void init(){
        setChange_password((TextView) findViewById(R.id.loginbtn));
        setOld_password((EditText) findViewById(R.id.old_licencenum_edit));
        setNew_password((EditText) findViewById(R.id.new_password_edit));
        setConfirm_password((EditText) findViewById(R.id.con_password_edit));
    }

    public EditText getOld_password() {
        return old_password;
    }

    public void setOld_password(EditText old_password) {
        this.old_password = old_password;
    }

    public EditText getNew_password() {
        return new_password;
    }

    public void setNew_password(EditText new_password) {
        this.new_password = new_password;
    }

    public EditText getConfirm_password() {
        return confirm_password;
    }

    public void setConfirm_password(EditText confirm_password) {
        this.confirm_password = confirm_password;
    }

    public TextView getChange_password() {
        return change_password;
    }

    public void setChange_password(TextView change_password) {
        if(change_password != null) change_password.setOnClickListener(this);
        this.change_password = change_password;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == getChange_password().getId()) onClickChangePassword(v);
    }

    public void onClickChangePassword(View v) {

        if(!NetworkHelper.state()){
            ToastHelper.showNoNetwork(getContext());
            return;
        }

        if(getOld_password().getText().length() <= 0){
            ToastHelper.showPleaseFillOldPassword(getContext());
            return;
        }

        if(getNew_password().getText().length() <= 0){
            ToastHelper.showPleaseFillNewPassword(getContext());
            return;
        }

        if(getConfirm_password().getText().length() <= 0){
            ToastHelper.showPleaseFillConfirmPassword(getContext());
            return;
        }

        if(!getNew_password().getText().toString().equals(getConfirm_password().getText().toString())){
            ToastHelper.showNewPasswordAreNotSame(getContext());
            return;
        }

        getHighCourtLoader().start();

        ChangePasswordModel.changePassword(getOld_password().getText().toString(), getNew_password().getText().toString(), this);
    }

    @Override
    public void onPasswordChangeSuccess(ChangePasswordModel changePasswordModel) {
        getHighCourtLoader().stop();
        ToastHelper.showPasswordChnageSuccess(getContext());
        getHighCourtActivity().finish();
    }

    @Override
    public void onPasswordChangeError(Throwable t) {
        getHighCourtLoader().stop();
        ToastHelper.showPasswordChnageFailur(getContext());
    }

    @Override
    public void onPasswordChangeFailure(ChangePasswordModel changePasswordModel) {
        getHighCourtLoader().stop();
        ToastHelper.showToast(changePasswordModel.getError(), getContext());
    }

}
