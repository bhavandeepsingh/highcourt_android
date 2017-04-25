package com.high.court.tabs;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.high.court.R;
import com.high.court.adapters.AdapterPaymentHistory;
import com.high.court.adapters.AdapterSingleBench;


public class Subscription_Frag extends Fragment {


    public static String[] judgesnamelist = {
            "Satish Kumar Mittal",
            "Satish Kumar Mittal",

    };
    public static String[] courtroomlist = {
            "75/-",
            "75/-",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.subscription_frag, container, false);


        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager llm = new GridLayoutManager(getActivity(), 1);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(llm);
        AdapterPaymentHistory adapter = new AdapterPaymentHistory(getActivity(), judgesnamelist, courtroomlist);
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setAdapter(adapter);



        return view;
    }
}
