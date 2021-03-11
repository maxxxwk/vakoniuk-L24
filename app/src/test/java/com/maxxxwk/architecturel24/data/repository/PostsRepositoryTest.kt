package com.maxxxwk.architecturel24.data.repository

import com.maxxxwk.architecturel24.data.JSONPlaceholderService
import com.maxxxwk.architecturel24.data.PostEntity
import com.maxxxwk.architecturel24.data.PostMapper
import com.maxxxwk.architecturel24.data.database.PostDatabase
import com.maxxxwk.architecturel24.domain.model.PostModel
import com.maxxxwk.architecturel24.domain.model.UserStatusTypes
import io.kotest.matchers.shouldBe
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test


@ExperimentalCoroutinesApi
class PostsRepositoryTest {

    private val dispatcher = TestCoroutineDispatcher()

    @Test
    fun `posts repository return posts from local storage if it not empty`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val fakePostsEntities = listOf(
            PostEntity(1, titleStub, bodyStub, 1, false),
            PostEntity(2, titleStub, bodyStub, 2, false),
            PostEntity(3, titleStub, bodyStub, 3, false)
        )
        val fakePostsModels = listOf(
            PostModel(1, 1, titleStub, bodyStub, UserStatusTypes.NORMAL),
            PostModel(2, 2, titleStub, bodyStub, UserStatusTypes.NORMAL),
            PostModel(3, 3, titleStub, bodyStub, UserStatusTypes.NORMAL)
        )
        val mockDB = mockk<PostDatabase> {
            every { postDao() } returns mockk {
                coEvery { getAll() } returns fakePostsEntities
            }
        }
        val mockJSONPlaceholderService = mockk<JSONPlaceholderService>(relaxed = true)
        val mockUserStatusRepository = mockk<UserStatusRepository> {
            coEvery { getBannedUsersIdList() } returns emptyList()
            coEvery { getUsersWithWarningsIdList() } returns emptyList()
        }
        val mockPostMapper = mockk<PostMapper> {
            coEvery { map(fakePostsEntities, any(), any()) } returns fakePostsModels
        }
        val fakePostsRepository = PostsRepository(
            mockJSONPlaceholderService,
            mockPostMapper,
            mockUserStatusRepository,
            mockDB,
            dispatcher
        )
        runBlockingTest {
            fakePostsRepository.getPosts() shouldBe fakePostsModels
        }
    }

    @Test
    fun `posts repository return posts from json placeholder service if local storage is empty`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val fakePostsEntities = listOf(
            PostEntity(1, titleStub, bodyStub, 1, true),
            PostEntity(2, titleStub, bodyStub, 2, true),
            PostEntity(3, titleStub, bodyStub, 3, true)
        )
        val fakePostsModels = listOf(
            PostModel(1, 1, titleStub, bodyStub, UserStatusTypes.NORMAL),
            PostModel(2, 2, titleStub, bodyStub, UserStatusTypes.NORMAL),
            PostModel(3, 3, titleStub, bodyStub, UserStatusTypes.NORMAL)
        )
        val mockJSONPlaceholderService = mockk<JSONPlaceholderService> {
            coEvery { postsList() } returns fakePostsEntities
        }
        val mockDB = mockk<PostDatabase> {
            every { postDao() } returns mockk(relaxed = true) {
                coEvery { getAll() } returns emptyList()
            }
        }
        val mockUserStatusRepository = mockk<UserStatusRepository> {
            coEvery { getBannedUsersIdList() } returns emptyList()
            coEvery { getUsersWithWarningsIdList() } returns emptyList()
        }
        val mockPostMapper = mockk<PostMapper> {
            coEvery { map(fakePostsEntities, any(), any()) } returns fakePostsModels
        }
        val fakePostsRepository = PostsRepository(
            mockJSONPlaceholderService,
            mockPostMapper,
            mockUserStatusRepository,
            mockDB,
            dispatcher
        )
        runBlockingTest {
            fakePostsRepository.getPosts() shouldBe fakePostsModels
        }
    }
}