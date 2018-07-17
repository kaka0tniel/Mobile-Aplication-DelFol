package com.otniel.delfol.delfol.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.otniel.delfol.delfol.R;

/**
 * Created by Otniel on 5/12/2018.
 */

public class Tab2 extends Fragment{
    public Tab2() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_tab2, container, false);
    }
}
