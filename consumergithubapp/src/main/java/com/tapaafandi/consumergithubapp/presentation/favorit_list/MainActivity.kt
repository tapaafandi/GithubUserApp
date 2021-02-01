package com.tapaafandi.consumergithubapp.presentation.favorit_list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.tapaafandi.consumergithubapp.adapter.FavoriteAdapter
import com.tapaafandi.consumergithubapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var favoriteAdapter: FavoriteAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        favoriteAdapter = FavoriteAdapter()
        favoriteAdapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        binding.apply {
            rvFavoriteUser.setHasFixedSize(true)
            rvFavoriteUser.layoutManager = LinearLayoutManager(this@MainActivity)
            rvFavoriteUser.adapter = favoriteAdapter
        }

        viewModel.setUserFavorite(this)

        viewModel.getUserFavorite().observe(this, { listOfUser ->
            if (listOfUser != null) {
                favoriteAdapter.setData(listOfUser)
                binding.tvWelcome.visibility = View.GONE
                binding.ivGithubLogo.visibility = View.GONE
            }
        })
    }

}