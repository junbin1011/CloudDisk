package com.cloud.disk.bundle.dynamic

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.cloud.dynamicbundle.databinding.FragmentDynamicBinding
import dagger.hilt.android.AndroidEntryPoint

@Route(path = "/dynamicBundle/dynamic")
@AndroidEntryPoint
class DynamicFragment : Fragment() {

    private lateinit var binding: FragmentDynamicBinding
    private val dynamicViewModel: DynamicViewModel by viewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentDynamicBinding.inflate(inflater, container, false)
        binding.btnUpload.setOnClickListener { dynamicViewModel.uploadDynamic() }
        binding.tvMessage.setOnClickListener { dynamicViewModel.getDynamicList() }
        dynamicViewModel.getDynamicList()
        subscribeUi()
        return binding.root
    }

    private fun subscribeUi() {
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
        binding.showTip = true
        //显示异常提醒数据
        binding.tvMessage.text = errorMessage
    }

    private fun showDynamicList(dynamicList: List<Dynamic>) {
        binding.showTip = false
        val dynamicListAdapter = DynamicListAdapter()
        dynamicListAdapter.submitList(dynamicList)
        binding.dynamicList.addItemDecoration(DividerItemDecoration(
                activity, DividerItemDecoration.VERTICAL))
        //设置布局显示格式
        binding.dynamicList.layoutManager = LinearLayoutManager(activity)
        binding.dynamicList.adapter = dynamicListAdapter
    }

    private fun showEmptyData() {
        binding.showTip = true
        //显示空数据
        binding.tvMessage.text = "empty data"
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