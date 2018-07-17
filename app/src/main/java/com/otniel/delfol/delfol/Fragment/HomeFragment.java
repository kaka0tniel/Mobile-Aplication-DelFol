package com.otniel.delfol.delfol.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otniel.delfol.delfol.Adapter.pertandinganAdapter;
import com.otniel.delfol.delfol.Model.hasilPertandingan;
import com.otniel.delfol.delfol.R;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import static com.nostra13.universalimageloader.core.ImageLoader.TAG;
public class HomeFragment extends Fragment {
    private ListView mListView;



    public HomeFragment() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_home, container, false);
        mListView = (ListView)view.findViewById(R.id.listview);


        hasilPertandingan match1 = new hasilPertandingan("Real Madrid ", "0 - 2","Arsenal","drawable://" + R.drawable.liga1,"drawable://" + R.drawable.liga2);
        hasilPertandingan match2 = new hasilPertandingan("Barcelona", "1 - 2","Real Madrid","drawable://" + R.drawable.liga3,"drawable://" + R.drawable.liga4);
        hasilPertandingan match3 = new hasilPertandingan("Chelesea", "2 - 2","Arsenal","drawable://" + R.drawable.liga2,"drawable://" + R.drawable.liga5);
        hasilPertandingan match4 = new hasilPertandingan("Arsenal", "1 - 4","Barcelona","drawable://" + R.drawable.liga3,"drawable://" + R.drawable.liga2);
        hasilPertandingan match5 = new hasilPertandingan("Manchester", "1 - 4","Chelsea","drawable://" + R.drawable.liga4,"drawable://" + R.drawable.liga1);
        hasilPertandingan match6 = new hasilPertandingan("Barcelona", "1 - 4","Real Madrid","drawable://" + R.drawable.liga1,"drawable://" + R.drawable.liga3);
        hasilPertandingan match7 = new hasilPertandingan("Juventus", "1 - 4","Arsenal","drawable://" + R.drawable.liga2,"drawable://" + R.drawable.liga4);
        hasilPertandingan match8 = new hasilPertandingan("Arsenal", "1 - 4","Juventus","drawable://" + R.drawable.liga4,"drawable://" + R.drawable.liga1);
        hasilPertandingan match9 = new hasilPertandingan("Barcelona", "1 - 4","Chelsea","drawable://" + R.drawable.liga3,"drawable://" + R.drawable.liga5);



        ArrayList<hasilPertandingan> peoplelist = new ArrayList<>();
        peoplelist.add(match1);
        peoplelist.add(match2);
        peoplelist.add(match3);
        peoplelist.add(match4);
        peoplelist.add(match5);
        peoplelist.add(match6);
        peoplelist.add(match7);
        peoplelist.add(match8);
        peoplelist.add(match9);
        peoplelist.add(match1);
        peoplelist.add(match2);
        peoplelist.add(match3);
        peoplelist.add(match4);
        peoplelist.add(match5);
        peoplelist.add(match6);
        peoplelist.add(match7);
        peoplelist.add(match8);
        peoplelist.add(match9);


        pertandinganAdapter adapter = new pertandinganAdapter(getActivity(),R.layout.adapter_layout_pertandingan,peoplelist);
        mListView.setAdapter(adapter);


        return view;
    }

}
