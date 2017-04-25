package com.high.court.adapters;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.high.court.R;


public class AdapterRoster extends RecyclerView.Adapter<AdapterRoster.ViewHolder> {
    Context context;
    String[] get_judgesnamelist;
    String[] get_courtroomlist;

    public AdapterRoster(Context ctx, String[] judgesnamelist, String[] courtroomlist) {
        super();
        get_judgesnamelist = judgesnamelist;
        get_courtroomlist = courtroomlist;

        this.context = ctx;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_roster, viewGroup, false);
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
                    Toast.makeText(context, "", Toast.LENGTH_SHORT).show();

                    // Create custom dialog object
                    final Dialog dialog = new Dialog(context);
                    // Include dialog.xml file
                    dialog.setContentView(R.layout.dialog_roster);
                    // Set dialog title
                    dialog.setTitle("Custom Dialog");



                    dialog.show();




                }
            });
            
        }
    }

}

