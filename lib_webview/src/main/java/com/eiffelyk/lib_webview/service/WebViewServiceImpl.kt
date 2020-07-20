package com.eiffelyk.lib_webview.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.eiffelyk.lib_base.service.ConstantsPath
import com.eiffelyk.lib_base.service.webview.WebViewService
import com.eiffelyk.lib_webview.WebViewActivity

@Route(path = ConstantsPath.WEB_VIEW_SERVICE_PATH)
class WebViewServiceImpl : WebViewService {
    override fun start(context: Context, title: String, url: String) {
        WebViewActivity.start(context, title, url)
    }

    override fun init(context: Context?) {

    }
}