package com.cloud.disk.bundle.file;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloud.disk.api.file.FileInfo;
import com.cloud.filebundle.R;

import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@Route(path = "/fileBundle/file")
@AndroidEntryPoint
public class FileFragment extends Fragment implements FileListContract.View {

    private RecyclerView fileListRecycleView;
    private TextView tvMessage;
    private FileListContract.FilePresenter filePresenter = new FilePresenterImpl(new FileRemoteDataSource(), this);

    public static FileListContract.View newInstance() {
        FileFragment fragment = new FileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_file, container, false);
        fileListRecycleView = view.findViewById(R.id.file_list);
        tvMessage = view.findViewById(R.id.tv_message);
        tvMessage.setOnClickListener(v -> filePresenter.getFileList());
        filePresenter.getFileList();
        return view;
    }

    @Override
    public void showFileList(List<FileInfo> fileList) {
        showTip(false);
        FileListAdapter fileListAdapter = new FileListAdapter(fileList, getActivity());
        fileListRecycleView.addItemDecoration(new DividerItemDecoration(
                getActivity(), DividerItemDecoration.VERTICAL));
        //设置布局显示格式
        fileListRecycleView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fileListRecycleView.setAdapter(fileListAdapter);
    }

    @Override
    public void showNetWorkException(String errorMessage) {
        showTip(true);
        //显示异常提醒数据
        tvMessage.setText(errorMessage);
    }

    @Override
    public void showEmptyData() {
        showTip(true);
        //显示空数据
        tvMessage.setText("empty data");
    }

    public void showTip(boolean show) {
        if (show) {
            tvMessage.setVisibility(View.VISIBLE);
            fileListRecycleView.setVisibility(View.GONE);
        } else {
            tvMessage.setVisibility(View.GONE);
            fileListRecycleView.setVisibility(View.VISIBLE);
        }
    }
}