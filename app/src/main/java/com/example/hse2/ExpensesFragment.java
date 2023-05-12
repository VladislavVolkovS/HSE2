package com.example.hse2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ExpensesFragment extends Fragment {

    Button[] buttons;
    Button add;
    int[] images;

    public  ExpensesFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root_view =inflater.inflate(R.layout.fragment_expenses, container, false);
        buttons[0] = (Button) root_view.findViewById(R.id.button1);
        buttons[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ImageView change_image = (ImageView) root_view.findViewById()
            }
        });
        buttons[1] = (Button) root_view.findViewById(R.id.button2);
        buttons[2] = (Button) root_view.findViewById(R.id.button3);
        buttons[3] = (Button) root_view.findViewById(R.id.button4);
        buttons[4] = (Button) root_view.findViewById(R.id.button5);
        buttons[5] = (Button) root_view.findViewById(R.id.button6);
        buttons[6] = (Button) root_view.findViewById(R.id.button7);
        buttons[7] = (Button) root_view.findViewById(R.id.button8);
        buttons[8] = (Button) root_view.findViewById(R.id.button9);
        buttons[9] = (Button) root_view.findViewById(R.id.button10);
        buttons[10] = (Button) root_view.findViewById(R.id.button11);
        buttons[11] = (Button) root_view.findViewById(R.id.button12);
        //ImageView change_image = (ImageView) root_view.findViewById()
        return root_view;
    }

   // @Override
    public void onClick(View view){
        //
    }
}