package com.pok.onli.pokies.com.ApplicationPackage98po2

import android.app.Application
import android.content.Context

class Aplication98po2 : Application(), ApplicationContract98po2.ApplicationMethods98po2 {

    private lateinit var applicationSetupMethods98po2: ApplicationContract98po2.ApplicationSetupMethods98po2

    override fun onCreate() {
        applicationSetupMethods98po2 = ApplicationSetuper98po2(this)
        applicationSetupMethods98po2.afSetup98po2()
        applicationSetupMethods98po2.maSetup98po2()
        applicationSetupMethods98po2.osSetup98po2()
        super.onCreate()
    }

    override fun getContext98po2(): Context  = this

}