package com.pok.onli.pokies.com.WebActivityPackage98po2

import android.Manifest
import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import androidx.core.animation.doOnEnd
import androidx.lifecycle.lifecycleScope
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.PermissionRequest
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import com.pok.onli.pokies.com.R
import com.pok.onli.pokies.com.Utils98po2.filePathCallBack98po2
import com.pok.onli.pokies.com.Utils98po2.uriView98po2
import com.pok.onli.pokies.com.databinding.ActivityWebActivity98po2Binding
import kotlinx.coroutines.launch

class WebActivity98po2 : AppCompatActivity(), WebContract98po2.WebMethods98po2 {

    private lateinit var binding98po2: ActivityWebActivity98po2Binding
    private lateinit var webViewPresenter98po2: WebContract98po2.WebPresenter98po2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding98po2 = ActivityWebActivity98po2Binding.inflate(layoutInflater)
        setContentView(binding98po2.root)
        webViewPresenter98po2 = WebPresenter98po2(this)
        webViewPresenter98po2.setUpWebView98po2()
        webViewPresenter98po2.setUpWebClient98po2()
        webViewPresenter98po2.setUpWebChromeClient98po()
        webViewPresenter98po2.loadTheOne98po2()
        binding98po2.srl98po2.setOnRefreshListener {
            binding98po2.wv98po2.loadUrl(
                binding98po2.wv98po2.url ?: return@setOnRefreshListener
            )
            binding98po2.srl98po2.isRefreshing = false
        }
    }

    override fun onResume() {
        webViewPresenter98po2.internetChecker98po2()
        super.onResume()
    }

    override fun onPause() {
        webViewPresenter98po2.stopChecking98po2()
        super.onPause()
    }

    override fun webView98po2(): WebView {
        return binding98po2.wv98po2
    }

    override fun sharedPreferences98po2(): SharedPreferences {
        return getSharedPreferences("SP_98po2", MODE_PRIVATE)
    }

    override fun checkPermissions98po2() {
        Dexter.withContext(this)
            .withPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .withListener(object : MultiplePermissionsListener {
                override fun onPermissionsChecked(p0: MultiplePermissionsReport?) {
                }

                override fun onPermissionRationaleShouldBeShown(
                    p0: MutableList<PermissionRequest>?,
                    p1: PermissionToken?
                ) {

                }
            }).check()
    }

    override fun context98po2(): Context {
        return this
    }

    override fun startActivityForResult98po2(intent98po2: Intent) {
        startActivityForResult(intent98po2, 0)
    }

    override fun intentString98po2(): String? {
        return intent.getStringExtra("webURL98po2")
    }

    override fun handleNetworkMissing98po2() {
        lifecycleScope.launch {
            binding98po2.srl98po2.animate().alpha(0f).run {
                duration = 500
            }
            binding98po2.wv98po2.animate().alpha(0f).run {
                duration = 500
            }
            binding98po2.tvNoConnectionLabel98po2.animate().alpha(1f).run {
                startDelay = 500
                duration = 500
            }
            binding98po2.bInternet98po2.animate().alpha(1f).run {
                startDelay = 500
                duration = 500
            }
            binding98po2.lanimationInternet98po2.animate().alpha(1f).run {
                startDelay = 500
                duration = 500
            }
            ValueAnimator.ofFloat(0f, 1f).run {
                duration = 1000
                addUpdateListener {
                    binding98po2.lanimationInternet98po2.progress = it.animatedValue as Float
                }
                start()
            }
            binding98po2.bInternet98po2.setOnClickListener {
                if (webViewPresenter98po2.isNetworkIsPresent98po2()) {
                    ValueAnimator.ofArgb(
                        resources.getColor(R.color.theme_2_98po2),
                        resources.getColor(R.color.theme_green_98po2)
                    ).run {
                        duration = 500
                        repeatCount = 1
                        repeatMode = ValueAnimator.REVERSE
                        setEvaluator(ArgbEvaluator())
                        addUpdateListener {
                            binding98po2.root.setBackgroundColor(it.animatedValue as Int)
                        }
                        doOnEnd {
                            binding98po2.srl98po2.animate().alpha(1f).run {
                                startDelay = 300
                                duration = 500
                            }
                            binding98po2.wv98po2.animate().alpha(1f).run {
                                startDelay = 300
                                duration = 500
                            }
                            binding98po2.tvNoConnectionLabel98po2.animate().alpha(0f).run {
                                duration = 500
                            }
                            binding98po2.bInternet98po2.animate().alpha(0f).run {
                                duration = 500
                            }
                            binding98po2.lanimationInternet98po2.animate().alpha(0f).run {
                                duration = 500
                            }
                            webViewPresenter98po2.internetChecker98po2()
                        }
                        start()
                    }
                    ValueAnimator.ofFloat(1f, 0f).run {
                        duration = 1000
                        addUpdateListener {
                            binding98po2.lanimationInternet98po2.progress =
                                it.animatedValue as Float
                        }
                        start()
                    }
                } else {
                    ValueAnimator.ofArgb(
                        resources.getColor(R.color.theme_2_98po2),
                        resources.getColor(R.color.theme_red_98po2)
                    ).run {
                        duration = 500
                        repeatCount = 1
                        repeatMode = ValueAnimator.REVERSE
                        setEvaluator(ArgbEvaluator())
                        addUpdateListener {
                            binding98po2.root.setBackgroundColor(it.animatedValue as Int)
                        }
                        start()
                    }
                }
            }
        }
    }

    override fun onBackPressed() {
        return if (binding98po2.wv98po2.canGoBack())
            binding98po2.wv98po2.goBack() else
            super.onBackPressed()
    }

    override fun onActivityResult(requestCode98po2: Int, resultCode98po2: Int, data98po2: Intent?) {
        if (filePathCallBack98po2 != null && requestCode98po2 == 0) {
            val uriResult98po2 =
                if (data98po2 == null || resultCode98po2 != RESULT_OK) null else data98po2.data
            if (uriResult98po2 != null) {
                filePathCallBack98po2?.onReceiveValue(arrayOf(uriResult98po2))
            } else {
                filePathCallBack98po2?.onReceiveValue(arrayOf(uriView98po2))
            }
            filePathCallBack98po2 = null
        }
        super.onActivityResult(requestCode98po2, resultCode98po2, data98po2)
    }
}