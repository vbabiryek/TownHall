package com.example.vivianbabiryekulumba.townhall.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.vivianbabiryekulumba.townhall.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class PetitionListFrag extends Fragment {

    private static final String TAG = "PetitionListFrag";

    public PetitionListFrag() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_petition_list, container, false);
    }

}
