package com.high.court.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.adapters.AdapterRoster;
import com.high.court.http.models.RosterModel;
import com.high.court.http.models.http_interface.RosterInterface;

import java.util.HashMap;
import java.util.Map;

public class RosterActivity extends HighCourtActivity implements RosterInterface {

    Context context = RosterActivity.this;

    AdapterRoster adapterRoster;

    RosterModel rosterModel;

    private int totalItemCount;
    private int pastVisiblesItems;
    private boolean loadingNextPage = false;
    private int visibleItemCount;
    int page_no = 2;

    public RelativeLayout loader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_roster);

        loader = (RelativeLayout) findViewById(R.id.loader);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Roster");

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapterRoster = new AdapterRoster(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterRoster);
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
                        if (!loadingNextPage && getRosterModel().getPagination().isLoad_more()) {
                            RosterModel.getRoster(RosterActivity.this, makeRequest(), page_no, false);
                            loadingNextPage = true;
                            loader.setVisibility(View.VISIBLE);
                        }
                    }

                }
            }
        });

        if(findViewById(R.id.roster_search_text) != null){
            ((EditText) findViewById(R.id.roster_search_text)).addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    RosterModel.getRoster(RosterActivity.this, makeRequest(), 1, true);
                }
            });
        }

    }

    private Map<String, String> makeRequest() {
        if(((EditText) findViewById(R.id.roster_search_text)).length() > 0){
            Map<String, String> stringStringMap = new HashMap<>();
            stringStringMap.put("RosterSearch[title]", ((EditText) findViewById(R.id.roster_search_text)).getText().toString());
            return stringStringMap;
        }
        return null;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public AdapterRoster getAdapterRoster() {
        return adapterRoster;
    }

    public void setAdapterRoster(AdapterRoster adapterRoster) {
        this.adapterRoster = adapterRoster;
    }

    public RosterModel getRosterModel() {
        if(rosterModel == null) rosterModel = HighCourtApplication.getRosterModel();
        return rosterModel;
    }

    public void setRosterModel(RosterModel rosterModel) {
        this.rosterModel = rosterModel;
    }

    @Override
    public void onRosterSuccess(RosterModel rosterModel) {
        loader.setVisibility(View.GONE);
        if(rosterModel != null){
            setRosterModel(rosterModel);
            getAdapterRoster().getRosterList().addAll(rosterModel.getRosterList());
            getAdapterRoster().notifyDataSetChanged();
            page_no++;
        }

    }

    @Override
    public void onRosterSearch(RosterModel rosterModel) {
        if(rosterModel != null){
            setRosterModel(rosterModel);
            getAdapterRoster().setRosterList(rosterModel.getRosterList());
            getAdapterRoster().notifyDataSetChanged();
            page_no = 2;
        }
    }

    @Override
    public void onRosterFailur(Throwable t) {
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onRosterFailur() {
        loader.setVisibility(View.GONE);
    }
}
