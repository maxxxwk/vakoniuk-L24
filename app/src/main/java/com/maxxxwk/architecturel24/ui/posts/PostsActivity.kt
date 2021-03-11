package com.maxxxwk.architecturel24.ui.posts

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxxxwk.architecturel24.App
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.ActivityPostsBinding
import com.maxxxwk.architecturel24.ui.postCreation.PostCreationActivity
import javax.inject.Inject

class PostsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val postsAdapter = PostsListAdapter()
    private lateinit var binding: ActivityPostsBinding
    private lateinit var viewModel: PostsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        setupRecyclerView()
        setupListeners()
        viewModel.loadPosts()
    }

    override fun onRestart() {
        super.onRestart()
        viewModel.loadPosts()
    }

    private fun setupViewModel() {
        (application as App).daggerComponent.inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[PostsActivityViewModel::class.java]
        observePosts()
    }

    private fun observePosts() {
        viewModel.postsLiveData.observe(this, {
            postsAdapter.submitList(it)
        })
    }

    private fun setupListeners() {
        with(binding) {
            btnOpenCreatePostActivity.setOnClickListener {
                PostCreationActivity.start(this@PostsActivity)
            }
        }
    }

    private fun setupRecyclerView() {
        with(binding.rvPosts) {
            layoutManager = LinearLayoutManager(this@PostsActivity)
            adapter = postsAdapter
        }
    }
}
