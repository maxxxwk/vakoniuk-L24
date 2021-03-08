package com.maxxxwk.architecturel24.ui.postCreation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.maxxxwk.architecturel24.App
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.ActivityPostCreationBinding
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationResult
import javax.inject.Inject

class PostCreationActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityPostCreationBinding
    private lateinit var viewModel: PostCreationActivityViewModel

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, PostCreationActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_creation)
        setupViewModel()
        setupListeners()
    }

    private fun setupViewModel() {
        (application as App).daggerComponent.inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[PostCreationActivityViewModel::class.java]
        observePostCreationResult()
    }

    private fun observePostCreationResult() {
        viewModel.postCreationLiveData.observe(this, {
            when(it) {
                is PostCreationResult.Failure -> showError(it)
                is PostCreationResult.Successful -> finish()
            }
        })
    }

    private fun setupListeners() {
        with(binding) {
            btnCreatePost.setOnClickListener {
                viewModel.createPost(
                    etPostTitle.text.toString(),
                    etPostBody.text.toString()
                )
            }
        }
    }

    private fun showError(postCreationFailure: PostCreationResult.Failure) {
        val message = when (postCreationFailure.postValidationResult) {
            PostValidationResult.TOO_SMALL_TITLE -> getString(R.string.too_small_post_title_error)
            PostValidationResult.TOO_SMALL_BODY -> getString(R.string.too_small_post_body_error)
            PostValidationResult.INCORRECT_TITLE -> getString(R.string.incorrect_words_in_post_title_error)
            else -> ""
        }
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}