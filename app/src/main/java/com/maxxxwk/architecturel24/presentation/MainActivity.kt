package com.maxxxwk.architecturel24.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.ActivityMainBinding
import com.maxxxwk.architecturel24.presentation.adapter.PostsListAdapter
import com.maxxxwk.architecturel24.presentation.model.PostUIModel
import com.maxxxwk.architecturel24.utils.AppModule
import com.maxxxwk.architecturel24.utils.DaggerAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val postsAdapter = PostsListAdapter()
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
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[MainActivityViewModel::class.java]
        observePosts()
        viewModel.loadPosts()
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