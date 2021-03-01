package com.cloud.disk.app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;

import com.cloud.disk.R;
import com.cloud.disk.app.adapter.SectionsPagerAdapter;
import com.cloud.disk.bundle.user.UserStateImpl;
import com.cloud.disk.bundle.user.UserController;
import com.cloud.disk.bundle.dynamic.DynamicFragment;
import com.cloud.disk.bundle.file.FileFragment;
import com.cloud.disk.platform.login.LoginActivity;
import com.cloud.disk.bundle.user.UserCenterFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(FileFragment.newInstance(new UserStateImpl()));
        fragments.add(DynamicFragment.newInstance());
        fragments.add(UserCenterFragment.newInstance());
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager(), fragments);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(view -> {
        if (UserController.isLogin) {
                return;
            }
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        });
    }
}