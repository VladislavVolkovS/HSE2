package com.example.hse2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

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
import com.vishnusivadas.advanced_httpurlconnection.FetchData;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.sql.SQLException;

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

            /*User user = new User();
            user.setLogin(login.getText().toString());
            user.setPassword(password.getText().toString());
            DataBaseConnect connect = new DataBaseConnect();
            try {
                connect.checkUser(user);
                connect.inputUser(user);
            } catch (SQLException | ClassNotFoundException e) {
                throw new RuntimeException(e);
            }*/


            auth.signInWithEmailAndPassword(login.getText().toString(), password.getText().toString()).addOnSuccessListener(authResult -> {
                Intent intent = new Intent(MainActivity.this, HSE_activity_main.class);
                intent.putExtra("login", login.getText().toString());
                startActivity(intent);
                finish();
            }).addOnFailureListener(e -> Snackbar.make(root, "Ошибка авторизации " + e.getMessage(), Snackbar.LENGTH_SHORT).show());
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

            //регистрация


            auth.createUserWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnSuccessListener(authResult -> {
                User user = new User();
                user.setEmail(email.getText().toString());
                user.setLogin(login.getText().toString());
                user.setPassword(password.getText().toString());
                if (!TextUtils.isEmpty(surname.getText().toString())){
                    user.setSurname(surname.getText().toString());
                }
                if (!TextUtils.isEmpty(name.getText().toString())){
                    user.setName(name.getText().toString());
                }
                if (!TextUtils.isEmpty(patronymic.getText().toString())){
                    user.setPatronymic(patronymic.getText().toString());
                }

                //Start ProgressBar first (Set visibility VISIBLE)
                //Start ProgressBar first (Set visibility VISIBLE)
                Handler handler = new Handler(Looper.getMainLooper());
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        //Starting Write and Read data with URL
                        //Creating array for parameters
                        String[] field = new String[2];
                        field[0] = "param-1";
                        field[1] = "param-2";
                        //Creating array for data
                        String[] data = new String[2];
                        data[0] = "data-1";
                        data[1] = "data-2";
                        PutData putData = new PutData("https://projects.vishnusivadas.com/AdvancedHttpURLConnection/putDataTest.php", "POST", field, data);
                        if (putData.startPut()) {
                            if (putData.onComplete()) {
                                String result = putData.getResult();
                                //End ProgressBar (Set visibility to GONE)
                                Log.i("PutData", result);
                            }
                        }
                        //End Write and Read data with URL
                    }
                });


                users.child(auth.getCurrentUser().getUid()).setValue(user)
                        .addOnSuccessListener(unused -> Snackbar.make(root, "Вы успешно зарегистрировались!", Snackbar.LENGTH_SHORT).show());
                startActivity(new Intent(MainActivity.this, HSE_activity_main.class));
                finish();
            }).addOnFailureListener(e -> Snackbar.make(root, "Ошибка регистрации. " + e.getMessage(), Snackbar.LENGTH_SHORT).show());
        });

        dialog.show();
    }
}