package com.example.androiddevelopment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.ImageView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.androiddevelopment.R
import com.example.androiddevelopment.databinding.ActivitySplashScreenBinding
import com.pixplicity.easyprefs.library.Prefs


class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        splashScreen.setKeepOnScreenCondition{true}

        var isLogin = Prefs.getBoolean("isLogin", false)
        if(isLogin){
            openMainActivity()
        }else{
            openLoginActivity()
        }

//        val splashImg : ImageView = findViewById(R.id.logo_splash)
//        splashImg.alpha = 0f
//        splashImg.animate().setDuration(1500).alpha(1f).withEndAction {
//            val i = Intent(this, LoginActivity::class.java)
//            startActivity(i)
//            overridePendingTransition(android.R.anim.fade_in,android.R.anim.fade_out)
//            finish()
//        }

    }

    private fun openMainActivity(){
        val intent = Intent(this@SplashScreen, MainActivity::class.java)
        startActivity(intent)
    }

    private fun openLoginActivity(){
        val intent = Intent(this@SplashScreen, LoginActivity::class.java)
        startActivity(intent)
    }
}