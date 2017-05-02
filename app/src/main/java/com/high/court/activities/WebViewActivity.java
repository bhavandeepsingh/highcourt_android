package com.high.court.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.webkit.WebView;

import com.high.court.R;

public class WebViewActivity extends HighCourtActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        String geturl = getIntent().getStringExtra("url");
        WebView mywebview = (WebView) findViewById(R.id.webView1);
        mywebview.loadUrl(geturl);


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }


}
