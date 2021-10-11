package com.pok.onli.pokies.com.WebActivityPackage98po2

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.webkit.WebView

interface WebContract98po2 {
    interface WebMethods98po2 {
        fun webView98po2 (): WebView
        fun sharedPreferences98po2 (): SharedPreferences
        fun checkPermissions98po2 ()
        fun context98po2 (): Context
        fun startActivityForResult98po2 (intent98po2: Intent)
        fun intentString98po2 (): String?
        fun handleNetworkMissing98po2 ()
    }
    interface WebPresenter98po2 {

        fun setUpWebView98po2 ()
        fun setUpWebClient98po2 ()
        fun setUpWebChromeClient98po ()
        fun loadTheOne98po2 ()
        fun internetChecker98po2 ()
        fun isNetworkIsPresent98po2 ():Boolean
        fun stopChecking98po2 ()

    }
}