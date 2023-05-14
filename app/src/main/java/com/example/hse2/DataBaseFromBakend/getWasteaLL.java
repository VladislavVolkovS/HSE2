package com.example.hse2.DataBaseFromBakend;

import android.app.ListActivity;
import android.content.Context;
import android.os.AsyncTask;

import com.example.hse2.Models.DataBase.DataBaseConnect;
import com.example.hse2.Models.User;
import com.example.hse2.Models.Waste;

import java.util.ArrayList;

public class getWasteaLL extends AsyncTask<ArrayList<Waste>,ArrayList<Waste>,ArrayList<Waste>> {

    private Context context;
    private ArrayList<Waste> list;
    private String login;

    public getWasteaLL(Context context,ArrayList<Waste> list,String login){
        this.context = context;
        this.list = list;
        this.login = login;
    }
    @Override
    protected ArrayList<Waste> doInBackground(ArrayList<Waste>... lists) {
        try {
            DataBaseConnect connect = new DataBaseConnect();
            User user = new User();
            user.setLogin(login);
            list = connect.getWasteAll(user,list);
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return list;
    }
    @Override
    protected void onPostExecute(ArrayList<Waste> a){
        a = list;

    }

}
