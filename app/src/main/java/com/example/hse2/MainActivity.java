package com.example.hse2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.hse2.DataBaseFromBakend.*;
import com.example.hse2.Models.DataBase.DataBaseConnect;
import com.example.hse2.Models.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.sql.SQLException;
import java.sql.Time;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class MainActivity extends AppCompatActivity {

    Button btnSignIn, btnRegister;
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    RelativeLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignIn = findViewById(R.id.btnSignIn);
        btnRegister = findViewById(R.id.btnRegister);

        root = findViewById(R.id.root_fragment);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("Users");

        btnRegister.setOnClickListener(view -> showWindowRegister());
        btnSignIn.setOnClickListener(view -> showWindowSignIn());
    }

    private void showWindowSignIn() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Вход");
        dialog.setMessage("Заполните поля для авторизации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View sign_in_window = inflater.inflate(R.layout.sign_in_window, null);
        dialog.setView(sign_in_window);

        MaterialEditText login = sign_in_window.findViewById(R.id.login_field);
        MaterialEditText password = sign_in_window.findViewById(R.id.password_field);

        dialog.setNegativeButton("Назад", (dialogInterface, i) -> dialogInterface.dismiss());

        dialog.setPositiveButton("Войти", (dialogInterface, i) -> {
            if (TextUtils.isEmpty(login.getText().toString())){
                Snackbar.make(root, "Введите логин", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password.getText().toString())){
                Snackbar.make(root, "Введите пароль", Snackbar.LENGTH_SHORT).show();
                return;
            }

            User user = new User();
            user.setLogin(login.getText().toString());
            user.setPassword(password.getText().toString());
            Boolean value = false;


            try {
                new  getInputUser(this,user,value).execute().get(3,TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
            if(user.getName() != null){
                value = true;
            }

            if (value) {
                Intent intent = new Intent(MainActivity.this, HSE_activity_main.class);
                intent.putExtra("login", login.getText().toString());
                startActivity(intent);
                finish();
            }
            else{
                Snackbar.make(root, "Вы неправильно ввели логин или пароль, или такого пользователя не существует", Snackbar.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    private void showWindowRegister() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Зарегистрироваться");
        dialog.setMessage("Заполните поля для завершения регистрации");

        LayoutInflater inflater = LayoutInflater.from(this);
        View register_window = inflater.inflate(R.layout.register_window, null);
        dialog.setView(register_window);

        MaterialEditText email = register_window.findViewById(R.id.email_field);
        MaterialEditText login = register_window.findViewById(R.id.login_field);
        MaterialEditText password = register_window.findViewById(R.id.password_field);
        MaterialEditText password2 = register_window.findViewById(R.id.confirm_password_field);
        MaterialEditText surname = register_window.findViewById(R.id.surname_field);
        MaterialEditText name = register_window.findViewById(R.id.name_field);
        MaterialEditText patronymic = register_window.findViewById(R.id.patronymic_field);

        dialog.setNegativeButton("Назад", (dialogInterface, i) -> dialogInterface.dismiss());

        dialog.setPositiveButton("Подтвердить регистрацию", (dialogInterface, i) -> {
            if (TextUtils.isEmpty(email.getText().toString())){
                Snackbar.make(root, "Введите электронную почту", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(login.getText().toString())){
                Snackbar.make(root, "Введите логин", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password.getText().toString())){
                Snackbar.make(root, "Введите пароль", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password2.getText().toString())){
                Snackbar.make(root, "Требуется подтвердить пароль", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (password.getText().toString().length() < 6){
                Snackbar.make(root, "Пароль должен содержать более 5 символов", Snackbar.LENGTH_SHORT).show();
                return;
            }
            if (!password.getText().toString().equals(password2.getText().toString())){
                Snackbar.make(root, "Пароли не совпадают", Snackbar.LENGTH_SHORT).show();
                return;
            }
            User user = new User();
            user.setName(name.getText().toString());
            user.setSurname(surname.getText().toString());
            user.setPatronymic(patronymic.getText().toString());
            user.setPassword(password.getText().toString());
            user.setLogin(login.getText().toString());
            user.setEmail(email.getText().toString());
            Boolean value = true;
            try {
                new registrUser(this,user,value).execute().get(5, TimeUnit.SECONDS);
            } catch (ExecutionException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } catch (TimeoutException e) {
                throw new RuntimeException(e);
            }
            if(user.helper){
                Intent intent = new Intent(MainActivity.this, HSE_activity_main.class);
                intent.putExtra("login", login.getText().toString());
                startActivity(intent);
                finish();
            }
            else{
                Snackbar.make(root, "Ошибка регистрации", Snackbar.LENGTH_SHORT).show();
            }
            //регистрация
//            Snackbar.make(root, "Ошибка регистрации. ", Snackbar.LENGTH_SHORT).show();

        });

        dialog.show();
    }
}