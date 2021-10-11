package com.pok.onli.pokies.com.WebActivityPackage98po2

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.net.http.SslError
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.webkit.*
import androidx.core.content.FileProvider
import com.pok.onli.pokies.com.Utils98po2.filePathCallBack98po2
import com.pok.onli.pokies.com.Utils98po2.uriView98po2
import kotlinx.coroutines.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class WebPresenter98po2(private val webMethods98po2: WebContract98po2.WebMethods98po2) : WebContract98po2.WebPresenter98po2 {

    private var job98po2: Job? = null

    override fun setUpWebView98po2() {
        webMethods98po2.webView98po2().run {
            settings.run {
                loadWithOverviewMode = true
                displayZoomControls = false
                useWideViewPort = true
                javaScriptEnabled = true
                loadsImagesAutomatically = true
                cacheMode = WebSettings.LOAD_CACHE_ELSE_NETWORK
                builtInZoomControls = true
                displayZoomControls = false
                domStorageEnabled = true
                mediaPlaybackRequiresUserGesture = false
            }

            scrollBarStyle = View.SCROLLBARS_INSIDE_OVERLAY
        }
    }

    override fun setUpWebClient98po2() {
        webMethods98po2.webView98po2().webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(
                view98po2: WebView?,
                request98po2: WebResourceRequest?
            ): Boolean {
                val prohibitedLinks98po2 = listOf("instagram", "facebook", "youtube", "viber", "tg:resolve")
                val modifiedLinks98po2 = listOf("mailto:", "tel:")
                return when {
                    prohibitedLinks98po2.filter {
                        request98po2?.url.toString().contains(it)
                    }.isNotEmpty() -> true
                    modifiedLinks98po2.filter {
                        request98po2
                            ?.url.toString().startsWith(it)
                    }.isNotEmpty() -> {
                        view98po2?.context?.startActivity(
                            Intent(
                                Intent.ACTION_VIEW,
                                request98po2?.url
                            )
                        )
                        true
                    }
                    else -> false
                }
            }

            override fun onPageFinished(view98po2: WebView?, url98po2: String?) {
                webMethods98po2.sharedPreferences98po2().edit().putString("Last_Page_98po2", url98po2?: return).apply()
                super.onPageFinished(view98po2, url98po2)
            }

            override fun onReceivedSslError(
                view98po2: WebView?,
                handler98po2: SslErrorHandler?,
                error98po2: SslError?
            ) {
                handler98po2?.proceed()
                super.onReceivedSslError(view98po2, handler98po2, error98po2)
            }
        }
    }

    override fun setUpWebChromeClient98po() {
        webMethods98po2.webView98po2().webChromeClient = object : WebChromeClient() {
            override fun onShowFileChooser(
                webView98po2: WebView?,
                filePathCallback98po2: ValueCallback<Array<Uri>>?,
                fileChooserParams98po2: FileChooserParams?
            ): Boolean {
                webMethods98po2.checkPermissions98po2()
                filePathCallBack98po2 = filePathCallback98po2
                val captureIntent98po2 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
                if (captureIntent98po2.resolveActivity(webMethods98po2.context98po2().packageManager) != null) {
                    val providedFile98po2 = createTempFile98po2()
                    uriView98po2 = FileProvider.getUriForFile(
                        webMethods98po2.context98po2(),
                        "${webMethods98po2.context98po2().packageName}.provider",
                        providedFile98po2
                    )
                    captureIntent98po2.run {
                        putExtra(MediaStore.EXTRA_OUTPUT, uriView98po2)
                        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                    }
                    val actionIntent98po2 = Intent(Intent.ACTION_GET_CONTENT).apply {
                        addCategory(Intent.CATEGORY_OPENABLE)
                        type = "image/*"
                    }
                    val intentChooser98po2 = Intent(Intent.ACTION_CHOOSER).apply {
                        putExtra(Intent.EXTRA_INTENT, captureIntent98po2)
                        putExtra(Intent.EXTRA_INITIAL_INTENTS, arrayOf(actionIntent98po2))
                    }
                    webMethods98po2.startActivityForResult98po2(intentChooser98po2)
                    return true

                }

                return super.onShowFileChooser(
                    webView98po2,
                    filePathCallback98po2,
                    fileChooserParams98po2
                )
            }
        }
    }

    override fun loadTheOne98po2() {
        webMethods98po2.sharedPreferences98po2().getString("Last_Page_98po2",null)?.let {
                lastpage98po2 ->
            webMethods98po2.webView98po2().loadUrl(lastpage98po2)
            return
        }
        webMethods98po2.webView98po2().loadUrl(webMethods98po2.intentString98po2() ?: return)
    }

    override fun internetChecker98po2() {
        job98po2 = CoroutineScope(Dispatchers.IO).launch {
            while (isActive) {
                delay(500)
                isNetworkIsPresent98po2().run {
                    if (!this) {
                        webMethods98po2.handleNetworkMissing98po2()
                        cancel()
                    }
                }
            }
        }
    }

    override fun isNetworkIsPresent98po2(): Boolean {
        val connectivityManager98po2 = webMethods98po2.context98po2().getSystemService(
            Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val networkCapabilities98po2 = connectivityManager98po2.getNetworkCapabilities(connectivityManager98po2.activeNetwork) ?: return false
            return networkCapabilities98po2.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
        } else {
            for (network98po2 in connectivityManager98po2.allNetworks) {
                connectivityManager98po2.getNetworkInfo(network98po2)?.let {
                    if (it.isConnected) return true
                }
            }
            return false
        }
    }

    override fun stopChecking98po2() {
        job98po2?.cancel()
    }

    private fun createTempFile98po2(): File {
        val date98po2 = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault())
            .format(Date())
        val fileDir98po2 = webMethods98po2.context98po2().getExternalFilesDir(
            Environment.DIRECTORY_PICTURES)
        return File.createTempFile("TMP${date98po2}_98po2", ".jpg", fileDir98po2)
    }
}