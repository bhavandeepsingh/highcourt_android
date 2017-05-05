package com.high.court.activities;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.adapters.AdapterCaseLow;
import com.high.court.http.models.CaseLawModel;
import com.high.court.http.models.http_interface.CaseLawInterface;
import com.high.court.http.models.http_request.ExcecutiveMemberModel;

public class CaseLawActivity extends AppCompatActivity implements CaseLawInterface {

    Context context = CaseLawActivity.this;

    CaseLawModel caseLawModel;

    private int totalItemCount;
    private int pastVisiblesItems;
    private boolean loadingNextPage = false;
    private int visibleItemCount;
    int page_no = 2;

    AdapterCaseLow adapterCaseLow;

    public RelativeLayout loader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case_low);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle("Case Law");

        loader = (RelativeLayout)findViewById(R.id.loader);

        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(context, 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        adapterCaseLow = new AdapterCaseLow(context);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapterCaseLow);
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
                        if (!loadingNextPage && getCaseLawModel().getPagination().isLoad_more()) {
                            CaseLawModel.getCaseLaw(CaseLawActivity.this, page_no);
                            loadingNextPage = true;
                            loader.setVisibility(View.VISIBLE);
                        }
                    }

                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id==android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    public CaseLawModel getCaseLawModel() {
        if(caseLawModel == null) caseLawModel = HighCourtApplication.getCaseLawModel();
        return caseLawModel;
    }

    public void setCaseLawModel(CaseLawModel caseLawModel) {
        this.caseLawModel = caseLawModel;
    }

    public AdapterCaseLow getAdapterCaseLow() {
        return adapterCaseLow;
    }

    @Override
    public void onCaseLawSuccess(CaseLawModel caseLawModel) {
        if(caseLawModel != null) {
            setCaseLawModel(caseLawModel);
            getAdapterCaseLow().getCaseLawList().addAll(caseLawModel.getCaseLawList());
            getAdapterCaseLow().notifyDataSetChanged();
            page_no++;
            loadingNextPage = false;
        }
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onCaseLawFailur(Throwable t) {
        loader.setVisibility(View.GONE);
    }

}
