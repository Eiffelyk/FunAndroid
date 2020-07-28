package com.eiffelyk.lib_webview

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.http.SslError
import android.os.Bundle
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.eiffelyk.lib_base.utils.StatusBarKt
import kotlinx.android.synthetic.main.activity_web_view.*


class WebViewActivity : AppCompatActivity() {
    private lateinit var title: String
    private lateinit var url: String

    companion object {
        fun start(context: Context, title: String, url: String) {
            val intent = Intent(context, WebViewActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("url", url)
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ARouter.getInstance().inject(this)
        title = intent.getStringExtra("title").toString()
        url = intent.getStringExtra("url").toString()
        setContentView(R.layout.activity_web_view)
        initActionBar()
        initWebView()
    }

    @SuppressLint("JavascriptInterface", "SetJavaScriptEnabled")
    private fun initWebView() {
        val setting = webView.settings
        setting.javaScriptEnabled = true
        setting.allowContentAccess = true
        setting.domStorageEnabled = true
        setting.allowFileAccess = true
        webView.addJavascriptInterface(this,"wan")
        webView.webChromeClient =object :WebChromeClient(){

        }
        webView.webViewClient =object : WebViewClient(){
            override fun onReceivedSslError(
                view: WebView?,
                handler: SslErrorHandler?,
                error: SslError?
            ) {
                handler?.proceed()
            }
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return super.shouldOverrideUrlLoading(view, request)
            }
        }
        webView.loadUrl(url)
    }

    private fun initActionBar() {
        titleText.text = title
        back.setOnClickListener { finish() }
    }
}