package com.pok.onli.pokies.com.SplashActivityRep98po2

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.pok.onli.pokies.com.R
import com.pok.onli.pokies.com.WebActivityPackage98po2.WebActivity98po2
import com.pok.onli.pokies.com.databinding.ActivityMain98po2Binding
import kotlinx.coroutines.launch

@SuppressLint("CustomSplashScreen")
class SplashActivity98po2 : AppCompatActivity(), SplashContract98po2.SplashMethods98po2 {

    private lateinit var binding98po2: ActivityMain98po2Binding
    private lateinit var splashPresenterMethods98po2: SplashContract98po2.SplashPresenterMethods98po2
    private var animator98po2: ValueAnimator? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_98po2)
        getSharedPreferences("SP_98po2", MODE_PRIVATE).getString("Last_Page_98po2", null)?.let {
            startActivity(Intent(this, WebActivity98po2::class.java))
            finish()
        }
        binding98po2 = ActivityMain98po2Binding.inflate(layoutInflater)
        setContentView(binding98po2.root)
        splashPresenterMethods98po2 = SplashPresenter98po2(this)
        splashPresenterMethods98po2.thisEndsHere98po2()
    }

    override fun onClick98po2(clickScope98po2: () -> Unit) {
        binding98po2.b98po2.setOnClickListener {
            lifecycleScope.launch {
                animation98po2()
                binding98po2.b98po2.isClickable = false
                clickScope98po2()
            }
        }
    }

    override fun onStop() {
        animator98po2?.cancel()
        super.onStop()
    }

    private fun animation98po2 () {
        animator98po2 = ValueAnimator.ofInt(0,3).apply {
            repeatMode = ValueAnimator.RESTART
            repeatCount = ValueAnimator.INFINITE
            addUpdateListener {
                var s = "Loading"
                for (i in 0..it.animatedValue as Int) {
                    s += "."
                }
                binding98po2.b98po2.text = s
            }
            duration = 500
            start()
        }

        binding98po2.pb98po2.animate().alpha(1f)
    }

    override fun startNewActivity98po2(url98po2: String) {
        startActivity(Intent(this, WebActivity98po2::class.java).apply { putExtra("webURL98po2", url98po2) })
        finish()
    }

    override fun getPackageName98po2(): String {
        return packageName
    }
}