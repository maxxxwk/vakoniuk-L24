package com.maxxxwk.architecturel24.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.ActivityMainBinding
import com.maxxxwk.architecturel24.presentation.adapter.PostListAdapter
import com.maxxxwk.architecturel24.presentation.model.PostUIModel
import com.maxxxwk.architecturel24.utils.AppModule
import com.maxxxwk.architecturel24.utils.DaggerAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val postsAdapter = PostListAdapter()
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: MainActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupRecyclerView()
        setupViewModel()
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            btnOpenCreatePostActivity.setOnClickListener {
                CreatePostActivity.start(this@MainActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPosts()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainActivityViewModel::class.java]
        observePosts()
    }

    private fun observePosts() {
        viewModel.postsLiveData.observe(this, {
            showPosts(it)
        })
    }

    private fun setupRecyclerView() {
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        binding.rvPosts.adapter = postsAdapter
    }

    private fun showPosts(posts: List<PostUIModel>) {
        postsAdapter.submitList(posts)
    }
}