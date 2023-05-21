package com.example.hse2.DataBaseFromBakend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hse2.Models.User;
import com.example.hse2.Models.DataBase.*;

import java.sql.Connection;
import java.sql.DriverManager;

public class getInputUser extends AsyncTask<User,Void,Boolean> {

    private Context context;
    private User user;

    private Boolean value;
    public getInputUser(Context context,User user,Boolean value){
        this.context = context;
        this.user = user;

    }

    @Override
    protected Boolean doInBackground(User... users) {
        try {
            DataBaseConnect connect = new DataBaseConnect();
            boolean a = connect.inputUser(user);
            value = a;
            return a;

        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }
    }
    @Override
    protected void onPostExecute(Boolean res){

        res = value;
    }
}
