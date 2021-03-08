package com.cloud.disk.bundle.file;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.cloud.filebundle.R;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DebugActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debug);
    }
}