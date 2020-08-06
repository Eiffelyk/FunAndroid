package com.eiffelyk.ft_home.adapter

import android.content.Context
import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_home.`interface`.NavigationTabItemSelectedListener
import com.eiffelyk.ft_home.databinding.FragmentNavRecyclerItemBinding
import com.eiffelyk.ft_home.model.nav.NavigationItem

class NavigationTabAdapter(context: Context, data: MutableList<NavigationItem>) :
    RecyclerView.Adapter<NavigationTabAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View, binding: FragmentNavRecyclerItemBinding) :
        RecyclerView.ViewHolder(itemView) {
        private val mBinding = binding
        fun setData(item: NavigationItem): Unit {
            mBinding.nameTxt = item.name
            if (adapterPosition == isSelectedPosition) {
                mBinding.fragmentNavRecyclerItem.isSelected = true
                mBinding.fragmentNavRecyclerItem.typeface = Typeface.DEFAULT_BOLD
            } else {
                mBinding.fragmentNavRecyclerItem.isSelected = false
                mBinding.fragmentNavRecyclerItem.typeface = Typeface.DEFAULT
            }
            mBinding.executePendingBindings()
            mBinding.fragmentNavRecyclerItem.setOnClickListener {
                mListener.onItemSelected(item, adapterPosition)
            }
        }
    }

    private var isSelectedPosition = 0
    private lateinit var mListener: NavigationTabItemSelectedListener
    private val mContext = context
    private val inflater = LayoutInflater.from(mContext)
    private val mData = data
    fun setItemSelectedListener(listener: NavigationTabItemSelectedListener): Unit {
        this.mListener = listener
    }

    fun setItemPositionSelected(position: Int) {
        isSelectedPosition = position
    }

    fun getItemPositionSelected(): Int {
        return isSelectedPosition
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NavigationTabAdapter.ViewHolder {
        val binding = FragmentNavRecyclerItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding.root, binding)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: NavigationTabAdapter.ViewHolder, position: Int) {
        holder.setData(mData[position])
    }
}