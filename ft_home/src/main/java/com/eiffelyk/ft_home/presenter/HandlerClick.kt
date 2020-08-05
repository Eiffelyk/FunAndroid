package com.eiffelyk.ft_home.presenter

import android.content.Context
import com.eiffelyk.lib_base.service.webview.warp.WebViewWarpService

object HandlerClick {
    fun navigationItemClick(context: Context, title: String, url: String): Unit {
        WebViewWarpService.instance.start(context, title, url)
    }
}