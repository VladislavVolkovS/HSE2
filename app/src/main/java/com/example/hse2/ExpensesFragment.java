package com.example.hse2;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hse2.DataBaseFromBakend.getWasteaLL;
import com.example.hse2.Models.Waste;
import com.google.android.material.snackbar.Snackbar;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;

public class ExpensesFragment extends Fragment {

    Button[] buttons = new Button[12];
    Button add;
    ListView listView;
    String text;
    String cost;

    String login;
    public void setLogin(String s){
        this.login = s;
    }
    public  ExpensesFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view =inflater.inflate(R.layout.fragment_expenses, container, false);
        listView = (ListView) root_view.findViewById(R.id.list_of_last);
        List<String> list = new ArrayList<>();
        add = (Button) root_view.findViewById(R.id.button13);
//        String login = getArguments().getString("login");

        ArrayList<Waste> wastes = new ArrayList<>();
        try {
           wastes = new getWasteaLL(getContext(),wastes,login).execute().get();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for (int i = 0; i < wastes.size(); i++) {
            list.add(""+wastes.get(i).getType() + " - " + wastes.get(i).getSum()+"₽");
        }
        listView.setAdapter(new ArrayAdapter<>(root_view.getContext(), android.R.layout.simple_list_item_1, list));

        /*
        здесь нужно поместить в список информацию о последних 20 покупках
         */
        // 10000 ccevvf "Трата: " + text + " - " + cost + "₽"
        buttons[0] = (Button) root_view.findViewById(R.id.button1);
        buttons[0].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_clothes_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_1);
            text = textv.getText().toString();
        });
        buttons[1] = (Button) root_view.findViewById(R.id.button2);
        buttons[1].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_products_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_2);
            text = textv.getText().toString();
        });
        buttons[2] = (Button) root_view.findViewById(R.id.button3);
        buttons[2].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_cafe_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_3);
            text = textv.getText().toString();
        });
        buttons[3] = (Button) root_view.findViewById(R.id.button4);
        buttons[3].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_health_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_4);
            text = textv.getText().toString();
        });
        buttons[4] = (Button) root_view.findViewById(R.id.button5);
        buttons[4].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_car_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_5);
            text = textv.getText().toString();
        });
        buttons[5] = (Button) root_view.findViewById(R.id.button6);
        buttons[5].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_online_order_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_6);
            text = textv.getText().toString();
        });
        buttons[6] = (Button) root_view.findViewById(R.id.button7);
        buttons[6].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_education_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_7);
            text = textv.getText().toString();
        });
        buttons[7] = (Button) root_view.findViewById(R.id.button8);
        buttons[7].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_transport_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_8);
            text = textv.getText().toString();
        });
        buttons[8] = (Button) root_view.findViewById(R.id.button9);
        buttons[8].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_tax_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_9);
            text = textv.getText().toString();
        });
        buttons[9] = (Button) root_view.findViewById(R.id.button10);
        buttons[9].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_entertainment_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_10);
            text = textv.getText().toString();
        });
        buttons[10] = (Button) root_view.findViewById(R.id.button11);
        buttons[10].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_internet_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_11);
            text = textv.getText().toString();
        });
        buttons[11] = (Button) root_view.findViewById(R.id.button12);
        buttons[11].setOnClickListener(view -> {
            ImageView change_image = (ImageView) root_view.findViewById(R.id.imageView);
            change_image.setImageResource(R.mipmap.ic_other_foreground);
            TextView textv = (TextView) root_view.findViewById(R.id.botton_12);
            text = textv.getText().toString();
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MaterialEditText cost_field = root_view.findViewById(R.id.cost_field);
                cost = cost_field.getText().toString();
                if (cost.isEmpty()) {
                    Snackbar.make(root_view, "Вы не ввели сумму", Snackbar.LENGTH_SHORT).show();
                }
                else if(text == null){
                    Snackbar.make(root_view, "Вы не выбрали тип покупки", Snackbar.LENGTH_SHORT).show();
                }
                else{
                    list.add(0, "" + text + " - " + cost + "₽");
                    Waste waste = new Waste();
                    listView.setAdapter(new ArrayAdapter<>(root_view.getContext(), android.R.layout.simple_list_item_1, list));
                    cost_field.onEditorAction(EditorInfo.IME_ACTION_DONE);
                    cost_field.setText("");
                }
            }
        });
        return root_view;
    }
}