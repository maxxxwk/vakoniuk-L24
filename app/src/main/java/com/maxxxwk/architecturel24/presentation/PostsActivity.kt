package com.maxxxwk.architecturel24.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.ActivityPostsBinding
import com.maxxxwk.architecturel24.di.AppModule
import com.maxxxwk.architecturel24.di.DaggerAppComponent
import javax.inject.Inject

class PostsActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val postsAdapter = PostsListAdapter()
    private lateinit var binding: ActivityPostsBinding
    private lateinit var viewModel: PostsActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModel()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_posts)
        setupRecyclerView()
        setupListeners()
    }

    private fun initViewModel() {
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[PostsActivityViewModel::class.java]
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
        viewModel.loadPosts().subscribe({ postUIModels ->
            postsAdapter.submitList(postUIModels)
        }, { t ->
            Log.d("LOG_TAG", t.message.orEmpty())
        })
    }

    private fun setupRecyclerView() {
        with(binding.rvPosts) {
            layoutManager = LinearLayoutManager(this@PostsActivity)
            adapter = postsAdapter
        }
    }
}