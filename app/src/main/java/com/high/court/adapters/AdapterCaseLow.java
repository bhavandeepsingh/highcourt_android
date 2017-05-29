package com.high.court.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.activities.HighCourtActivity;
import com.high.court.http.models.CaseLawModel;
import com.high.court.http.models.NotificationModel;

import java.util.List;


public class AdapterCaseLow extends RecyclerView.Adapter<AdapterCaseLow.ViewHolder> {

    Context context;
    List<CaseLawModel.CaseLaw> caseLawList;

    public AdapterCaseLow(Context ctx) {
        super();
        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_caselow, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        if(viewHolder.case_law_description != null) viewHolder.case_law_description.setText(getCaseLawList().get(i).getDiscription());
        if(viewHolder.case_law_below!= null) viewHolder.case_law_below.setText(getCaseLawList().get(i).getTitle());
        if (getCaseLawList().get(i).getIsRead() <= 0) {
            viewHolder.caselow_backg.setBackgroundColor(ContextCompat.getColor(context, R.color.clr_caselawback));
        }
    }

    @Override
    public int getItemCount() {
        int list_size = (getCaseLawList() != null && getCaseLawList().size() > 0)? getCaseLawList().size() : 0;
        if(list_size <= 0) showNoRecords();
        else hideNoRecords();
        return list_size;
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

        TextView case_law_description, case_law_below;
        LinearLayout caselow_backg;

        public ViewHolder(View itemView){
            super(itemView);
            case_law_description = (TextView) itemView.findViewById(R.id.case_law_description);
            case_law_below = (TextView) itemView.findViewById(R.id.case_law_below);
            caselow_backg = (LinearLayout) itemView.findViewById(R.id.caselow_backg);

        }
    }

    public List<CaseLawModel.CaseLaw> getCaseLawList() {
        if(caseLawList == null && HighCourtApplication.getCaseLawModel() != null) caseLawList = HighCourtApplication.getCaseLawModel().getCaseLawList();
        return caseLawList;
    }

    public void setCaseLawList(List<CaseLawModel.CaseLaw> caseLawList) {
        this.caseLawList = caseLawList;
    }

    public RelativeLayout getNoRecordsFounds(){
        return  (RelativeLayout) ((HighCourtActivity) context).findViewById(R.id.no_records_found);
    }



}

