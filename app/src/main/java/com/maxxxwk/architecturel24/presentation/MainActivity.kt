package com.maxxxwk.architecturel24.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.maxxxwk.architecturel24.databinding.ActivityMainBinding
import com.maxxxwk.architecturel24.presentation.adapter.PostsListAdapter
import com.maxxxwk.architecturel24.presentation.model.PostUIModel
import com.maxxxwk.architecturel24.utils.PostsComponent

class MainActivity : AppCompatActivity(), PostsView {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val presenter: PostsPresenter by lazy { PostsComponent.createPresenter(this) }
    private val postsAdapter = PostsListAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        presenter.attachView(this)
    }

    private fun setupRecyclerView() {
        binding.rvPosts.layoutManager = LinearLayoutManager(this)
        binding.rvPosts.adapter = postsAdapter
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }

    override fun showPosts(posts: List<PostUIModel>) {
        postsAdapter.submitList(posts)
    }
}