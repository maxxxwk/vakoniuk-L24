package com.maxxxwk.architecturel24.domain

import com.maxxxwk.architecturel24.data.PostEntity
import io.kotest.matchers.shouldBe
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class SortingPostsUseCaseTest {

    @ExperimentalCoroutinesApi
    @Test
    fun `sorting posts works correctly`() {
        val titleStub = "titleStub"
        val bodyStub = "bodyStub"
        val sortingPostsUseCase = SortingPostsUseCase(TestCoroutineDispatcher())
        val fakePostEntityList = listOf(
            PostEntity(8, titleStub, bodyStub, 1, true),
            PostEntity(1, titleStub, bodyStub, 1, false),
            PostEntity(4, titleStub, bodyStub, 1, true),
            PostEntity(7, titleStub, bodyStub, 1, true),
            PostEntity(5, titleStub, bodyStub, 1, true),
            PostEntity(6, titleStub, bodyStub, 1, true),
            PostEntity(9, titleStub, bodyStub, 1, false),
            PostEntity(3, titleStub, bodyStub, 1, false),
            PostEntity(2, titleStub, bodyStub, 1, false),
            PostEntity(10, titleStub, bodyStub, 1, true)
        )
        val sortedPostEntityList = listOf(
            PostEntity(9, titleStub, bodyStub, 1, false),
            PostEntity(3, titleStub, bodyStub, 1, false),
            PostEntity(2, titleStub, bodyStub, 1, false),
            PostEntity(1, titleStub, bodyStub, 1, false),
            PostEntity(8, titleStub, bodyStub, 1, true),
            PostEntity(4, titleStub, bodyStub, 1, true),
            PostEntity(7, titleStub, bodyStub, 1, true),
            PostEntity(5, titleStub, bodyStub, 1, true),
            PostEntity(6, titleStub, bodyStub, 1, true),
            PostEntity(10, titleStub, bodyStub, 1, true)
        )
        runBlockingTest {
            sortingPostsUseCase(fakePostEntityList) shouldBe sortedPostEntityList
        }
    }
}