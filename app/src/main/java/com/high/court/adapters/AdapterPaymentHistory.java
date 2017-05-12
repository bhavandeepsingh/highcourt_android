package com.high.court.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.HighCourtActivity;
import com.high.court.http.models.PaymentsModel;

import java.util.ArrayList;
import java.util.List;


public class AdapterPaymentHistory extends RecyclerView.Adapter<AdapterPaymentHistory.ViewHolder> {

    Context context;

    public static int PAYMENT_TYPE_SUBSCRIPTION = 1;
    public static int PAYMENT_TYPE_WELFARE = 2;
    int list_type;

    public AdapterPaymentHistory(Context ctx, int type) {
        super();
        this.context = ctx;
        list_type = type;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_display_board, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if(viewHolder.payment_from_to != null) viewHolder.payment_from_to.setText(getSubscriptionList().get(i).getFromToString());
        if(viewHolder.payment_amount != null) viewHolder.payment_amount.setText("Rs. " + String.valueOf(getSubscriptionList().get(i).getAmount()));
    }

    @Override
    public int getItemCount() {
        int size = getSubscriptionList().size();
        if(size <= 0) showNoRecords();
        else hideNoRecords();
        return size;
    }

    private void hideNoRecords() {
        if(getNoRecordsFounds() != null){
            getNoRecordsFounds().setVisibility(View.GONE);
        }
    }

    private void showNoRecords() {
        if(getNoRecordsFounds() != null){
            getNoRecordsFounds().setVisibility(View.VISIBLE);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView payment_from_to, payment_amount;
        public ViewHolder(View itemView) {
            super(itemView);
            payment_from_to = (TextView) itemView.findViewById(R.id.payment_from_to);
            payment_amount = (TextView) itemView.findViewById(R.id.payment_amount);
        }
    }

    public List<PaymentsModel.Subscription.PaymentLog> getSubscriptionList(){
        PaymentsModel paymentsModel = HighCourtApplication.getPaymentsModel();
        if(list_type == PAYMENT_TYPE_SUBSCRIPTION && paymentsModel != null && paymentsModel.getSubscription() != null && paymentsModel.getSubscription().getPaymentLog() != null)
            return paymentsModel.getSubscription().getPaymentLog();
        else if(list_type == PAYMENT_TYPE_SUBSCRIPTION && paymentsModel != null && paymentsModel.getWelfair() != null && paymentsModel.getWelfair().getPaymentLog() != null)
            return paymentsModel.getWelfair().getPaymentLog();
        else return new ArrayList<>();
    }

    public RelativeLayout getNoRecordsFounds(){
        return  (RelativeLayout) ((HighCourtActivity) context).findViewById(R.id.no_records_found);
    }

}

