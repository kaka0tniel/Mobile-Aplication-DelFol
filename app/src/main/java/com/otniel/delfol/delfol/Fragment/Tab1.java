package com.otniel.delfol.delfol.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import com.otniel.delfol.delfol.Adapter.pertandinganAdapter;
import com.otniel.delfol.delfol.Model.hasilPertandingan;
import com.otniel.delfol.delfol.R;

/**
 * Created by Otniel on 5/12/2018.
 */
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
public class Tab1 extends Fragment {
    private RecyclerView recyclerView;
    private pertandinganAdapter adapter;
    private ArrayList<hasilPertandingan> hasilPertandingans;
    public Tab1() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        return inflater.inflate(R.layout.fragment_tab1, container, false);
        //addData();

        //recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view);

        //adapter = new pertandinganAdapter(hasilPertandingans);

        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        //recyclerView.setLayoutManager(layoutManager);

        //recyclerView.setAdapter(adapter);
    }


}
