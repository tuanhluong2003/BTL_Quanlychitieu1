package com.google.BTL_Quanlychitieu.ui.Thongke;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.google.BTL_Quanlychitieu.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Frangment_Thongke extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    LineChart lineChart;

    private List<String> xValues;

    public Frangment_Thongke() {
    }

    public static Frangment_Thongke newInstance(String param1, String param2) {
        Frangment_Thongke fragment = new Frangment_Thongke();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_frangment__thongke, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lineChart = view.findViewById(R.id.chart);

        Description description = new Description();
        description.setText("Student record");
        description.setPosition(150f, 15f); // vi tri hien description text

        lineChart.setDescription(description);
        lineChart.getAxisRight().setDrawLabels(false); // an thong tin chia cot phia trai

        xValues = Arrays.asList("Ten 1", "Ten 2", "Ten 3", "Ten 4");

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM); // vi tri hien ten
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValues));
        xAxis.setLabelCount(4); // so cot hien thi
        xAxis.setGranularity(1f); // mat do hien ten

        YAxis yAxis = lineChart.getAxisLeft();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(120f);
        yAxis.setAxisLineWidth(2f);
        yAxis.setAxisLineColor(Color.BLACK);
        yAxis.setLabelCount(10);

        List<Entry> entries1 = new ArrayList<>();
        entries1.add(new Entry(0, 10f));
        entries1.add(new Entry(1, 40f));
        entries1.add(new Entry(2, 100f));
        entries1.add(new Entry(3, 30f));

        List<Entry> entries2 = new ArrayList<>();
        entries2.add(new Entry(0, 20f));
        entries2.add(new Entry(1, 45f));
        entries2.add(new Entry(2, 30f));
        entries2.add(new Entry(3, 20f));

        LineDataSet dataset1 = new LineDataSet(entries1, "nhom 1");
        dataset1.setColor(Color.RED);
        dataset1.setLineWidth(2f);
        LineDataSet dataset2 = new LineDataSet(entries2, "nhom 2");
        dataset2.setColor(Color.BLACK);
        dataset2.setLineWidth(2f);

        LineData lineData = new LineData(dataset1, dataset2);
        lineChart.setData(lineData);
        lineChart.invalidate();
    }
}