package com.tapaafandi.githubuserapp.presentation.ui.detail_user

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.tapaafandi.githubuserapp.R
import com.tapaafandi.githubuserapp.adapter.DetailUserViewPagerAdapter
import com.tapaafandi.githubuserapp.databinding.ActivityDetailUserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class DetailUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var detailUserViewPagerAdapter: DetailUserViewPagerAdapter
    private lateinit var viewPager: ViewPager

    private val viewModel: DetailUserViewModel by viewModels()
    private var favoriteState = false

    companion object {
        const val EXTRA_DETAIL = "extra_detail"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupActionBar()
        viewPagerConfiguration()
        observeDataDetailUser()

        val username = intent.getStringExtra(EXTRA_DETAIL)
        requestUserDetail(username)
        checkUserFromDatabase(username)
    }

    private fun checkUserFromDatabase(username: String?) {
        if (username != null) {
            lifecycleScope.launch(Dispatchers.IO) {
                val result = viewModel.checkFavoriteUser(username)
                withContext(Dispatchers.Main) {
                    favoriteState = if (result.isNotEmpty()) {
                        isFavorite(true)
                        true
                    } else {
                        isFavorite(false)
                        false
                    }
                }

            }
        }
    }

    private fun requestUserDetail(username: String?) {
        if (username != null) {
            lifecycleScope.launch {
                favoriteState = false
                isFavorite(false)
                viewModel.getGithubDetailUser(username)
            }
            sendToFollowersFollowingViewPagerAdapter(username)
        }
    }

    private fun observeDataDetailUser() {
        viewModel.githubUser.observe(this, { user ->
            with(binding) {
                tvName.text = user.name
                tvUsername.text = user.username
                tvRepository.text = user.repository.toString()
                tvFollowing.text = user.following.toString()
                tvFollowers.text = user.followers.toString()

                if (user.bio.isNullOrBlank()) {
                    llBio.visibility = View.GONE
                } else {
                    tvBio.text = user.bio
                }

                if (user.location.isNullOrBlank()) {
                    llLocation.visibility = View.GONE
                } else {
                    tvLocation.text = user.location
                }

                if (user.company.isNullOrBlank()) {
                    llCompany.visibility = View.GONE
                } else {
                    tvCompany.text = user.company
                }

                Glide.with(this@DetailUserActivity)
                    .load(user.avatarUrl)
                    .into(ivUserImage)
            }
            supportActionBar?.title = user.username

            binding.fbFavoriteUser.setOnClickListener {
                favoriteState = !favoriteState
                lifecycleScope.launch {
                    if (favoriteState) {
                        viewModel.addUserToFavorite(user)
                        Snackbar.make(it, R.string.favorite_success_added, Snackbar.LENGTH_SHORT)
                            .show()
                    } else {
                        viewModel.removeUserFromFavorite(user)
                        Snackbar.make(it, R.string.favorite_success_deleted, Snackbar.LENGTH_SHORT)
                            .show()

                    }
                }
                isFavorite(favoriteState)
            }
        })

    }

    private fun sendToFollowersFollowingViewPagerAdapter(username: String) {
        detailUserViewPagerAdapter.username = username
        viewPager.adapter = detailUserViewPagerAdapter
    }

    private fun setupActionBar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.elevation = 0f
    }

    private fun viewPagerConfiguration() {
        detailUserViewPagerAdapter = DetailUserViewPagerAdapter(this, supportFragmentManager)
        viewPager = binding.vpFollowingFollowers
        viewPager.adapter = detailUserViewPagerAdapter
        val tabs: TabLayout = binding.tlFollowingFollowers
        tabs.setupWithViewPager(viewPager)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    private fun isFavorite(isFavorite: Boolean) {
        if (isFavorite) {
            binding.fbFavoriteUser.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite
                )
            )
        } else {
            binding.fbFavoriteUser.setImageDrawable(
                ContextCompat.getDrawable(
                    this,
                    R.drawable.ic_favorite_border
                )
            )
        }
    }
}