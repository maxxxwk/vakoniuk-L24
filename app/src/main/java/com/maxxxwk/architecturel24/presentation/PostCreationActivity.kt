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
import com.maxxxwk.architecturel24.databinding.ActivityPostCreationBinding
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationResult
import com.maxxxwk.architecturel24.di.AppModule
import com.maxxxwk.architecturel24.di.DaggerAppComponent
import javax.inject.Inject

class PostCreationActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var binding: ActivityPostCreationBinding
    private lateinit var viewModel: PostCreationActivityViewModel
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
            context.startActivity(Intent(context, PostCreationActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_post_creation)
        initViewModel()
        setupListeners()
    }

    private fun initViewModel() {
        DaggerAppComponent.builder().appModule(AppModule(this)).build().inject(this)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory)[PostCreationActivityViewModel::class.java]
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
}