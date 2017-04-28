package com.high.court.layouts;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.helpers.ImageHelper;
import com.high.court.helpers.ToastHelper;
import android.widget.LinearLayout;
import com.high.court.helpers.UserHelper;
import com.high.court.http.models.ProfileModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 4/26/2017.
 */

public class ExicutiveMemberDetailLayout extends HighCourtMainLinearLayout implements View.OnClickListener{



    CircleImageView profilePicShow;

    EditText email_id, landline_no, mobile_no, residential_adress, court_address, blood_group;

    TextView save_text_view;

    ProfileModel profileModel;

    static int TYPE = 0;

    static int TYPE_MY_PRIFILE = 1;

    public ExicutiveMemberDetailLayout(Context context) {
        super(context);
    }

    public ExicutiveMemberDetailLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public ExicutiveMemberDetailLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(!isInitiate()) init();
    }

    @Override
    public void init() {
        super.init();
        setProfilePicShow((CircleImageView) findViewById(R.id.quick_start_cropped_image));
    }

    public CircleImageView getProfilePicShow() {
        return profilePicShow;
    }

    public ExicutiveMemberDetailLayout setProfilePicShow(CircleImageView profilePicShow) {
        if(profilePicShow != null) ImageHelper.loadImage(getProfileModel().getProfile_pic(), profilePicShow);
        this.profilePicShow = profilePicShow;
        return this;
    }

    public EditText getEmail_id() {
        if(email_id == null) setEmail_id((EditText) findViewById(R.id.email_val));
        return email_id;
    }

    public void setEmail_id(EditText email_id) {
        if(email_id != null) email_id.setText(getProfileModel().getEmail());
        this.email_id = email_id;
    }

    public EditText getLandline_no() {
        if(landline_no == null) setLandline_no((EditText) findViewById(R.id.landline_val));
        return landline_no;
    }

    public void setLandline_no(EditText landline_no) {
        this.landline_no = landline_no;
    }

    public EditText getMobile_no() {
        if(mobile_no == null) setMobile_no((EditText) findViewById(R.id.mobilenumber_val));
        return mobile_no;
    }

    public void setMobile_no(EditText mobile_no) {
        if(mobile_no != null) mobile_no.setText(getProfileModel().getMobile());
        this.mobile_no = mobile_no;
    }

    public EditText getResidential_adress() {
        if(residential_adress == null) setResidential_adress((EditText) findViewById(R.id.residential_val));
        return residential_adress;
    }

    public void setResidential_adress(EditText residential_adress) {
        if(residential_adress != null) residential_adress.setText(getProfileModel().getResidential_address());
        this.residential_adress = residential_adress;
    }

    public EditText getCourt_address() {
        if(court_address == null) setCourt_address((EditText) findViewById(R.id.courtaddress_val));
        return court_address;
    }

    public void setCourt_address(EditText court_address) {
        if(court_address != null) court_address.setText(getProfileModel().getCourt_address());
        this.court_address = court_address;
    }

    public EditText getBlood_group() {
        if(blood_group == null) setBlood_group((EditText) findViewById(R.id.bloodgroup_val));
        return blood_group;
    }

    public void setBlood_group(EditText blood_group) {
        if(blood_group != null) blood_group.setText(getProfileModel().getBlood_group());
        this.blood_group = blood_group;
    }

    public TextView getSave_text_view() {
        if(save_text_view == null) setSave_text_view((TextView) findViewById(R.id.save_text_button));
        return save_text_view;
    }

    public void setSave_text_view(TextView save_text_view) {
        if(save_text_view != null) save_text_view.setOnClickListener(this);
        this.save_text_view = save_text_view;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == getSave_text_view().getId()){
            onSaveButtonClick(v);
        }
    }

    private void onSaveButtonClick(View v) {
        if(!editTextValidate(getEmail_id())) ToastHelper.showEmailNotFill(getContext());
        else if(!editTextValidate(getLandline_no())) ToastHelper.showLanlineNotFill(getContext());
        else if(!editTextValidate(getMobile_no())) ToastHelper.showMoblieNotFill(getContext());
        else if(!editTextValidate(getResidential_adress())) ToastHelper.showResidentialNotFill(getContext());
        else if(!editTextValidate(getCourt_address())) ToastHelper.showCourtAddressNotFill(getContext());
        else if(!editTextValidate(getBlood_group())) ToastHelper.showBloodgroupNotFill(getContext());
    }

    boolean editTextValidate(EditText editText){
        if(editText.getText().length() <= 0)return false;
        return true;
    }

    public static int getTYPE() {
        return TYPE;
    }

    public static void setTYPE(int TYPE) {
        ExicutiveMemberDetailLayout.TYPE = TYPE;
    }

    public static int getTypeMyPrifile() {
        return TYPE_MY_PRIFILE;
    }

    public static void setTypeMyPrifile(int typeMyPrifile) {
        TYPE_MY_PRIFILE = typeMyPrifile;
    }

    public ProfileModel getProfileModel() {
        return profileModel;
    }

    public void setProfileModel(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }

    public void setProfile(ProfileModel profileModel) {
        if(profileModel != null){
            if(profileModel.getUser_id() == UserHelper.getId()) {
                setTYPE(TYPE_MY_PRIFILE);
            }
            setProfileModel(profileModel);
            init();
        }
    }
}