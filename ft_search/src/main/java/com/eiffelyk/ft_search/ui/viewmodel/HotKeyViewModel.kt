package com.eiffelyk.ft_search.ui.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eiffelyk.ft_search.model.HotKeyModel
import com.eiffelyk.ft_search.ui.repository.SearchRepository
import com.eiffelyk.lib_base.utils.BaseContext
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.launch

class HotKeyViewModel(private val searchRepository: SearchRepository) : ViewModel() {
    private val hotKeyLiveData = MutableLiveData<MutableList<HotKeyModel>>()

    fun getHotKey(): MutableLiveData<MutableList<HotKeyModel>> {
        viewModelScope.launch {
            val hotKey = searchRepository.getHotKey()
            if (hotKey is NetResult.Success) {
                hotKeyLiveData.postValue(hotKey.data)
            } else if (hotKey is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    hotKey.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return hotKeyLiveData
    }
}