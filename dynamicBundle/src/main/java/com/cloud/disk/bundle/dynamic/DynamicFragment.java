package com.cloud.disk.bundle.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.cloud.disk.bundle.file.FileInfo;
import com.cloud.disk.bundle.file.TransferFile;
import com.cloud.dynamicbundle.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class DynamicFragment extends Fragment {

    @Inject
    DynamicController dynamicController;
    Button btnShare;
    @Inject
    TransferFile transferFile;

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
        //上传文件
        FileInfo fileInfo = transferFile.upload("/data/data/user.png");
        dynamicController.post(new Dynamic(), fileInfo);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_dynamic, container, false);
        btnShare = inflate.findViewById(R.id.btn_share);
        btnShare.setOnClickListener(v -> shareToWeiXin("hello world"));
        return inflate;
    }

    public void shareToWeiXin(String content) {
        //通过第三方组件分享内容至微信
    }
}