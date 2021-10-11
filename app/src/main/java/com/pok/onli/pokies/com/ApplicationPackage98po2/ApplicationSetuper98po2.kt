package com.pok.onli.pokies.com.ApplicationPackage98po2

import com.appsflyer.AppsFlyerConversionListener
import com.appsflyer.AppsFlyerLib
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.identifier.AdvertisingIdClient
import com.onesignal.OneSignal
import com.pok.onli.pokies.com.Utils98po2.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ApplicationSetuper98po2(private val applicationMethods98po2: ApplicationContract98po2.ApplicationMethods98po2) : ApplicationContract98po2.ApplicationSetupMethods98po2 {

    override fun afSetup98po2() {
        val conversionListener98po2 = object : AppsFlyerConversionListener {
            override fun onConversionDataSuccess(p0: MutableMap<String, Any>?) {
                p0?.run {
                    status98po2 =
                        if (getValue(APPSFLYER_STATUS_TAG_98po2).toString() == "Organic") "Organic" else "Non-organic"

                    getValue(APPSFLYER_CAMPAIGN_TAG_98po2)
                        .toString()
                        .split("||").drop(1)
                        .map {
                            it.split(":")[1]
                        }.let { list98po2 ->
                            key98po2 =
                                if (list98po2.isNotEmpty()) list98po2[0] else "empty"
                            sub298po2 =
                                if (list98po2.size >= 2) list98po2[1] else "empty"
                            sub398po2 =
                                if (list98po2.size >= 3) list98po2[2] else "empty"
                        }


                    mediaSource98po2 = getValue(APPSFLYER_MEDIA_SOURCE_TAG_98po2).toString()
                    afChannel98po2 = getValue(APPSFLYER_AF_CHANNEL_TAG_98po2).toString()
                    adGroup98po2 = getValue(APPSFLYER_ADGROUP_TAG_98po2).toString()
                    adSet98po2 = getValue(APPSFLYER_ADSET_TAG_98po2).toString()

                }
            }

            override fun onConversionDataFail(p0: String?) {
            }

            override fun onAppOpenAttribution(p0: MutableMap<String, String>?) {
            }

            override fun onAttributionFailure(p0: String?) {
            }
        }
        AppsFlyerLib.getInstance().run {
            uid98po2 = getAppsFlyerUID(applicationMethods98po2.getContext98po2())
            init(
                decodeString98po2(CODE_APPSFLYER_98po2),
                conversionListener98po2,
                applicationMethods98po2.getContext98po2()
            )
            start(applicationMethods98po2.getContext98po2())
        }
    }

    override fun osSetup98po2() {
        OneSignal.initWithContext(applicationMethods98po2.getContext98po2())
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId(decodeString98po2(CODED_ONESIGNAL_98po2))
    }

    override fun maSetup98po2() {
        MobileAds.initialize(applicationMethods98po2.getContext98po2())
        CoroutineScope(Dispatchers.IO).launch {
            try {
                AID98po2 =
                    AdvertisingIdClient.getAdvertisingIdInfo(applicationMethods98po2.getContext98po2())?.id
            } catch (e98po2: Exception) {

            }
        }
    }
}