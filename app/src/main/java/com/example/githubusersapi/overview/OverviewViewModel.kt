package com.example.githubusersapi.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusersapi.data.User
import com.example.githubusersapi.data.UsersApi
import kotlinx.coroutines.*

class OverviewViewModel: ViewModel() {

    private val _users = MutableLiveData<List<User>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val users: LiveData<List<User>>
        get() = _users

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    lateinit var result : List<User>

    init {
        coroutineScope.launch {
            getUsers()
        }
    }

    suspend fun getUsers() {
        coroutineScope.launch {

            withContext(Dispatchers.IO) {
                try {
                    // this will run on a thread managed by Retrofit
                    result = UsersApi.retrofitService.getUsers()

                } catch (e: Exception) {
                    Log.i("Demo", "exception=${e.message}")
                }
            }

            _users.value = result
            Log.d("users", "_users.value = ${_users.value}")

        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}