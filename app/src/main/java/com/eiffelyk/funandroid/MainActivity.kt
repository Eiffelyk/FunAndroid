package com.eiffelyk.funandroid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.eiffelyk.lib_base.service.webview.warp.WebViewWarpService
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var url: String = "https://www.dataenlighten.com"
    var title: String = "明觉科技"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            WebViewWarpService.instance.start(this, title, url)
        }
    }
}