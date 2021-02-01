package com.tapaafandi.githubuserapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavigationSetup()
    }

    private fun bottomNavigationSetup() {
        val navController = findNavController(R.id.nav_host_fragment)
        binding.bnView.setupWithNavController(navController)
    }

}