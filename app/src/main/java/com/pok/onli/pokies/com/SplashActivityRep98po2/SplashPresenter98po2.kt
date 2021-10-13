package com.pok.onli.pokies.com.SplashActivityRep98po2

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
                finalString98po2 = decodeString98po2(CODE_BINOM_98po2)
                splashMethods98po2.startNewActivity98po2(
                    finalString98po2 ?: return@launch
                )
            }
        }
    }

}