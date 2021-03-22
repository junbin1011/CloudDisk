package com.cloud.disk.bundle.dynamic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alibaba.android.arouter.facade.annotation.Route
import com.cloud.dynamicbundle.R
import dagger.hilt.android.AndroidEntryPoint

@Route(path = "/dynamicBundle/dynamic")
@AndroidEntryPoint
class DynamicFragment : Fragment() {


    private lateinit var btnUpload: Button
    private lateinit var dynamicListRecycleView: RecyclerView
    private lateinit var tvMessage: TextView
    private val dynamicViewModel: DynamicViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_dynamic, container, false)
        btnUpload = view.findViewById(R.id.btn_upload)
        btnUpload.setOnClickListener { dynamicViewModel.uploadDynamic() }
        dynamicListRecycleView = view.findViewById(R.id.file_list)
        tvMessage = view.findViewById(R.id.tv_message)
        tvMessage.setOnClickListener { dynamicViewModel.getDynamicList() }
        dynamicViewModel.getDynamicList()
        bindView()
        return view
    }

    private fun bindView() {
        dynamicViewModel.dynamicListLiveData.observe(viewLifecycleOwner, {
            if (it.isNullOrEmpty()) {
                showEmptyData()
            } else {
                showDynamicList(it)
            }
        })
        dynamicViewModel.errorMessageLiveData.observe(viewLifecycleOwner, {
            showErrorMessage(it)
        })
    }


    private fun showErrorMessage(errorMessage: String) {
        showTip(true)
        //显示异常提醒数据
        tvMessage.text = errorMessage
    }

    private fun showDynamicList(dynamicList: List<Dynamic>) {
        showTip(false)
        val fileListAdapter = activity?.let { DynamicListAdapter(dynamicList, it) }
        dynamicListRecycleView.addItemDecoration(DividerItemDecoration(
                activity, DividerItemDecoration.VERTICAL))
        //设置布局显示格式
        dynamicListRecycleView.layoutManager = LinearLayoutManager(activity)
        dynamicListRecycleView.adapter = fileListAdapter
    }

    private fun showEmptyData() {
        showTip(true)
        //显示空数据
        tvMessage.text = "empty data"
    }


    private fun showTip(show: Boolean) {
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