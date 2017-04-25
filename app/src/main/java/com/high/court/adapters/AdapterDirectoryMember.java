package com.high.court.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.high.court.R;
import com.high.court.activities.ExicutiveMemberDetail;
import com.high.court.activities.MemberDirectoryActivity;
import com.high.court.activities.ProfileActivity;
import com.high.court.activities.ProfileActivity2;


public class AdapterDirectoryMember extends RecyclerView.Adapter<AdapterDirectoryMember.ViewHolder> {
    Context context;
    String[] get_judgesnamelist;
    String[] get_courtroomlist;

    public AdapterDirectoryMember(Context ctx, String[] judgesnamelist, String[] courtroomlist) {
        super();
        get_judgesnamelist = judgesnamelist;
        get_courtroomlist = courtroomlist;

        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_directory_memm, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {



    }

    @Override
    public int getItemCount() {
       // return get_ratelist_title.length;
        return 22;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
       // TextView title, title_val;

        public ViewHolder(View itemView) {
            super(itemView);
//            title = (TextView) itemView.findViewById(R.id.title);
//            title_val = (TextView) itemView.findViewById(R.id.title_val);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            Intent intent = new Intent(context, ProfileActivity2.class);
            context.startActivity(intent);
                }
            });



        }
    }

}

