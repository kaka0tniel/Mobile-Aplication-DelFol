package com.otniel.delfol.delfol.Tab;

/**
 * Created by Otniel on 5/12/2018.
 */


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;

import com.otniel.delfol.delfol.Fragment.HomeFragment;
import com.otniel.delfol.delfol.Fragment.Tab1;
import com.otniel.delfol.delfol.Fragment.Tab2;
import com.otniel.delfol.delfol.Fragment.Tab3;
import com.otniel.delfol.delfol.R;


/**
 * Created by Ujang Wahyu on 18/08/2016.
 */

public class MyAdapter extends FragmentPagerAdapter {
    private Context mContext;
    private String[] titles ={"A","B","C","D"};
    int[] icon = new int[]{R.drawable.icon_stadium,R.drawable.icon_news,R.drawable.icon_trophy,R.drawable.icon_favorite};
    private int heightIcon;

    public MyAdapter(FragmentManager fm, Context c){
        super(fm);
        mContext = c;
        double scale = c.getResources().getDisplayMetrics().density;
        heightIcon=(int)(24*scale+0.5f);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment frag= null;

        if(position ==0){
            frag = new HomeFragment();
        }else if(position == 1){
            frag = new Tab1();
        }else if(position == 2){
            frag = new Tab2();
        }else if(position == 3){
            frag = new Tab3();
        }

        Bundle b = new Bundle();
        b.putInt("position", position);
        frag.setArguments(b);
        return frag;
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    public CharSequence getPageTitle(int position){
        Drawable d = mContext.getResources().getDrawable(icon[position]);
        d.setBounds(0,0,heightIcon,heightIcon);
        ImageSpan is = new ImageSpan(d);

        SpannableString sp = new SpannableString(" ");
        sp.setSpan(is,0,sp.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        return sp;
    }

}