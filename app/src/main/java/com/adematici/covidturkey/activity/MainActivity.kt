package com.adematici.covidturkey.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.adematici.covidturkey.R
import com.adematici.covidturkey.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var navController: NavController
    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.navhostfragment)
        appBarConfiguration = AppBarConfiguration(
                setOf(R.id.istatistikFragment,R.id.belirtiFragment,R.id.korunmaFragment))

        binding.bottomNavigationView.setupWithNavController(navController)
    }

}