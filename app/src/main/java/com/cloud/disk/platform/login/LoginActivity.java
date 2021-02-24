package com.cloud.disk.platform.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cloud.disk.R;
import com.cloud.disk.library.http.callback.CallBack;
import com.cloud.disk.bundle.user.UserController;


public class LoginActivity extends AppCompatActivity {

    UserController userController = new UserController();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        userController.login("", "", new CallBack() {
            @Override
            public void success(String message) {

            }

            @Override
            public void filed(String message) {

            }
        });
    }
}