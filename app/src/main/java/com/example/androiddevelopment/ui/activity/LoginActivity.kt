package com.example.androiddevelopment.ui.activity

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.util.Patterns
import android.widget.Toast
import com.example.androiddevelopment.databinding.ActivityLoginBinding
import com.pixplicity.easyprefs.library.Prefs

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtSignUp.text = Html.fromHtml("<font color=${Color.BLACK}>Don't have an account ? </font>" +
                "<font color=${"#006EFF"}> Sign up</font>")

        binding.btnLogin.setOnClickListener {
            if (binding.txtPassword.text.toString().isNotEmpty() && binding.txtEmail.text.toString().isNotEmpty()){
                if (Patterns.EMAIL_ADDRESS.matcher(binding.txtEmail.text.toString()).matches()) {
                    Prefs.putBoolean("isLogin", true)

                    Toast.makeText(this@LoginActivity, "Login Success", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this@LoginActivity, MainActivity::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this@LoginActivity, "Email is not valid", Toast.LENGTH_SHORT).show()
                }

            }else{
                Toast.makeText(this@LoginActivity, "Password is empty or Email is empty", Toast.LENGTH_SHORT).show()
            }
        }

        binding.txtLoginGuest.setOnClickListener{
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            startActivity(intent)
        }
    }
}