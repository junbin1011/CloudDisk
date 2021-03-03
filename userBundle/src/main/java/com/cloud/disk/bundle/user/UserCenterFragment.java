package com.cloud.disk.bundle.user;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cloud.userbundle.R;


public class UserCenterFragment extends Fragment {

    UserController userController = new UserController();
    Button btnShare;

    public static UserCenterFragment newInstance() {
        UserCenterFragment fragment = new UserCenterFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userController.getUserInfo();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_user_center, container, false);
        btnShare=inflate.findViewById(R.id.btn_share);
        btnShare.setOnClickListener(v -> shareToWeiXin("hello world"));
        return inflate;
    }

    public void shareToWeiXin(String content) {
        //通过第三方组件分享内容至微信
    }
}