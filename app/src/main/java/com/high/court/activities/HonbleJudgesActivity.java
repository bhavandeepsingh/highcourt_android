package com.high.court.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.adapters.AdapterHonbleHudges;
import com.high.court.http.models.JudgesModel;
import com.high.court.http.models.http_interface.JudgesModelInterface;
import com.high.court.http.models.http_request.ExcecutiveMemberModel;

import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;

public class HonbleJudgesActivity extends HighCourtActivity implements JudgesModelInterface {

    Context context = HonbleJudgesActivity.this;

    private int totalItemCount;
    private int pastVisiblesItems;
    private boolean loadingNextPage = false;
    private int visibleItemCount;
    int page_no = 2;
    RelativeLayout loader;

    AdapterHonbleHudges adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_honble_judges);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Hon'ble Judges");

        loader = (RelativeLayout) findViewById(R.id.loader);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapter = new AdapterHonbleHudges(context);
        recyclerView.setNestedScrollingEnabled(false);

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
                        if (!loadingNextPage && HighCourtApplication.getJudgesModel().getPagination().isLoad_more()) {
                            JudgesModel.getJudges(HonbleJudgesActivity.this, makeRequest(), page_no, false);
                            loadingNextPage = true;
                            loader.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
        recyclerView.setAdapter(adapter);

        if((EditText) (findViewById(R.id.judges_serach_text)) != null){

            ((EditText) findViewById(R.id.judges_serach_text)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    page_no = 1;
                    JudgesModel.getJudges(HonbleJudgesActivity.this, makeRequest(), page_no, true);
                }

            });
        }
    }

    private Map<String, String> makeRequest() {
        if((EditText) (findViewById(R.id.judges_serach_text)) != null && ((EditText) (findViewById(R.id.judges_serach_text))).getText().toString().length() > 0){
            Map<String, String> stringRequestBodyMap = new HashMap<>();
            stringRequestBodyMap.put("JudgesSearch[name]", ((EditText) (findViewById(R.id.judges_serach_text))).getText().toString());
            return stringRequestBodyMap;
        }
        return null;
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
    public void onJudgesSuccess(JudgesModel judgesModel) {
        loader.setVisibility(View.GONE);
        if(judgesModel != null && judgesModel.getJudgeList() != null)
        getAdapter().getJudgesList().addAll(judgesModel.getJudgeList());
        getAdapter().notifyDataSetChanged();

        if(judgesModel.getPagination() != null && judgesModel.getPagination().isLoad_more()){
            page_no++;
            loadingNextPage = true;
        }
    }

    @Override
    public void onJudgesFailur(Throwable t) {
        loadingNextPage = false;
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onJudgesSearch(JudgesModel judgesModel) {
        HighCourtApplication.setJudgesModel(judgesModel);
        getAdapter().notifyDataSetChanged();
    }

    public AdapterHonbleHudges getAdapter() {
        return adapter;
    }

}
