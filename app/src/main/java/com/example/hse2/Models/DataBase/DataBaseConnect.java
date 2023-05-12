package com.example.hse2.Models.DataBase;
import com.example.hse2.Models.User;
import com.example.hse2.Models.Waste;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

public class DataBaseConnect extends ConfigsDB {
    Connection DBConnect;
    /**
     * Метод который создаёт коннект с базой данных
     * Параметры можно изменить в классе ConfigsDB
    */
    public Connection getDBConnect() throws ClassNotFoundException, SQLException{
        String connectSting = "jdbc:mysql://" + DBHost + ":" + DBPort + "/" + DBName;
        DBConnect = DriverManager.getConnection(connectSting,DBUser,DBPassword);

        return DBConnect;
    }



    public boolean checkUser(User user) throws SQLException, ClassNotFoundException {
        String check = "SELECT * FROM " + ConstUsers.USER_TABLE + " WHERE " +
                ConstUsers.USERS_LOGIN + "=? OR " + ConstUsers.USERS_EMAIL + "=?" ;
        ResultSet res = null;
        PreparedStatement sel = getDBConnect().prepareStatement(check);
        sel.setString(1,user.getLogin());
        sel.setString(2,user.getEmail());
        res = sel.executeQuery();
        int count = 0;
        while (res.next()){
            count++;
        }
        if(count == 0){
            return false;
        }
        else{
            return true;
        }
    }

    public User inputUser(User user) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + ConstUsers.USER_TABLE + " WHERE " +
                ConstUsers.USERS_PASSWORD + "=? AND " + ConstUsers.USERS_LOGIN + "=?" ;
        ResultSet res = null;
        PreparedStatement sel = getDBConnect().prepareStatement(select);
        sel.setString(1,user.getPassword());
        sel.setString(2,user.getLogin());
        res = sel.executeQuery();
        int count = 0;
        while (res.next()){
            count++;
            user.setName(res.getString(1));
            user.setSurname(res.getString(2));
            user.setPatronymic(res.getString(3));
            user.setBrithDate(res.getDate(4));
            user.setGender(res.getString(5));
            user.setLogin(res.getString(6));
            user.setPassword(res.getString(7));
            user.setEmail(res.getString(8));


        }
        if(count == 0){
            System.out.println("Неправильно введёт логин или пароль");
            return user.NullUser();
        }
        else{
            System.out.println("Вы успешно авторизирвоались");
            return user;
        }
    }

    public boolean addUser(User user) throws SQLException, ClassNotFoundException {
        boolean res = checkUser(user);
        if(res == false){
            String insert = "INSERT INTO " + ConstUsers.USER_TABLE + "(" +
                    ConstUsers.USERS_NAME +","+ ConstUsers.USERS_SURNAME +","+ ConstUsers.USERS_PATRONYMIC +","+ ConstUsers.USERS_BRITHDATE +","+ ConstUsers.USERS_GENDER
                    +","+ ConstUsers.USERS_LOGIN +","+ ConstUsers.USERS_PASSWORD +","+ ConstUsers.USERS_EMAIL +")" +
                    "VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement pST = getDBConnect().prepareStatement(insert);
            pST.setString(1,user.getName());
            pST.setString(2,user.getSurname());
            pST.setString(3,user.getPatronymic());
            pST.setDate(4,Date.valueOf(user.getBrithDate().toString()));
            pST.setString(5,user.getGender());
            pST.setString(6,user.getLogin());
            pST.setString(7,user.getPassword());
            pST.setString(8,user.getEmail());
            pST.executeUpdate();
            System.out.println("Пользователь Добавлен");
            return true;
        }
        else {
            System.out.println("Пользователь с таким логином или почтой уже существует");
            return false;
        }
    }




    public ArrayList<String> selectType() throws SQLException, ClassNotFoundException {
        String select =  "SELECT * FROM " + ConstTypeWaste.TYPE_WASTE_TABLE ;
        PreparedStatement get = getDBConnect().prepareStatement(select);
        ResultSet res = get.executeQuery();
        ArrayList<String> list =  new ArrayList<>();
        while (res.next()){
            list.add(res.getString(1));
        }
        return list;
    }

    public void addWaste(User user, Waste waste) throws SQLException, ClassNotFoundException {
        String insert = "INSERT INTO " + ConstWaste.WASTE_TABLE + "(" +
                ConstWaste.WASTE_LOGIN +","+ ConstWaste.WASTE_NAME +","+ ConstWaste.WASTE_TYPE +","+ ConstWaste.WASTE_SUM +","+ ConstWaste.WASTE_DATE
                +")" +
                "VALUES(?,?,?,?,?)";
        PreparedStatement added = getDBConnect().prepareStatement(insert);
        added.setString(1,user.getLogin());
        added.setString(2,waste.getName());
        added.setString(3,waste.getType());
        added.setInt(4,waste.getSum());

        java.util.Date today = new java.util.Date();
        Timestamp timestamp = new Timestamp(today.getTime());
        added.setTimestamp (5, timestamp);
        added.executeUpdate();
    }
    public void getWasteAll(User user,ArrayList<Waste> list) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + ConstWaste.WASTE_TABLE + " WHERE " +
                ConstWaste.WASTE_LOGIN + "=?";
        ResultSet res = null;
        PreparedStatement sel = getDBConnect().prepareStatement(select);
        sel.setString(1,user.getLogin());
        res = sel.executeQuery();
        while (res.next()){
            Waste waste = new Waste(res.getInt(4),res.getString(2),res.getString(3),res.getTimestamp(5));
            list.add(waste);
        }
    }
    public void getWasteInterval(User user,ArrayList<Waste> list) throws SQLException, ClassNotFoundException {
        String select = "SELECT * FROM " + ConstWaste.WASTE_TABLE + " WHERE " +
                ConstUsers.USERS_LOGIN + "=?" ;
        ResultSet res = null;
        PreparedStatement sel = getDBConnect().prepareStatement(select);
        sel.setString(1,user.getLogin());
        res = sel.executeQuery();
        while (res.next()){
            Waste waste = new Waste(res.getInt(3),res.getString(1),res.getString(2),res.getTimestamp(4));
            list.add(waste);
        }
    }


}
