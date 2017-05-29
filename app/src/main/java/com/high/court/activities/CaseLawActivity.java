package com.high.court.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
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
import com.high.court.adapters.AdapterNotification;
import com.high.court.http.models.CaseLawModel;
import com.high.court.http.models.http_interface.CaseLawInterface;

import java.util.HashMap;
import java.util.Map;

public class CaseLawActivity extends HighCourtActivity implements CaseLawInterface {

    Context context = CaseLawActivity.this;
    Map<String, Integer> caselaw_read = new HashMap<>();
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

        recyclerView.addOnChildAttachStateChangeListener(new CaseLawActivity.ChildAttachListener(llm, this));

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

    private class ChildAttachListener implements RecyclerView.OnChildAttachStateChangeListener {

        LinearLayoutManager llm;
        CaseLawActivity caseLawActivity;

        public ChildAttachListener(LinearLayoutManager llm, CaseLawActivity caseLawActivity) {
            super();
            this.llm = llm;
            this.caseLawActivity = caseLawActivity;
        }

        @Override
        public void onChildViewAttachedToWindow(final View view) {
            caseLawActivity.addReadNotification(llm.findLastVisibleItemPosition());
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(2000);
                        caseLawActivity.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if(view.findViewById(R.id.caselow_backg) != null) view.findViewById(R.id.caselow_backg).
                                        setBackgroundColor(ContextCompat.getColor(context, R.color.clr_white));
                            }
                        });
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }

        @Override
        public void onChildViewDetachedFromWindow(View view) {

        }
    }

    public void addReadNotification(int index){
        if(index >= 0){
            if(getAdapterCaseLow().getCaseLawList().get(index).getIsRead() <= 0) {
                int notification_id = getAdapterNotification().getCaseLawList().get(index).getId();
                getCaseLaw_read().put(String.valueOf(index), notification_id);
                getAdapterNotification().getCaseLawList().get(index).setIsRead(1);
            }
        }
    }

    public AdapterCaseLow getAdapterNotification() {
        return adapterCaseLow;
    }

    public Map<String, Integer> getCaseLaw_read() {
        return caselaw_read;
    }





}
