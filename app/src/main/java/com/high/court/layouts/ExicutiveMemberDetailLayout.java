package com.high.court.layouts;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.high.court.R;
import com.high.court.helpers.ImageHelper;
import com.high.court.helpers.ToastHelper;
import com.high.court.helpers.UserHelper;
import com.high.court.http.models.ProfileModel;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by admin on 4/26/2017.
 */

public class ExicutiveMemberDetailLayout extends HighCourtMainLinearLayout implements View.OnClickListener {


    CircleImageView profilePicShow;

    EditText designation_val, profile_val, barcouncil_val, barassociaation_val, landline_val, mobilenumber_val, residential_val,
            courtaddress_val, bloodgroup_val, email_id, landline_no, mobile_no, residential_adress, court_address, blood_group;

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
        if (!isInitiate()) init();
    }

    @Override
    public void init() {
        super.init();
        setProfilePicShow((CircleImageView) findViewById(R.id.quick_start_cropped_image));
        fieldsDisabled();
        getDesignation_val();
        getProfile_val();
        getBarassociaation_val();
        getBarcouncil_val();
    }

    public CircleImageView getProfilePicShow() {
        return profilePicShow;
    }

    public ExicutiveMemberDetailLayout setProfilePicShow(CircleImageView profilePicShow) {
        if (profilePicShow != null) ImageHelper.loadImage(getProfileModel().getProfile_pic(), profilePicShow);
        this.profilePicShow = profilePicShow;
        return this;
    }


    public EditText getDesignation_val() {
        if (designation_val == null) setDesignation_val((EditText) findViewById(R.id.designation_val));
        return designation_val;
    }

    public void setDesignation_val(EditText designation_val) {
        if (designation_val != null) designation_val.setText(getProfileModel().getDesignation());
        this.designation_val = designation_val;
    }

    public EditText getProfile_val() {
        if (profile_val == null) setProfile_val((EditText) findViewById(R.id.profile_val));
        return profile_val;
    }

    public void setProfile_val(EditText profile_val) {
        if (profile_val != null) profile_val.setText(getProfileModel().getProfile());
        this.profile_val = profile_val;
    }

    public EditText getBarcouncil_val() {
        if (barcouncil_val == null) setBarcouncil_val((EditText) findViewById(R.id.barcouncil_val));
        return barcouncil_val;
    }

    public void setBarcouncil_val(EditText barcouncil_val) {
        if (barcouncil_val != null) barcouncil_val.setText(getProfileModel().getBio());
        this.barcouncil_val = barcouncil_val;
    }

    public EditText getBarassociaation_val() {
        if (barassociaation_val == null)
            setBarassociaation_val((EditText) findViewById(R.id.barassociaation_val));
        return barassociaation_val;
    }

    public void setBarassociaation_val(EditText barassociaation_val) {
        if(barassociaation_val != null) barassociaation_val.setText(getProfileModel().getMembership_number());
        this.barassociaation_val = barassociaation_val;
    }

    public EditText getEmail_id() {
        if (email_id == null) setEmail_id((EditText) findViewById(R.id.email_val));
        return email_id;
    }

    public void setEmail_id(EditText email_id) {
        if (email_id != null) email_id.setText(getProfileModel().getEmail());
        this.email_id = email_id;
    }

    public EditText getLandline_val() {
        if (landline_val == null) setLandline_val((EditText) findViewById(R.id.landline_val));
        return landline_val;
    }

    public void setLandline_val(EditText landline_val) {
        this.landline_val = landline_val;
    }

    public EditText getMobilenumber_val() {
        if (mobilenumber_val == null)
            setMobilenumber_val((EditText) findViewById(R.id.mobilenumber_val));
        return mobilenumber_val;
    }

    public void setMobilenumber_val(EditText mobilenumber_val) {
        this.mobilenumber_val = mobilenumber_val;
    }

    public EditText getResidential_val() {
        if (residential_val == null)
            setResidential_val((EditText) findViewById(R.id.residential_val));
        return residential_val;
    }

    public void setResidential_val(EditText residential_val) {
        this.residential_val = residential_val;
    }

    public EditText getCourtaddress_val() {
        if (courtaddress_val == null)
            setCourtaddress_val((EditText) findViewById(R.id.courtaddress_val));
        return courtaddress_val;
    }

    public void setCourtaddress_val(EditText courtaddress_val) {
        this.courtaddress_val = courtaddress_val;
    }

    public EditText getBloodgroup_val() {
        if (bloodgroup_val == null) setBloodgroup_val((EditText) findViewById(R.id.bloodgroup_val));
        return bloodgroup_val;
    }

    public void setBloodgroup_val(EditText bloodgroup_val) {
        this.bloodgroup_val = bloodgroup_val;
    }


    public EditText getLandline_no() {
        if (landline_no == null) setLandline_no((EditText) findViewById(R.id.landline_val));
        return landline_no;
    }

    public void setLandline_no(EditText landline_no) {
        this.landline_no = landline_no;
    }

    public EditText getMobile_no() {
        if (mobile_no == null) setMobile_no((EditText) findViewById(R.id.mobilenumber_val));
        return mobile_no;
    }

    public void setMobile_no(EditText mobile_no) {
        if (mobile_no != null) mobile_no.setText(getProfileModel().getMobile());
        this.mobile_no = mobile_no;
    }

    public EditText getResidential_adress() {
        if (residential_adress == null)
            setResidential_adress((EditText) findViewById(R.id.residential_val));
        return residential_adress;
    }

    public void setResidential_adress(EditText residential_adress) {
        if (residential_adress != null)
            residential_adress.setText(getProfileModel().getResidential_address());
        this.residential_adress = residential_adress;
    }

    public EditText getCourt_address() {
        if (court_address == null) setCourt_address((EditText) findViewById(R.id.courtaddress_val));
        return court_address;
    }

    public void setCourt_address(EditText court_address) {
        if (court_address != null) court_address.setText(getProfileModel().getCourt_address());
        this.court_address = court_address;
    }

    public EditText getBlood_group() {
        if (blood_group == null) setBlood_group((EditText) findViewById(R.id.bloodgroup_val));
        return blood_group;
    }

    public void setBlood_group(EditText blood_group) {
        if (blood_group != null) blood_group.setText(getProfileModel().getBlood_group());
        this.blood_group = blood_group;
    }

    public TextView getSave_text_view() {
        if (save_text_view == null)
            setSave_text_view((TextView) findViewById(R.id.save_text_button));
        return save_text_view;
    }

    public void setSave_text_view(TextView save_text_view) {
        if (save_text_view != null) save_text_view.setOnClickListener(this);
        this.save_text_view = save_text_view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == getSave_text_view().getId()) {
            onSaveButtonClick(v);
        }
    }

    private void onSaveButtonClick(View v) {
        if (!editTextValidate(getEmail_id())) ToastHelper.showEmailNotFill(getContext());
        else if (!editTextValidate(getLandline_no())) ToastHelper.showLanlineNotFill(getContext());
        else if (!editTextValidate(getMobile_no())) ToastHelper.showMoblieNotFill(getContext());
        else if (!editTextValidate(getResidential_adress()))
            ToastHelper.showResidentialNotFill(getContext());
        else if (!editTextValidate(getCourt_address()))
            ToastHelper.showCourtAddressNotFill(getContext());
        else if (!editTextValidate(getBlood_group()))
            ToastHelper.showBloodgroupNotFill(getContext());
        fieldsDisabled();

    }

    boolean editTextValidate(EditText editText) {
        if (editText.getText().length() <= 0) return false;
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
        setProfileModel(profileModel);
        init();
    }

    public void fieldsEnabled() {

        getEmail_id().setEnabled(true);
        getLandline_val().setEnabled(true);
        getMobilenumber_val().setEnabled(true);
        getResidential_val().setEnabled(true);
        getCourtaddress_val().setEnabled(true);
        getBloodgroup_val().setEnabled(true);
    }

    public void fieldsDisabled() {

        getEmail_id().setEnabled(false);
        getLandline_val().setEnabled(false);
        getMobilenumber_val().setEnabled(false);
        getResidential_val().setEnabled(false);
        getCourtaddress_val().setEnabled(false);
        getBloodgroup_val().setEnabled(false);
    }


}