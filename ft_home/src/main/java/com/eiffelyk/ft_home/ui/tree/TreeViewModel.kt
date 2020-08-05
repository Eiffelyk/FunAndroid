package com.eiffelyk.ft_home.ui.tree

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eiffelyk.ft_home.model.tree.TreeData
import com.eiffelyk.lib_base.utils.BaseContext
import com.eiffelyk.lib_net.model.NetResult
import kotlinx.coroutines.launch

class TreeViewModel(private val treeRepository: TreeRepository) : ViewModel() {

    private val treeLiveData = MutableLiveData<MutableList<TreeData>>()

    fun getTreeDataLive(): MutableLiveData<MutableList<TreeData>> {
        return treeLiveData
    }

    fun getTreeList(): Unit {
        viewModelScope.launch {
            var treeData = treeRepository.getTreeList()
            if (treeData is NetResult.Success) {
                treeLiveData.postValue(treeData.data)
            } else if (treeData is NetResult.Error) {
                Toast.makeText(
                    BaseContext.instance.getContext(),
                    treeData.exception.errorMsg,
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    }
}