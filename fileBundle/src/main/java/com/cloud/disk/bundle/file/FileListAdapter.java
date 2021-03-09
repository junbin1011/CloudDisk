package com.cloud.disk.bundle.file;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.cloud.disk.api.file.FileInfo;
import com.cloud.disk.bundle.file.util.FileUtils;
import com.cloud.filebundle.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileListAdapter extends RecyclerView.Adapter<FileListAdapter.ViewHolder> {
    public List<FileInfo> infoList = new ArrayList<>();
    public Context context;
    public LayoutInflater inflater;

    public FileListAdapter(List<FileInfo> list, Context context) {
        this.context = context;
        this.infoList = list;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.file_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.tvName.setText(infoList.get(position).fileName);
        holder.tvSize.setText(FileUtils.formatFileSize(infoList.get(position).fileSize) + "");
    }

    @Override
    public int getItemCount() {
        return infoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;
        public TextView tvSize;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvSize = itemView.findViewById(R.id.tv_size);
        }
    }

}
