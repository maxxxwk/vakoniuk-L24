package com.maxxxwk.architecturel24.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.ActivityPostsBinding
import com.maxxxwk.architecturel24.utils.AppModule
import com.maxxxwk.architecturel24.utils.DaggerAppComponent
import javax.inject.Inject

class PostsActivity : AppCompatActivity() {

    private val postsAdapter = PostsListAdapter()
    private lateinit var binding: ActivityPostsBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: PostsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        setupRecyclerView()
        setupViewModel()
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            btnOpenCreatePostActivity.setOnClickListener {
                PostCreationActivity.start(this@PostsActivity)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.loadPosts()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[PostsActivityViewModel::class.java]
        observePosts()
    }

    private fun observePosts() {
        viewModel.postsLiveData.observe(this, {
            postsAdapter.submitList(it)
        })
    }

    private fun setupRecyclerView() {
        with(binding.rvPosts) {
            layoutManager = LinearLayoutManager(this@PostsActivity)
            adapter = postsAdapter
        }
    }
}