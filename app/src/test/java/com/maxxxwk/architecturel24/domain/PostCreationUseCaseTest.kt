package com.maxxxwk.architecturel24.domain

import com.maxxxwk.architecturel24.data.repository.PostsRepository
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationResult
import com.maxxxwk.architecturel24.domain.postValidation.PostValidationUseCase
import com.maxxxwk.architecturel24.ui.postCreation.PostCreationResult
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PostCreationUseCaseTest {

    private val dispatcher = TestCoroutineDispatcher()

    @Test
    fun `creating post with correct content has successful result`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val mockPostsRepository = mockk<PostsRepository>(relaxed = true)
        val mockPostValidationUseCase = mockk<PostValidationUseCase>()
        coEvery { mockPostValidationUseCase.invoke(any()) } returns PostValidationResult.SUCCESSFUL
        val fakePostCreationUseCase = PostCreationUseCase(
            mockPostsRepository,
            mockPostValidationUseCase,
            dispatcher
        )
        runBlockingTest {
            fakePostCreationUseCase(titleStub, bodyStub) shouldBe PostCreationResult.Successful
        }
    }

    @Test
    fun `creating post with incorrect title has failure result`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val mockPostsRepository = mockk<PostsRepository>(relaxed = true)
        val mockPostValidationUseCase = mockk<PostValidationUseCase>()
        coEvery { mockPostValidationUseCase.invoke(any()) } returns PostValidationResult.INCORRECT_TITLE
        val fakePostCreationUseCase = PostCreationUseCase(
            mockPostsRepository,
            mockPostValidationUseCase,
            dispatcher
        )
        runBlockingTest {
            assert(fakePostCreationUseCase(titleStub, bodyStub) is PostCreationResult.Failure)
        }
    }

    @Test
    fun `creating post with too small title has failure result`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val mockPostsRepository = mockk<PostsRepository>(relaxed = true)
        val mockPostValidationUseCase = mockk<PostValidationUseCase>()
        coEvery { mockPostValidationUseCase.invoke(any()) } returns PostValidationResult.TOO_SMALL_TITLE
        val fakePostCreationUseCase = PostCreationUseCase(
            mockPostsRepository,
            mockPostValidationUseCase,
            dispatcher
        )
        runBlockingTest {
            assert(fakePostCreationUseCase(titleStub, bodyStub) is PostCreationResult.Failure)
        }
    }

    @Test
    fun `creating post with too small body has failure result`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val mockPostsRepository = mockk<PostsRepository>(relaxed = true)
        val mockPostValidationUseCase = mockk<PostValidationUseCase>()
        coEvery { mockPostValidationUseCase.invoke(any()) } returns PostValidationResult.TOO_SMALL_BODY
        val fakePostCreationUseCase = PostCreationUseCase(
            mockPostsRepository,
            mockPostValidationUseCase,
            dispatcher
        )
        runBlockingTest {
            assert(fakePostCreationUseCase(titleStub, bodyStub) is PostCreationResult.Failure)
        }
    }
}