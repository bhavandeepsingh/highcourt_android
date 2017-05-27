package com.high.court.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import com.google.gson.Gson;
import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.helpers.UserHelper;
import com.high.court.http.models.PaymentsModel;
import com.high.court.utility.AvenuesParams;
import com.high.court.utility.Constants;
import com.high.court.utility.ServiceUtility;

import org.apache.http.util.EncodingUtils;

/**
 * Created by bhavan on 5/8/17.
 */

public class CCAvenue extends HighCourtActivity{

    private ProgressDialog dialog;
    Intent mainIntent;
    String html, encVal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ccavenue_web_activity);
        mainIntent = getIntent();

        // Calling async task to get display content
        new RenderView().execute();
    }

    /**
     * Async task class to get json by making HTTP call
     * */
    private class RenderView extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Showing progress dialog
            dialog = new ProgressDialog(CCAvenue.this);
            dialog.setMessage("Please wait...");
            dialog.setCancelable(false);
           // dialog.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
            if (dialog.isShowing())
                dialog.dismiss();

            @SuppressWarnings("unused")
            class MyJavaScriptInterface
            {
                @JavascriptInterface
                public void processHTML(String html)
                {
                    html = html.replace("<head></head><body>", "").replace("</body>", "");
                    // process the html as needed by the app

                    CCAvenue.this.html = html;
                    if(dialog.isShowing()) dialog.cancel();

                    PaymentsModel paymentsModel = new Gson().fromJson(html, PaymentsModel.class);
                    if(paymentsModel != null && paymentsModel.getStatus() == AvenuesParams.SUCCESS){
                        HighCourtApplication.setPaymentsModel(paymentsModel);
                        HighCourtApplication.setPaymentRecreateStatus(true);
                        showToast("Payment successfully done.");
                    }else{
                        showToast("Some error occurs in payment please try again.");
                    }
                    CCAvenue.this.finish();
                }
            }

            final WebView webview = (WebView) findViewById(R.id.webview);
            webview.getSettings().setJavaScriptEnabled(true);
            webview.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
            webview.setWebViewClient(new WebViewClient(){
                @Override
                public void onPageFinished(WebView view, String url) {
                    if(dialog.isShowing()) dialog.cancel();
                    super.onPageFinished(webview, url);

                    if(url.indexOf("/success") != -1 || url.indexOf("/payment/cancel") != -1){
                        webview.setVisibility(View.GONE);
                        webview.loadUrl("javascript:window.HTMLOUT.processHTML(document.getElementsByTagName('html')[0].innerHTML);");
                        if(!dialog.isShowing()) dialog.show();
                    }
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    if(dialog.isShowing()) dialog.cancel();
                    Toast.makeText(getApplicationContext(), "Oh no! " + description, Toast.LENGTH_SHORT).show();
                }
            });

            /* An instance of this class will be registered as a JavaScript interface */

            StringBuffer params = new StringBuffer();

            params.append(ServiceUtility.addToPostParams(AvenuesParams.USER_ID, String.valueOf(UserHelper.getLoginId())));
            params.append(ServiceUtility.addToPostParams(AvenuesParams.AMOUNT, mainIntent.getStringExtra(AvenuesParams.AMOUNT)));
            params.append(ServiceUtility.addToPostParams(AvenuesParams.PAYMENT_TYPE, mainIntent.getStringExtra(AvenuesParams.PAYMENT_TYPE)));

            String vPostParams = params.substring(0,params.length()-1);
            try {
                dialog.show();
                webview.postUrl(Constants.TRANS_URL, EncodingUtils.getBytes(vPostParams, "UTF-8"));
            } catch (Exception e) {
                showToast("Exception occured while opening webview.");
            }
        }
    }

    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
