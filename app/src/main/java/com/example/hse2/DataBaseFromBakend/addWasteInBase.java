package com.example.hse2.DataBaseFromBakend;

import android.content.Context;
import android.os.AsyncTask;

import com.example.hse2.Models.DataBase.DataBaseConnect;
import com.example.hse2.Models.User;
import com.example.hse2.Models.Waste;

public class addWasteInBase extends AsyncTask<String,String,String> {

    private Context context;
    private Waste waste;
    private  User login;

    public addWasteInBase(Context context,Waste waste,String login){
        this.context = context;
        this.waste = waste;
        this.login = new User();
        this.login.setLogin(login);
    }

    @Override
    protected String doInBackground(String... str) {
        try {
            DataBaseConnect connect = new DataBaseConnect();
            connect.addWaste(login,waste);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return waste.getType();
    }
}
