package com.example.androiddevelopment.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.androiddevelopment.R
import com.example.androiddevelopment.databinding.ActivityMainBinding
import com.example.androiddevelopment.ui.fragment.*
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showFragment(R.id.container, HomeFragment.newInstance("", ""))

        binding.bottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_home -> {
                    showFragment(R.id.container, HomeFragment.newInstance("", ""))
                }
                R.id.menu_application -> {
                    showFragment(R.id.container, ApplicationFragment.newInstance("", ""))
                }
                R.id.menu_profile -> {
                    showFragment(R.id.container, ProfileFragment.newInstance("", ""))
                }
                R.id.menu_news -> {
                    showFragment(R.id.container, NewsFragment.newInstance("", ""))
                }
                R.id.menu_favorite -> {
                    showFragment(R.id.container, FavoriteFragment.newInstance("", ""))
                }
            }
            true
        }
    }

    fun showFragment(id: Int, fragment: Fragment){
        supportFragmentManager.beginTransaction().replace(id, fragment).commit()
    }
}
