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
                saveDynamicToCache(dynamicList)
            } catch (e: NetWorkErrorException) {
                val dynamicCacheList = getDynamicListFromCache()
                if (dynamicCacheList.isNotEmpty()) {
                    dynamicListLiveData.value = dynamicCacheList
                } else {
                    errorMessageLiveData.value = "NetWorkErrorException"
                }
            }
        }

    }

    fun uploadDynamic() {
        //上传文件
        val fileInfo = transferFile.upload("/data/data/user.png")
        fileInfo?.let { dynamicRepository.post(Dynamic(0, "第一个动态", System.currentTimeMillis()), it) }
    }

    private fun getDynamicListFromCache(): List<Dynamic> {
        return dynamicRepository.getDynamicListFromCache()
    }

    private fun saveDynamicToCache(dynamicList: List<Dynamic>) {
        dynamicRepository.saveDynamicToCache(dynamicList)
    }

}
