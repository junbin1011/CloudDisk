package com.cloud.disk.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cloud.disk.R;
import com.cloud.disk.controller.DynamicController;
import com.cloud.disk.controller.FileController;
import com.cloud.disk.model.Dynamic;
import com.cloud.disk.model.FileInfo;


public class DynamicFragment extends Fragment {

    DynamicController dynamicController = new DynamicController();
    FileController fileController = new FileController();
    Button btnShare;
    public static DynamicFragment newInstance() {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dynamicController.getDynamicList();
        FileInfo fileInfo = fileController.upload("/data/data/user.png");
        dynamicController.post(new Dynamic(), fileInfo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_dynamic, container, false);
        btnShare=inflate.findViewById(R.id.btn_share);
        btnShare.setOnClickListener(v -> shareToWeiXin("hello world"));
        return inflate;
    }

    public void shareToWeiXin(String content) {
        //通过第三方组件分享内容至微信
    }
}