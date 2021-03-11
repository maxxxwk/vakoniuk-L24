package com.maxxxwk.architecturel24.domain.postValidation

import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.data.PostEntity
import com.maxxxwk.architecturel24.data.repository.AndroidResourcesRepository
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PostValidationUseCaseTest {

    private val postValidationUseCase: PostValidationUseCase by lazy {
        val mockAndroidResourcesRepository = mockk<AndroidResourcesRepository> {
            coEvery { getString(R.string.forbidden_word_buy) } returns "buy"
            coEvery { getString(R.string.forbidden_word_goods) } returns "goods"
            coEvery { getString(R.string.forbidden_word_advertisement) } returns "advertisement"
        }
        PostValidationUseCase(
            mockAndroidResourcesRepository,
            TestCoroutineDispatcher()
        )
    }

    @Test
    fun `validation of post with title length less than 3 works correctly`() {
        val fakeTitle = "x"
        val fakeBody = "xxxxx"
        val fakePostEntity = PostEntity(1, fakeTitle, fakeBody, 1, false)
        runBlockingTest {
            postValidationUseCase(fakePostEntity) shouldBe PostValidationResult.TOO_SMALL_TITLE
        }
    }

    @Test
    fun `validation of post with body length less than 5 works correctly`() {
        val fakeTitle = "xxx"
        val fakeBody = "x"
        val fakePostEntity = PostEntity(1, fakeTitle, fakeBody, 1, false)
        runBlockingTest {
            postValidationUseCase(fakePostEntity) shouldBe PostValidationResult.TOO_SMALL_BODY
        }
    }

    @Test
    fun `validation of post with forbidden word 'buy' in title works correctly`() {
        val fakeTitle = "BuY"
        val fakeBody = "xxxxx"
        val fakePostEntity = PostEntity(1, fakeTitle, fakeBody, 1, false)
        runBlockingTest {
            postValidationUseCase(fakePostEntity) shouldBe PostValidationResult.INCORRECT_TITLE
        }
    }

    @Test
    fun `validation of post with forbidden word 'goods' in title works correctly`() {
        val fakeTitle = "GooDS"
        val fakeBody = "xxxxx"
        val fakePostEntity = PostEntity(1, fakeTitle, fakeBody, 1, false)
        runBlockingTest {
            postValidationUseCase(fakePostEntity) shouldBe PostValidationResult.INCORRECT_TITLE
        }
    }

    @Test
    fun `validation of post with forbidden word 'advertisement' in title works correctly`() {
        val fakeTitle = "AdvertiseMent"
        val fakeBody = "xxxxx"
        val fakePostEntity = PostEntity(1, fakeTitle, fakeBody, 1, false)
        runBlockingTest {
            postValidationUseCase(fakePostEntity) shouldBe PostValidationResult.INCORRECT_TITLE
        }
    }

    @Test
    fun `validation of correct post works correctly`() {
        val fakeTitle = "xxx"
        val fakeBody = "xxxxx"
        val fakePostEntity = PostEntity(1, fakeTitle, fakeBody, 1, false)
        runBlockingTest {
            postValidationUseCase(fakePostEntity) shouldBe PostValidationResult.SUCCESSFUL
        }
    }
}