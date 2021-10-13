package com.pok.onli.pokies.com.ApplicationPackage98po2

import com.onesignal.OneSignal
import com.pok.onli.pokies.com.Utils98po2.CODED_ONESIGNAL_98po2
import com.pok.onli.pokies.com.Utils98po2.decodeString98po2

class ApplicationSetuper98po2(private val applicationMethods98po2: ApplicationContract98po2.ApplicationMethods98po2) : ApplicationContract98po2.ApplicationSetupMethods98po2 {


    override fun osSetup98po2() {
        OneSignal.initWithContext(applicationMethods98po2.getContext98po2())
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        OneSignal.setAppId(decodeString98po2(CODED_ONESIGNAL_98po2))
    }

}