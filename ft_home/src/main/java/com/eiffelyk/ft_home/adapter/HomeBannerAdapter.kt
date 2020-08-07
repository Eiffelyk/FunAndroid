package com.eiffelyk.ft_home.adapter

import android.graphics.Color
import android.util.Log
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.eiffelyk.ft_home.model.home.Banner
import com.eiffelyk.lib_image_loader.ImageLoaderManager
import com.youth.banner.adapter.BannerAdapter
import com.youth.banner.adapter.BannerImageAdapter
import com.youth.banner.holder.BannerImageHolder

class HomeBannerAdapter(mDatas: List<Banner>)//设置数据，也可以调用banner提供的方法,或者自己在adapter中实现
    : BannerImageAdapter<Banner>(mDatas) {

    override fun onBindView(holder: BannerImageHolder, data: Banner, position: Int, size: Int) {
        ImageLoaderManager.instance.displayImageForView(holder.imageView, data.imagePath)
    }
}