package com.cloud.disk.bundle.file;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cloud.disk.bundle.user.UserState;
import com.cloud.filebundle.R;


public class FileFragment extends Fragment {

    FileController fileController;

    public FileFragment(UserState userState) {
        fileController = new FileController(userState);
    }

    public static FileFragment newInstance(UserState userState) {
        FileFragment fragment = new FileFragment(userState);
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