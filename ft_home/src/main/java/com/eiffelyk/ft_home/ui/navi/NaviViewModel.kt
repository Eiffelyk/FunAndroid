package com.eiffelyk.ft_home.ui.navi

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eiffelyk.ft_home.model.nav.NavigationItem
import com.eiffelyk.lib_base.utils.BaseContext
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.launch

class NaviViewModel(private val naviRepository: NaviRepository) : ViewModel() {
    private val navigationLiveData = MutableLiveData<MutableList<NavigationItem>>()
    fun getNavigationData(): MutableLiveData<MutableList<NavigationItem>> {
        viewModelScope.launch {
            val data = naviRepository.getNavigationData()
            if (data is NetResult.Success) {
                navigationLiveData.postValue(data.data)
            } else if (data is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    data.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return navigationLiveData
    }
}