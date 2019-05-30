package com.example.webviewtest

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.appcompat.app.AppCompatActivity
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val webView: WebView by lazy {
        WebView(applicationContext)
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.layoutParams = ConstraintLayout.LayoutParams(
            ConstraintLayout.LayoutParams.MATCH_PARENT,
            ConstraintLayout.LayoutParams.MATCH_PARENT)
        parent_layout.addView(webView)

        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.webChromeClient = WebChromeClient()
        //os O 동작 x
//        webView.settings.apply {
//            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN
//            cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
//            setAppCacheEnabled(true)
//        }

        webView.loadUrl("https://renovatio0424.github.io/")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            webView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
                if (supportActionBar == null)
                    return@setOnScrollChangeListener

                if (scrollY > oldScrollY && supportActionBar!!.isShowing)
                    supportActionBar!!.hide()
                else if (scrollY < oldScrollY && !supportActionBar!!.isShowing)
                    supportActionBar!!.show()
            }
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}

