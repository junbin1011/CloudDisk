package com.cloud.disk.platform.login;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.cloud.disk.library.http.callback.CallBack;
import com.cloud.platform.R;


public class LoginActivity extends AppCompatActivity {

    private LoginController loginController = new LoginController();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginController.login("", "", new CallBack() {
            @Override
            public void success(String message) {

            }

            @Override
            public void filed(String message) {

            }
        });
    }
}