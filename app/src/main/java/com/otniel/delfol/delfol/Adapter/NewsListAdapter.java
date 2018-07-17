package com.otniel.delfol.delfol.Adapter;

/**
 * Created by Otniel on 5/20/2018.
 */
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.otniel.delfol.delfol.Model.News;
import com.otniel.delfol.delfol.R;

import java.util.ArrayList;

public class NewsListAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<News> newsList;

    public NewsListAdapter(Context context, int layout, ArrayList<News> newsList) {
        this.context = context;
        this.layout = layout;
        this.newsList = newsList;
    }

    @Override
    public int getCount() {
        return newsList.size();
    }

    @Override
    public Object getItem(int position) {
        return newsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtTitle, txtContent;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtTitle = (TextView) row.findViewById(R.id.txtTitle);
            holder.txtContent = (TextView) row.findViewById(R.id.txtContent);
            holder.imageView = (ImageView) row.findViewById(R.id.imgNews);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        News news = newsList.get(position);

        holder.txtTitle.setText(news.getTitle());
        holder.txtContent.setText(news.getContent());

        byte[] newsImage = news.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(newsImage, 0, newsImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
