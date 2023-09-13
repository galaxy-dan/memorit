package com.galaxy.memorit.ui.main

import android.net.http.SslError
import android.os.Build
import android.view.ViewGroup
import android.webkit.SslErrorHandler
import android.webkit.WebResourceRequest
import android.webkit.WebView
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.web.AccompanistWebViewClient
import com.google.accompanist.web.rememberWebViewNavigator
import com.google.accompanist.web.rememberWebViewState


class WebViewClient(): AccompanistWebViewClient() {

    override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
        super.onReceivedSslError(view, handler, error)
        handler?.proceed()
    }

    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
        return super.shouldOverrideUrlLoading(view, request)
        view!!.loadUrl(request!!.url.toString())
        return true
    }

}
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MainLayout(modifier: Modifier = Modifier, viewmodel: MainViewmodel = hiltViewModel()) {


    var webView: WebView? = null
//    val url = "www.naver.com"
    val url = "https://moku--moku.vercel.app/"
    val webViewClients = WebViewClient()
    val webViewNavigator = rememberWebViewNavigator()
    val webViewState = rememberWebViewState(url = url)
    val backHandler =
        BackHandler {
            if (webViewNavigator.canGoBack) {
                webViewNavigator.navigateBack()
            } else {

            }
        }
    webViewClients.state = webViewState
    webViewClients.navigator = webViewNavigator

    AndroidView(factory = {
        WebView(it).apply {
            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            // to play video on a web view
            settings.javaScriptEnabled = true
            settings.loadWithOverviewMode = true
            settings.useWideViewPort = true
            settings.domStorageEnabled = true

            // to verify that the client requesting your web page is actually your Android app.
            settings.userAgentString =
                System.getProperty("http.agent") //Dalvik/2.1.0 (Linux; U; Android 11; M2012K11I Build/RKQ1.201112.002)

            settings.useWideViewPort = true




            webViewClient = webViewClients
            loadUrl(url)
            webView = this
        }
    }, update = {
        webView = it
    })
}
@Composable
@Preview
fun MainLayoutPreview() {
    MainLayoutPreview()
}