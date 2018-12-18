package com.example.phnf2.projetounidadefinal.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.phnf2.projetounidadefinal.R;


public class Fragment_Sobre extends Fragment {

    TextView sobre;

    public Fragment_Sobre() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment__sobre, container, false);

        sobre = view.findViewById(R.id.textViewSobre);

        sobre.setMovementMethod(LinkMovementMethod.getInstance());

        return view;
    }


}
