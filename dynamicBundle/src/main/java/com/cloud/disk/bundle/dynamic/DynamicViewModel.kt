package com.cloud.disk.bundle.dynamic

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cloud.disk.api.file.TransferFile
import com.cloud.disk.bundle.dynamic.data.DynamicRepository
import com.cloud.disk.bundle.dynamic.exception.NetWorkErrorException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DynamicViewModel @Inject internal constructor(
        private val transferFile: TransferFile,
        private val dynamicRepository: DynamicRepository) : ViewModel() {


    val dynamicListLiveData: MutableLiveData<List<Dynamic>> = MutableLiveData()

    val errorMessageLiveData: MutableLiveData<String> = MutableLiveData();

    fun getDynamicList() {
        viewModelScope.launch {
            try {
                val dynamicList = dynamicRepository.getDynamicList()
                dynamicListLiveData.value = dynamicList
                dynamicRepository.saveDynamicToCache(dynamicList)
            } catch (e: NetWorkErrorException) {
                val dynamicCacheList = dynamicRepository.getDynamicListFromCache()
                if (dynamicCacheList.isNullOrEmpty()) {
                    errorMessageLiveData.value = "NetWorkErrorException"
                } else {
                    dynamicListLiveData.value = dynamicCacheList
                }
            }
        }

    }

    fun uploadDynamic() {
        //上传文件
        val fileInfo = transferFile.upload("/data/data/user.png")
        fileInfo?.let { dynamicRepository.post(Dynamic(0, "第一个动态", System.currentTimeMillis()), it) }
    }

}
