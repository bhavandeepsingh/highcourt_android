package com.high.court.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.CCAvenue;
import com.high.court.adapters.AdapterPaymentHistory;
import com.high.court.helpers.DateHelper;
import com.high.court.http.models.PaymentsModel;
import com.high.court.utility.AvenuesParams;


public class WelfareFund_Frag extends Fragment {

    View welfare_view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        welfare_view = inflater.inflate(R.layout.welfarefund_frag, container, false);
        RecyclerView recyclerView = (RecyclerView) welfare_view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(getActivity(), 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        AdapterPaymentHistory adapter = new AdapterPaymentHistory(getActivity(), AdapterPaymentHistory.PAYMENT_TYPE_WELFARE);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);

        if(getPaymentsModel() != null && getPaymentsModel().getTotal_amount() > 0){
            getWelfarePaymentDueContainer().setVisibility(View.VISIBLE);
            setUpPaymentDues();
        }else{
            getWelfarePaymentDueContainer().setVisibility(View.GONE);
        }

        return welfare_view;
    }

    private void setUpPaymentDues() {

        TextView subscription_year = (TextView) getWelfare_view().findViewById(R.id.subscription_year);
        subscription_year.setText(DateHelper.formateDateFromstring("yyyy-mm-dd", "yyyy", getPaymentsModel().getPending_to()));

        TextView pay_due_from = (TextView) getWelfare_view().findViewById(R.id.pay_due_from);
        pay_due_from.setText("From " + DateHelper.formateDateFromstring("yyyy-MM-dd", "MMM dd, yyyy", getPaymentsModel().getPending_from()));

        TextView pay_due_to = (TextView) getWelfare_view().findViewById(R.id.pay_due_to);
        pay_due_to.setText("To " +DateHelper.formateDateFromstring("yyyy-MM-dd", "MMM dd, yyyy", getPaymentsModel().getPending_to()));

        TextView month_amount_pending_text = (TextView) getWelfare_view().findViewById(R.id.year_amount_pending_text);
        month_amount_pending_text.setText("Rs. " + getPaymentsModel().getAmount() + " per year * " + getPaymentsModel().getNumber_count());

        TextView month_amount_total = (TextView) getWelfare_view().findViewById(R.id.year_amount_total);
        month_amount_total.setText("Rs. " + getPaymentsModel().getAmount());

        TextView subscription_total_year = (TextView) getWelfare_view().findViewById(R.id.subscription_total_year);
        subscription_total_year.setText("Rs. " + getPaymentsModel().getTotal_amount());

        TextView payment_due_row_text = (TextView) getWelfare_view().findViewById(R.id.payment_due_row_text);
        payment_due_row_text.setText(getPaymentsModel().getFromToString());

        getWelfare_view().findViewById(R.id.welfare_payment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CCAvenue.class);
                intent.putExtra(AvenuesParams.AMOUNT, String.valueOf(getPaymentsModel().getTotal_amount()));
                intent.putExtra(AvenuesParams.PAYMENT_TYPE, 2);
                getActivity().startActivity(intent);
            }
        });

    }

    public PaymentsModel.Subscription getPaymentsModel(){
        if(HighCourtApplication.getPaymentsModel() != null && HighCourtApplication.getPaymentsModel().getWelfair() != null)
            return HighCourtApplication.getPaymentsModel().getWelfair();
        return null;
    }


    LinearLayout getWelfarePaymentDueContainer(){
        return (LinearLayout) getWelfare_view().findViewById(R.id.welfare_payment_due_container);
    }

    public View getWelfare_view() {
        return welfare_view;
    }
}
