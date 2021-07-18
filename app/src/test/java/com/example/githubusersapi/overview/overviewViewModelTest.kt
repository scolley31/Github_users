package com.example.githubusersapi.overview

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.githubusersapi.data.User
import kotlinx.coroutines.*
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class OverviewViewModelTest {

    private lateinit var viewModel: OverviewViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()


    @Before
    fun setupViewModel() {
            viewModel = OverviewViewModel()
    }

    @Test
    fun toDataItem_from() {

            val user = mutableListOf<User>(
                User(
                    "Scolley", 1, null, null, null, null, null,
                    null, null, null, null, null,
                    null, null, null, null, null, null
                ),
                User(
                    "Fake", 2, null, null, null, null, null,
                    null, null, null, null, null,
                    null, null, null, null, null, null
                )
            )

            val result = viewModel.toDataItem(user)

            assertEquals(DataItem.Right(user[0]), result[0])
            assertEquals(DataItem.Left(user[1]), result[1])

    }

}