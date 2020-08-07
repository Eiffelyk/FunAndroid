package com.eiffelyk.ft_home.ui.home

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eiffelyk.ft_home.model.home.Banner
import com.eiffelyk.lib_base.utils.BaseContext
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.launch

class HomeViewModel(private val homeRepository: HomeRepository) : ViewModel() {
    private val bannerLiveData = MutableLiveData<MutableList<Banner>>()

    fun getBannerLiveData(): MutableLiveData<MutableList<Banner>> {
        return bannerLiveData
    }

    fun getBanner(): Unit {
        viewModelScope.launch {
            val banner = homeRepository.getBanner()
            if (banner is NetResult.Success) {
                bannerLiveData.postValue(banner.data)
            } else if (banner is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    banner.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}