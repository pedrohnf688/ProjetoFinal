package com.example.phnf2.projetounidadefinal.fragment;


import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.phnf2.projetounidadefinal.R;
import com.example.phnf2.projetounidadefinal.modelo.Ordenha;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.LegendRenderer;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.List;

import static com.example.phnf2.projetounidadefinal.util.FirebaseUtil.getCurrentUserId;

/**
 * A simple {@link Fragment} subclass.
 */
@SuppressLint("ValidFragment")
public class Fragment_GraficoOrdenha extends Fragment {

    GraphView graphView;
    String id;
    String kId;
    DatabaseReference databaseOrdenha;
    FirebaseDatabase mFirebase;
    BarGraphSeries<DataPoint> graphSeriesBar;
    Query query;

    @SuppressLint("ValidFragment")
    public Fragment_GraficoOrdenha(String id, String kId) {
        this.id = id;
        this.kId = kId;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_fragment__grafico_ordenha, container, false);


        graphView = view.findViewById(R.id.GraficoOrdenha);

        mFirebase = FirebaseDatabase.getInstance();

        if(mFirebase == null) {
            mFirebase.setPersistenceEnabled(true);
             query = mFirebase.getReference("Ordenhas").child(id).orderByChild("idOrdenha").equalTo(kId);

        }else{
            query = mFirebase.getReference("Ordenhas").child(id).orderByChild("idOrdenha").equalTo(kId);
            query.keepSynced(true);
        }

        graphSeriesBar = new BarGraphSeries<>();

        graphView.addSeries(graphSeriesBar);

        graphSeriesBar.setDrawValuesOnTop(true);

        graphSeriesBar.setValuesOnTopColor(Color.RED);

        graphSeriesBar.setSpacing(40);

        graphSeriesBar.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 100);
            }
        });

        graphView.getViewport().setXAxisBoundsManual(true);
        graphView.getViewport().setMinX(0);
        graphView.getViewport().setMaxX(9);

        graphView.getViewport().setYAxisBoundsManual(true);
        graphView.getViewport().setMinY(0);
        graphView.getViewport().setMaxY(100);



        graphSeriesBar.setTitle("Variáveis da Ordenha");

        graphView.getLegendRenderer().setVisible(true);
        graphView.getLegendRenderer().setAlign(LegendRenderer.LegendAlign.TOP);


        graphSeriesBar.setOnDataPointTapListener(new OnDataPointTapListener() {
            @Override
            public void onTap(Series series, DataPointInterface dataPoint) {

                if(dataPoint.getX() == 0){
                    Toast.makeText(getActivity(), "Gráfico: Barra de Gor: "+dataPoint, Toast.LENGTH_SHORT).show();
                }else if(dataPoint.getX() == 1){
                    Toast.makeText(getActivity(), "Gráfico: Barra de Prot: "+dataPoint, Toast.LENGTH_SHORT).show();
                }else if(dataPoint.getX() == 2){
                    Toast.makeText(getActivity(), "Gráfico: Barra de Cas: "+dataPoint, Toast.LENGTH_SHORT).show();
                }else if(dataPoint.getX() == 3){
                    Toast.makeText(getActivity(), "Gráfico: Barra de Lact: "+dataPoint, Toast.LENGTH_SHORT).show();
                }else if(dataPoint.getX() == 4){
                    Toast.makeText(getActivity(), "Gráfico: Barra de St: "+dataPoint, Toast.LENGTH_SHORT).show();
                }else if(dataPoint.getX() == 5){
                    Toast.makeText(getActivity(), "Gráfico: Barra de Esd: "+dataPoint, Toast.LENGTH_SHORT).show();
                }else if(dataPoint.getX() == 6){
                    Toast.makeText(getActivity(), "Gráfico: Barra de Nu: "+dataPoint, Toast.LENGTH_SHORT).show();
                }else if(dataPoint.getX() == 7){
                    Toast.makeText(getActivity(), "Gráfico: Barra de Cel: "+dataPoint, Toast.LENGTH_SHORT).show();
                }else if(dataPoint.getX() == 8) {
                    Toast.makeText(getActivity(), "Gráfico: Barra de Ccs: " + dataPoint, Toast.LENGTH_SHORT).show();
                }

            }
        });


        return view;

    }


    @Override
    public void onStart() {
        super.onStart();

        //Receber dados do Firebase e plotar no grafico... Espero que der certo.
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                DataPoint[] dpBar = new DataPoint[9];
                double gord = 0, prot = 0, cas = 0, lact = 0, st = 0, esd = 0, nu = 0, cel = 0, ccs = 0;
                List<Double> doubleList = new ArrayList<>();

                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){

                    Ordenha ordenha = dataSnapshot1.getValue(Ordenha.class);

                    gord = ordenha.getGord();
                    prot = ordenha.getProt();
                    cas = ordenha.getCas();
                    lact = ordenha.getLact();
                    st  = ordenha.getSt();
                    esd = ordenha.getEsd();
                    nu = ordenha.getNu();
                    cel = ordenha.getCel();
                    ccs = ordenha.getCcs();

                }

                doubleList.add(gord);
                doubleList.add(prot);
                doubleList.add(cas);
                doubleList.add(lact);
                doubleList.add(st);
                doubleList.add(esd);
                doubleList.add(nu);
                doubleList.add(cel);
                doubleList.add(ccs);

                Log.i("Size","Tamanho:"+doubleList.size());

                for(int i=0;i<doubleList.size();i++){
                    dpBar[i] = new DataPoint(i, doubleList.get(i));

                }

                graphSeriesBar.resetData(dpBar);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
