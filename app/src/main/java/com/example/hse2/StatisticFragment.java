package com.example.hse2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hse2.Models.Waste;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class StatisticFragment extends Fragment {
    PieChart pieChart;
    ArrayList<PieEntry> pieEntries;
    ArrayList<Waste> wasteArrayList = new ArrayList<>();
    String login;
    public void setLogin(String s){
        this.login = s;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View r_view = inflater.inflate(R.layout.fragment_statistic, container, false);
        Button day = (Button) r_view.findViewById(R.id.button_day);
        Button week = (Button) r_view.findViewById(R.id.button_week);
        Button month = (Button) r_view.findViewById(R.id.button_month);
        Button year = (Button) r_view.findViewById(R.id.button_year);
        pieChart = (PieChart) r_view.findViewById(R.id.pie_chart);
        pieEntries = new ArrayList<>();
        /*int countOfWaste = 100;
        Waste w = new Waste(1800, "билет в автобусе", "транспорт");
        wasteArrayList.add(w);
        pieEntries.add(new PieEntry(countOfWaste, w.getType()));*/

        //еще добавим

        day.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(1000, "Продукты"));
                pieEntries.add(new PieEntry(800, "Кафе и рестораны"));
                pieEntries.add(new PieEntry(140, "Прочее"));

                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Статистика за день");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueTextSize(12);
                Description title = new Description();
                title.setText("");
                pieChart.setDescription(title);
                pieChart.setCenterText("Статистика за день");
                pieChart.setCenterTextSize(20);
                pieChart.setData(new PieData(pieDataSet));
                pieChart.animateXY(1000, 1000);
                pieChart.invalidate();
            }
        });
        week.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(5000, "Продукты"));
                pieEntries.add(new PieEntry(2500, "Кафе и рестораны"));
                pieEntries.add(new PieEntry(700, "Прочее"));
                pieEntries.add(new PieEntry(8300, "Одежда"));

                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Статистика за неделю");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueTextSize(12);
                Description title = new Description();
                title.setText("");
                pieChart.setDescription(title);
                pieChart.setCenterText("Статистика за неделю");
                pieChart.setCenterTextSize(20);
                pieChart.setData(new PieData(pieDataSet));
                pieChart.animateXY(1000, 1000);
                pieChart.invalidate();
            }
        });
        month.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(25000, "Продукты"));
                pieEntries.add(new PieEntry(15000, "Кафе и рестораны"));
                pieEntries.add(new PieEntry(10000, "Прочее"));
                pieEntries.add(new PieEntry(8300, "Одежда"));

                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Статистика за месяц");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueTextSize(12);
                Description title = new Description();
                title.setText("");
                pieChart.setDescription(title);
                pieChart.setCenterText("Статистика за месяц");
                pieChart.setCenterTextSize(20);
                pieChart.setData(new PieData(pieDataSet));
                pieChart.animateXY(1000, 1000);
                pieChart.invalidate();
            }
        });
        year.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pieEntries = new ArrayList<>();
                pieEntries.add(new PieEntry(500000, "Продукты"));
                pieEntries.add(new PieEntry(350000, "Кафе и рестораны"));
                pieEntries.add(new PieEntry(230000, "Прочее"));
                pieEntries.add(new PieEntry(300000, "Одежда"));
                PieDataSet pieDataSet = new PieDataSet(pieEntries, "Статистика за год");
                pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
                pieDataSet.setValueTextSize(12);
                Description title = new Description();
                title.setText("");
                pieChart.setDescription(title);
                pieChart.setCenterText("Статистика за год");
                pieChart.setCenterTextSize(20);
                pieChart.setData(new PieData(pieDataSet));
                pieChart.animateXY(1000, 1000);
                pieChart.invalidate();
            }
        });


        return r_view;
    }

}