package com.example.hse2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.hse2.DataBaseFromBakend.getWasteaLL;
import com.example.hse2.Models.Waste;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Objects;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;

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
                ArrayList<Waste> wastes = new ArrayList<>();
                try {
                    wastes = new getWasteaLL(getContext(),wastes,login).execute().get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int a1 = 0;
                int a2 = 0;
                int a3 = 0;
                int a4 = 0;
                int a5 = 0;
                int a6 = 0;
                int a7 = 0;
                int a8 = 0;
                int a9 = 0;
                int a10 = 0;
                int a11 = 0;
                for(int i = 0;i < wastes.size();i++){
                    Calendar dateTime = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
                    dateTime.setTimeInMillis(wastes.get(i).getDate().getTime());
                    Calendar currentDate = Calendar.getInstance(TimeZone.getTimeZone("UTC"));

                    boolean isToday = dateTime.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                            dateTime.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) &&
                            dateTime.get(Calendar.DAY_OF_MONTH) == currentDate.get(Calendar.DAY_OF_MONTH)+1;
                    if(!isToday){
                        continue;
                    }

                    if(Objects.equals(wastes.get(i).getType(), "Одежда и аксессуары")){
                        a1+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Продукты")){
                        a2+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Рестораны и кафе")){
                        a3+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Развлечения и хобби")){
                        a4+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Здоровье и красота")){
                        a5+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Автомобиль")){
                        a6+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Онлайн заказы")){
                        a7+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Транспорт")){
                        a8+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Образование")){
                        a9+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Налоги, ЖКХ, интернет и связь")){
                        a10+=wastes.get(i).getSum();
                    }
                    else{
                        a11+=wastes.get(i).getSum();
                    }
                }
                pieEntries.add(new PieEntry(a1, "Одежда и аксессуары"));
                pieEntries.add(new PieEntry(a2, "Продукты"));
                pieEntries.add(new PieEntry(a3, "Рестораны и кафе"));
                pieEntries.add(new PieEntry(a4, "Развлечения и хобби"));
                pieEntries.add(new PieEntry(a5, "Здоровье и красота"));
                pieEntries.add(new PieEntry(a6, "Автомобиль"));
                pieEntries.add(new PieEntry(a7, "Онлайн заказы"));
                pieEntries.add(new PieEntry(a8, "Транспорт"));
                pieEntries.add(new PieEntry(a9, "Образование"));
                pieEntries.add(new PieEntry(a10, "Налоги, ЖКХ, интернет и связь"));
                pieEntries.add(new PieEntry(a11, "Другое"));
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
                ArrayList<Waste> wastes = new ArrayList<>();
                try {
                    wastes = new getWasteaLL(getContext(),wastes,login).execute().get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int a1 = 0;
                int a2 = 0;
                int a3 = 0;
                int a4 = 0;
                int a5 = 0;
                int a6 = 0;
                int a7 = 0;
                int a8 = 0;
                int a9 = 0;
                int a10 = 0;
                int a11 = 0;
                for(int i = 0;i < wastes.size();i++){
                    Calendar dateTime = Calendar.getInstance();
                    dateTime.setTimeInMillis(wastes.get(i).getDate().getTime());
                    Calendar currentDate = Calendar.getInstance();
                    boolean isToday = dateTime.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                            dateTime.get(Calendar.WEEK_OF_YEAR) == currentDate.get(Calendar.WEEK_OF_YEAR);
                    if(!isToday){
                        continue;
                    }
                    if(Objects.equals(wastes.get(i).getType(), "Одежда и аксессуары")){
                        a1+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Продукты")){
                        a2+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Рестораны и кафе")){
                        a3+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Развлечения и хобби")){
                        a4+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Здоровье и красота")){
                        a5+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Автомобиль")){
                        a6+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Онлайн заказы")){
                        a7+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Транспорт")){
                        a8+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Образование")){
                        a9+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Налоги, ЖКХ, интернет и связь")){
                        a10+=wastes.get(i).getSum();
                    }
                    else{
                        a11+=wastes.get(i).getSum();
                    }
                }
                pieEntries.add(new PieEntry(a1, "Одежда и аксессуары"));
                pieEntries.add(new PieEntry(a2, "Продукты"));
                pieEntries.add(new PieEntry(a3, "Рестораны и кафе"));
                pieEntries.add(new PieEntry(a4, "Развлечения и хобби"));
                pieEntries.add(new PieEntry(a5, "Здоровье и красота"));
                pieEntries.add(new PieEntry(a6, "Автомобиль"));
                pieEntries.add(new PieEntry(a7, "Онлайн заказы"));
                pieEntries.add(new PieEntry(a8, "Транспорт"));
                pieEntries.add(new PieEntry(a9, "Образование"));
                pieEntries.add(new PieEntry(a10, "Налоги, ЖКХ, интернет и связь"));
                pieEntries.add(new PieEntry(a11, "Другое"));
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
                ArrayList<Waste> wastes = new ArrayList<>();
                try {
                    wastes = new getWasteaLL(getContext(),wastes,login).execute().get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int a1 = 0;
                int a2 = 0;
                int a3 = 0;
                int a4 = 0;
                int a5 = 0;
                int a6 = 0;
                int a7 = 0;
                int a8 = 0;
                int a9 = 0;
                int a10 = 0;
                int a11 = 0;
                for(int i = 0;i < wastes.size();i++){
                    Calendar dateTime = Calendar.getInstance();
                    dateTime.setTimeInMillis(wastes.get(i).getDate().getTime());
                    Calendar currentDate = Calendar.getInstance();
                    boolean isToday = dateTime.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                            dateTime.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH);
                    if(!isToday){
                        continue;
                    }
                    if(Objects.equals(wastes.get(i).getType(), "Одежда и аксессуары")){
                        a1+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Продукты")){
                        a2+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Рестораны и кафе")){
                        a3+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Развлечения и хобби")){
                        a4+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Здоровье и красота")){
                        a5+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Автомобиль")){
                        a6+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Онлайн заказы")){
                        a7+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Транспорт")){
                        a8+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Образование")){
                        a9+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Налоги, ЖКХ, интернет и связь")){
                        a10+=wastes.get(i).getSum();
                    }
                    else{
                        a11+=wastes.get(i).getSum();
                    }
                }
                pieEntries.add(new PieEntry(a1, "Одежда и аксессуары"));
                pieEntries.add(new PieEntry(a2, "Продукты"));
                pieEntries.add(new PieEntry(a3, "Рестораны и кафе"));
                pieEntries.add(new PieEntry(a4, "Развлечения и хобби"));
                pieEntries.add(new PieEntry(a5, "Здоровье и красота"));
                pieEntries.add(new PieEntry(a6, "Автомобиль"));
                pieEntries.add(new PieEntry(a7, "Онлайн заказы"));
                pieEntries.add(new PieEntry(a8, "Транспорт"));
                pieEntries.add(new PieEntry(a9, "Образование"));
                pieEntries.add(new PieEntry(a10, "Налоги, ЖКХ, интернет и связь"));
                pieEntries.add(new PieEntry(a11, "Другое"));

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
                ArrayList<Waste> wastes = new ArrayList<>();
                try {
                    wastes = new getWasteaLL(getContext(),wastes,login).execute().get();
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                int a1 = 0;
                int a2 = 0;
                int a3 = 0;
                int a4 = 0;
                int a5 = 0;
                int a6 = 0;
                int a7 = 0;
                int a8 = 0;
                int a9 = 0;
                int a10 = 0;
                int a11 = 0;
                for(int i = 0;i < wastes.size();i++){

                    Calendar dateTime = Calendar.getInstance();
                    dateTime.setTimeInMillis(wastes.get(i).getDate().getTime());
                    Calendar currentDate = Calendar.getInstance();
                    boolean isToday = dateTime.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR);
                    if(!isToday){
                        continue;
                    }

                    if(Objects.equals(wastes.get(i).getType(), "Одежда и аксессуары")){
                        a1+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Продукты")){
                        a2+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Рестораны и кафе")){
                        a3+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Развлечения и хобби")){
                        a4+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Здоровье и красота")){
                        a5+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Автомобиль")){
                        a6+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Онлайн заказы")){
                        a7+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Транспорт")){
                        a8+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Образование")){
                        a9+=wastes.get(i).getSum();
                    }
                    else if(Objects.equals(wastes.get(i).getType(), "Налоги, ЖКХ, интернет и связь")){
                        a10+=wastes.get(i).getSum();
                    }
                    else{
                        a11+=wastes.get(i).getSum();
                    }
                }
                pieEntries.add(new PieEntry(a1, "Одежда и аксессуары"));
                pieEntries.add(new PieEntry(a2, "Продукты"));
                pieEntries.add(new PieEntry(a3, "Рестораны и кафе"));
                pieEntries.add(new PieEntry(a4, "Развлечения и хобби"));
                pieEntries.add(new PieEntry(a5, "Здоровье и красота"));
                pieEntries.add(new PieEntry(a6, "Автомобиль"));
                pieEntries.add(new PieEntry(a7, "Онлайн заказы"));
                pieEntries.add(new PieEntry(a8, "Транспорт"));
                pieEntries.add(new PieEntry(a9, "Образование"));
                pieEntries.add(new PieEntry(a10, "Налоги, ЖКХ, интернет и связь"));
                pieEntries.add(new PieEntry(a11, "Другое"));
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