package com.maxxxwk.architecturel24.ui

import android.view.View
import com.maxxxwk.architecturel24.R
import com.maxxxwk.architecturel24.data.repository.AndroidResourcesRepository
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class PostUIMapperTest {

    @Test
    fun `mapping normal users post ui models works correctly`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val backgroundColorStub = 0
        val mockAndroidResourcesRepository = mockk<AndroidResourcesRepository> {
            coEvery { getColor(R.color.white) } returns backgroundColorStub
        }
        val fakePostModel = PostModel(1, 1, titleStub, bodyStub, UserStatusTypes.NORMAL)
        val expectedPostUIModel = NormalUsersPostUIModel(
            1,
            1,
            titleStub,
            bodyStub,
            backgroundColorStub,
            View.GONE
        )
        val fakePostUIMapper =
            PostUIMapper(mockAndroidResourcesRepository, TestCoroutineDispatcher())
        runBlockingTest {
            fakePostUIMapper.map(fakePostModel) shouldBe expectedPostUIModel
        }
    }

    @Test
    fun `mapping warning users post ui models works correctly`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val backgroundColorStub = 0
        val mockAndroidResourcesRepository = mockk<AndroidResourcesRepository> {
            coEvery { getColor(R.color.user_with_warning_post_background) } returns backgroundColorStub
        }
        val fakePostModel = PostModel(1, 1, titleStub, bodyStub, UserStatusTypes.WARNING)
        val expectedPostUIModel = NormalUsersPostUIModel(
            1,
            1,
            titleStub,
            bodyStub,
            backgroundColorStub,
            View.VISIBLE
        )
        val fakePostUIMapper =
            PostUIMapper(mockAndroidResourcesRepository, TestCoroutineDispatcher())
        runBlockingTest {
            fakePostUIMapper.map(fakePostModel) shouldBe expectedPostUIModel
        }
    }

    @Test
    fun `mapping banned users post ui models works correctly`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val mockAndroidResourcesRepository = mockk<AndroidResourcesRepository>(relaxed = true)
        val fakePostModel = PostModel(1, 1, titleStub, bodyStub, UserStatusTypes.BANNED)
        val expectedPostUIModel = BannedUsersPostUIModel(1, 1)
        val fakePostUIMapper =
            PostUIMapper(mockAndroidResourcesRepository, TestCoroutineDispatcher())
        runBlockingTest {
            fakePostUIMapper.map(fakePostModel) shouldBe expectedPostUIModel
        }
    }
}