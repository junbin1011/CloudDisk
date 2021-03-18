package com.cloud.disk.bundle.dynamic;

import android.accounts.NetworkErrorException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloud.disk.api.file.FileInfo;
import com.cloud.disk.api.file.TransferFile;
import com.cloud.dynamicbundle.R;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@Route(path = "/dynamicBundle/dynamic")
@AndroidEntryPoint
public class DynamicFragment extends Fragment {

    @Inject
    DynamicController dynamicController;
    Button btnUpload;
    @Inject
    TransferFile transferFile;

    private RecyclerView dynamicListRecycleView;
    private TextView tvMessage;

    public static DynamicFragment newInstance() {
        DynamicFragment fragment = new DynamicFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        btnUpload = view.findViewById(R.id.btn_upload);
        btnUpload.setOnClickListener(v -> uploadDynamic());
        dynamicListRecycleView = view.findViewById(R.id.file_list);
        tvMessage = view.findViewById(R.id.tv_message);
        tvMessage.setOnClickListener(v -> getDynamicList());
        getDynamicList();
        return view;
    }

    public void uploadDynamic() {
        //上传文件
        FileInfo fileInfo = transferFile.upload("/data/data/user.png");
        dynamicController.post(new Dynamic(0, "第一个动态", System.currentTimeMillis()), fileInfo);
    }

    public void getDynamicList() {
        new Thread(() -> {
            Message message = new Message();
            try {
                List<Dynamic> dynamicList = dynamicController.getDynamicList();
                message.what = 1;
                message.obj = dynamicList;
            } catch (NetworkErrorException e) {
                message.what = 0;
                message.obj = "NetworkErrorException";
                e.printStackTrace();
            }
            mHandler.sendMessage(message);
        }).start();
    }

    Handler mHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what == 1) {
                showTip(false);
                //显示网络数据
                List<Dynamic> dynamicList = (List<Dynamic>) msg.obj;
                if (dynamicList == null || dynamicList.size() == 0) {
                    showTip(true);
                    //显示空数据
                    tvMessage.setText("empty data");

                } else {
                    DynamicListAdapter fileListAdapter = new DynamicListAdapter(dynamicList, getActivity());
                    dynamicListRecycleView.addItemDecoration(new DividerItemDecoration(
                            getActivity(), DividerItemDecoration.VERTICAL));
                    //设置布局显示格式
                    dynamicListRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    dynamicListRecycleView.setAdapter(fileListAdapter);
                    //从网络中更新到数据保持到缓存之中
                    dynamicController.saveDynamicToCache(dynamicList);
                }
            } else if (msg.what == 0) {
                //尝试从缓存中读取数据
                List<Dynamic> dynamicList = dynamicController.getDynamicListFromCache();
                if (dynamicList == null || dynamicList.size() == 0) {
                    showTip(true);
                    //显示异常提醒数据
                    tvMessage.setText(msg.obj.toString());
                } else {
                    DynamicListAdapter fileListAdapter = new DynamicListAdapter(dynamicList, getActivity());
                    dynamicListRecycleView.addItemDecoration(new DividerItemDecoration(
                            getActivity(), DividerItemDecoration.VERTICAL));
                    //设置布局显示格式
                    dynamicListRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    dynamicListRecycleView.setAdapter(fileListAdapter);
                }

            }
            return false;
        }
    });

    public void showTip(boolean show) {
        if (show) {
            tvMessage.setVisibility(View.VISIBLE);
            dynamicListRecycleView.setVisibility(View.GONE);
        } else {
            tvMessage.setVisibility(View.GONE);
            dynamicListRecycleView.setVisibility(View.VISIBLE);
        }
    }
}