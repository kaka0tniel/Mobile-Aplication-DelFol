package com.otniel.delfol.delfol.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.otniel.delfol.delfol.Model.modelBerita;
import com.otniel.delfol.delfol.R;
import com.otniel.delfol.delfol.beritaActivity;

import java.util.List;

/**
 * Created by Otniel on 5/22/2018.
 */

public class beritaAdapter extends RecyclerView.Adapter<beritaAdapter.HolderData>{
    private List<modelBerita> mList ;
    private Context ctx;


    public  beritaAdapter (Context ctx, List<modelBerita> mList)
    {
        this.ctx = ctx;
        this.mList = mList;
    }

    @Override
    public HolderData onCreateViewHolder(ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlist,parent, false);
        HolderData holder = new HolderData(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(HolderData holder, int position) {
        modelBerita dm = mList.get(position);
        holder.title.setText(dm.getTitle());
        holder.content.setText(dm.getContent());
        holder.dm = dm;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    class HolderData extends  RecyclerView.ViewHolder{
        TextView title, content;
        modelBerita dm;
        public HolderData (View v)
        {
            super(v);

            title  = (TextView) v.findViewById(R.id.tvNama);
            content = (TextView) v.findViewById(R.id.tvUsia);

            v.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent goInput = new Intent(ctx,beritaActivity.class);
                    goInput.putExtra("id", dm.getId_news());
                    goInput.putExtra("title", dm.getTitle());
                    goInput.putExtra("content", dm.getContent());

                    ctx.startActivity(goInput);
                }
            });
        }
    }
}
