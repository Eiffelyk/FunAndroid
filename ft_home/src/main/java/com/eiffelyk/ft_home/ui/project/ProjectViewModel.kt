package com.eiffelyk.ft_home.ui.project

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eiffelyk.ft_home.model.project.ProjectTabItem
import com.eiffelyk.lib_base.utils.BaseContext
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.launch

class ProjectViewModel(private val projectRepository: ProjectRepository) : ViewModel() {
    private val tabDataLiveData = MutableLiveData<MutableList<ProjectTabItem>>()

    fun getTabData(): MutableLiveData<MutableList<ProjectTabItem>> {
        viewModelScope.launch {
            val result = projectRepository.getTabData()
            if (result is NetResult.Success) {
                tabDataLiveData.postValue(result.data)
            } else if (result is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    result.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        return tabDataLiveData
    }
}