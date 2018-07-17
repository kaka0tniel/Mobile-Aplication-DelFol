package com.otniel.delfol.delfol.Adapter;

/**
 * Created by Otniel on 5/13/2018.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.nostra13.universalimageloader.cache.memory.impl.WeakMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;

import java.util.ArrayList;

import com.otniel.delfol.delfol.Model.hasilPertandingan;
import com.otniel.delfol.delfol.R;

import java.util.ArrayList;
public class pertandinganAdapter extends ArrayAdapter<hasilPertandingan>{
    private static final String TAG = "Match Adapter";

    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    static class ViewHolder {
        TextView liga1;
        TextView score;
        TextView liga2;
        ImageView imgURL1;
        ImageView imgUR2;
    }


    public pertandinganAdapter(Context context, int resource, ArrayList<hasilPertandingan> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        setupImageLoader();

        String liga1  = getItem(position).getLiga1();
        String score = getItem(position).getScore();
        String liga2 = getItem(position).getLiga2();
        String imgURL1 = getItem(position).getImgURL1();
        String imgURL2 = getItem(position).getImgURL2();



        final View result;

        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource,parent,false);
            holder = new ViewHolder();
            holder.liga1 = (TextView) convertView.findViewById(R.id.textView1);
            holder.score = (TextView) convertView.findViewById(R.id.textView2);
            holder.liga2 = (TextView) convertView.findViewById(R.id.textView3);
            holder.imgURL1 = (ImageView)convertView.findViewById(R.id.itemImage1);
            holder.imgUR2 = (ImageView)convertView.findViewById(R.id.itemImage2);

            result = convertView;

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
            result = convertView;
        }

        lastPosition = position;

        ImageLoader imageLoader = ImageLoader.getInstance();

        int defaultImage =mContext.getResources().getIdentifier("@drawable/image_failed",null,mContext.getPackageName());


        DisplayImageOptions options = new DisplayImageOptions.Builder().cacheInMemory(true)
                .cacheOnDisc(true).resetViewBeforeLoading(true)
                .showImageForEmptyUri(defaultImage)
                .showImageOnFail(defaultImage)
                .showImageOnLoading(defaultImage).build();

        imageLoader.displayImage(imgURL1,holder.imgURL1,options);
        imageLoader.displayImage(imgURL2,holder.imgUR2,options);

        holder.liga1.setText(liga1);
        holder.score.setText(score);
        holder.liga2.setText(liga2);


        return  convertView;
    }

    private void setupImageLoader(){
        DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
                .cacheOnDisk(true).resetViewBeforeLoading(true)
                .imageScaleType(ImageScaleType.EXACTLY)
                .displayer(new FadeInBitmapDisplayer(300)).build();
        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(mContext)
                .defaultDisplayImageOptions(defaultOptions)
                .memoryCache(new WeakMemoryCache())
                .discCacheSize(100 * 1024).build();

        ImageLoader.getInstance().init(config);
    }
}
