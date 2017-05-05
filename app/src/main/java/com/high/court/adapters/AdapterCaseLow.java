package com.high.court.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.high.court.HighCourtApplication;
import com.high.court.R;
import com.high.court.http.models.CaseLawModel;

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
        if(viewHolder.case_law_below!= null) viewHolder.case_law_below.setText(getCaseLawList().get(i).getDiscription());
    }

    @Override
    public int getItemCount() {
        if(getCaseLawList() != null && getCaseLawList().size() > 0) return getCaseLawList().size();
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView case_law_description, case_law_below;

        public ViewHolder(View itemView){
            super(itemView);
            case_law_description = (TextView) itemView.findViewById(R.id.case_law_description);
            case_law_below = (TextView) itemView.findViewById(R.id.case_law_below);
        }
    }

    public List<CaseLawModel.CaseLaw> getCaseLawList() {
        if(caseLawList == null && HighCourtApplication.getCaseLawModel() != null) caseLawList = HighCourtApplication.getCaseLawModel().getCaseLawList();
        return caseLawList;
    }

    public void setCaseLawList(List<CaseLawModel.CaseLaw> caseLawList) {
        this.caseLawList = caseLawList;
    }

}

