package com.maxxxwk.architecturel24.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.databinding.ActivityCreatePostBinding
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationResult
import com.maxxxwk.architecturel24.utils.AppModule
import com.maxxxwk.architecturel24.utils.DaggerAppComponent
import javax.inject.Inject

class CreatePostActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCreatePostBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: CreatePostViewModel
    private val onErrorCallback: (PostValidationResult) -> Unit = {
        when (it) {
            PostValidationResult.TOO_SMALL_TITLE -> {
                showError(getString(R.string.too_small_post_title_error))
            }
            PostValidationResult.TOO_SMALL_BODY -> {
                showError(getString(R.string.too_small_post_body_error))
            }
            PostValidationResult.INCORRECT_TITLE -> {
                showError(getString(R.string.incorrect_words_in_post_title_error))
            }
        }
    }
    private val onSuccessfulCallback: () -> Unit = {
        finish()
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, CreatePostActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_post)
        setupViewModel()
        setupListeners()
    }

    private fun setupListeners() {
        with(binding) {
            btnCreatePost.setOnClickListener {
                viewModel.createPost(
                    etPostTitle.text.toString(),
                    etPostBody.text.toString(),
                    onErrorCallback,
                    onSuccessfulCallback
                )
            }
        }
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory)[CreatePostViewModel::class.java]
    }
}