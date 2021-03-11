package com.maxxxwk.architecturel24.data

import com.maxxxwk.architecturel24.domain.SortingPostsUseCase
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class PostMapperTest {

    @ExperimentalCoroutinesApi
    @Test
    fun `mapping post entities works correctly`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val fakePosts = listOf(
            PostEntity(1, titleStub, bodyStub, 1, false),
            PostEntity(2, titleStub, bodyStub, 2, false),
            PostEntity(3, titleStub, bodyStub, 3, false),
            PostEntity(4, titleStub, bodyStub, 3, true),
            PostEntity(5, titleStub, bodyStub, 4, true),
            PostEntity(6, titleStub, bodyStub, 5, true)
        )
        val expectedResult = listOf(
            PostModel(1, 1, titleStub, bodyStub, UserStatusTypes.BANNED),
            PostModel(2, 2, titleStub, bodyStub, UserStatusTypes.NORMAL),
            PostModel(3, 3, titleStub, bodyStub, UserStatusTypes.WARNING),
            PostModel(3, 4, titleStub, bodyStub, UserStatusTypes.WARNING),
            PostModel(4, 5, titleStub, bodyStub, UserStatusTypes.WARNING),
            PostModel(5, 6, titleStub, bodyStub, UserStatusTypes.NORMAL),
        )
        val mockSortingPostsUseCase = mockk<SortingPostsUseCase>()
        coEvery { mockSortingPostsUseCase.invoke(fakePosts) } returns fakePosts
        val fakePostMapper = PostMapper(mockSortingPostsUseCase, TestCoroutineDispatcher())
        val fakeBannedUsersIds = listOf(1)
        val fakeUsersWithWarningIds = listOf(3, 4)
        runBlockingTest {
            fakePostMapper.map(
                fakePosts,
                fakeBannedUsersIds,
                fakeUsersWithWarningIds
            ) shouldBe expectedResult
        }
    }
}