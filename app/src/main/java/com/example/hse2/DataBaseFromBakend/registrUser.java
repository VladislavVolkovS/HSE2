package com.example.hse2.DataBaseFromBakend;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hse2.Models.User;
import com.example.hse2.Models.DataBase.*;

public class registrUser extends AsyncTask<Boolean,Void,User>{

    private Context context;
    private Boolean value;
    private User user;

    public registrUser(Context context,User user,Boolean value){
        this.context = context;
        this.user = user;
        this.value = value;
    }

    @Override
    protected User doInBackground(Boolean... booleans) {
        try {
            DataBaseConnect connect = new DataBaseConnect();
            boolean a = connect.addUser(user,value);
            user.helper = a;
            return user;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return user;
    }
    @Override
    protected void onPostExecute(User res){
        this.user = res;

    }

}

