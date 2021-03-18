package com.cloud.disk.bundle.dynamic

import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.cloud.disk.api.file.TransferFile
import com.cloud.dynamicbundle.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@Route(path = "/dynamicBundle/dynamic")
@AndroidEntryPoint
class DynamicFragment : Fragment() {
    @Inject
    lateinit var dynamicController: DynamicController

    @Inject
    lateinit var transferFile: TransferFile

    private lateinit var btnUpload: Button
    private lateinit var dynamicListRecycleView: RecyclerView
    private lateinit var tvMessage: TextView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dynamic, container, false)
        btnUpload = view.findViewById(R.id.btn_upload)
        btnUpload.setOnClickListener { uploadDynamic() }
        dynamicListRecycleView = view.findViewById(R.id.file_list)
        tvMessage = view.findViewById(R.id.tv_message)
        tvMessage.setOnClickListener { getDynamicList() }
        getDynamicList()
        return view
    }

    private fun uploadDynamic() {
        //上传文件
        val fileInfo = transferFile.upload("/data/data/user.png")
        fileInfo?.let { dynamicController.post(Dynamic(0, "第一个动态", System.currentTimeMillis()), it) }
    }

    public fun getDynamicList() {
        Thread {
            val message = Message()
            val dynamicList = dynamicController.getDynamicList()
            message.what = 1
            message.obj = dynamicList
            mHandler.sendMessage(message)
        }.start()
    }

    var mHandler = Handler { msg ->
        if (msg.what == 1) {
            showTip(false)
            //显示网络数据
            var dynamicList = mutableListOf<Dynamic>()
            msg.obj?.let { dynamicList = msg.obj as MutableList<Dynamic> }
            if (dynamicList.isEmpty()) {
                showTip(true)
                //显示空数据
                tvMessage.text = "empty data"
            } else {
                val fileListAdapter = activity?.let { DynamicListAdapter(dynamicList, it) }
                dynamicListRecycleView.addItemDecoration(DividerItemDecoration(
                        activity, DividerItemDecoration.VERTICAL))
                //设置布局显示格式
                dynamicListRecycleView.layoutManager = LinearLayoutManager(activity)
                dynamicListRecycleView.adapter = fileListAdapter
                //从网络中更新到数据保持到缓存之中
                dynamicController.saveDynamicToCache(dynamicList)
            }
        } else if (msg.what == 0) {
            //尝试从缓存中读取数据
            val dynamicList = dynamicController.getDynamicListFromCache()
            if (dynamicList.isEmpty()) {
                showTip(true)
                //显示异常提醒数据
                tvMessage.text = msg.obj.toString()
            } else {
                val fileListAdapter = activity?.let { DynamicListAdapter(dynamicList, it) }
                dynamicListRecycleView.addItemDecoration(DividerItemDecoration(
                        activity, DividerItemDecoration.VERTICAL))
                //设置布局显示格式
                dynamicListRecycleView.layoutManager = LinearLayoutManager(activity)
                dynamicListRecycleView.adapter = fileListAdapter
            }
        }
        false
    }

    fun showTip(show: Boolean) {
        if (show) {
            tvMessage.visibility = View.VISIBLE
            dynamicListRecycleView.visibility = View.GONE
        } else {
            tvMessage.visibility = View.GONE
            dynamicListRecycleView.visibility = View.VISIBLE
        }
    }

    companion object {
        fun newInstance(): DynamicFragment {
            val fragment = DynamicFragment()
            val args = Bundle()
            fragment.arguments = args
            return fragment
        }
    }
}