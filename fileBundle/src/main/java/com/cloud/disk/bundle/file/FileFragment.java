package com.cloud.disk.bundle.file;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.cloud.filebundle.R;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@Route(path = "/fileBundle/file")
@AndroidEntryPoint
public class FileFragment extends Fragment {

    @Inject
    FileController fileController;

    public static FileFragment newInstance() {
        FileFragment fragment = new FileFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fileController.getFileList();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_file, container, false);
    }
}