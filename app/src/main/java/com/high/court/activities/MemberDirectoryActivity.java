package com.high.court.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.ListViewCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.high.court.R;
import com.high.court.adapters.AdapterDirectoryMember;
import com.high.court.adapters.AdapterHonbleHudges;
import com.high.court.helpers.HighCourtLoader;
import com.high.court.http.models.ProfileModel;
import com.high.court.http.models.http_interface.MemberInterface;
import com.high.court.http.models.http_request.ExcecutiveMemberModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnEditorAction;
import butterknife.OnTextChanged;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class MemberDirectoryActivity extends HighCourtActivity implements MemberInterface{

    Context context = MemberDirectoryActivity.this;
    private int totalItemCount;
    private int pastVisiblesItems;
    private boolean loadingNextPage = false;
    private int visibleItemCount;
    int page_no = 2;
    AdapterDirectoryMember adapterDirectoryMember;

    HighCourtLoader highCourtLoader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_directory);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Member Directory");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if (dy > 0) //check for scroll down
                {
                    visibleItemCount = llm.getChildCount();
                    totalItemCount = llm.getItemCount();
                    pastVisiblesItems = llm.findFirstVisibleItemPosition();

                    if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                        if (!loadingNextPage) {
                            ExcecutiveMemberModel.getMembersList(MemberDirectoryActivity.this, makeRequest(), page_no, false);
                            loadingNextPage = true;
                        }
                    }

                }
            }
        });
        adapterDirectoryMember = new AdapterDirectoryMember(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterDirectoryMember);

        if(findViewById(R.id.serach_edit_text) != null){
            ((EditText) findViewById(R.id.serach_edit_text)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    ExcecutiveMemberModel.getMembersList(MemberDirectoryActivity.this, makeRequest(), 0, true);
                }
            });
        }



    }

    private Map<String, String> makeRequest() {
        Map<String, String>  stringRequestBodyMap = new HashMap<>();
        if(findViewById(R.id.serach_edit_text) != null){
            stringRequestBodyMap.put("ProfileSearch[name]", ((EditText)findViewById(R.id.serach_edit_text)).getText().toString());
        }
        return stringRequestBodyMap;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home){
            onBackPressed();
        }

        if (id == R.id.action_notif) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onProfileMembers(ExcecutiveMemberModel excecutiveMemberModel) {
        if(excecutiveMemberModel != null && excecutiveMemberModel.getProfileModels() != null){
            getAdapterDirectoryMember().getProfileModelList().addAll(excecutiveMemberModel.getProfileModels());
            getAdapterDirectoryMember().notifyDataSetChanged();
            page_no++;
        }
        if(excecutiveMemberModel != null && excecutiveMemberModel.getPagination().isLoad_more()){
            loadingNextPage = false;
        }else{
            page_no = 2;
        }
    }

    @Override
    public void onProfileMemberSearch(ExcecutiveMemberModel excecutiveMemberModel) {
        //getHighCourtLoader().stop();
        if(excecutiveMemberModel != null) {
            getAdapterDirectoryMember().setProfileModelList(excecutiveMemberModel.getProfileModels());
            getAdapterDirectoryMember().notifyDataSetChanged();

            if(excecutiveMemberModel.getPagination().isLoad_more()){
                loadingNextPage = false;
            }else{ page_no = 2; }
        }

    }

    @Override
    public void onProfileMemberFailur(ExcecutiveMemberModel excecutiveMemberModel) {
        loadingNextPage = false;
    }

    @Override
    public void onProfileMemberFailur(Throwable t) {
        loadingNextPage = false;
    }

    public AdapterDirectoryMember getAdapterDirectoryMember() {
        return adapterDirectoryMember;
    }

    public void setAdapterDirectoryMember(AdapterDirectoryMember adapterDirectoryMember) {
        this.adapterDirectoryMember = adapterDirectoryMember;
    }

    public HighCourtLoader getHighCourtLoader() {
        if(highCourtLoader == null) highCourtLoader = HighCourtLoader.init(getApplicationContext());
        return highCourtLoader;
    }

    public void setHighCourtLoader(HighCourtLoader highCourtLoader) {
        this.highCourtLoader = highCourtLoader;
    }

}
