package com.example.githubusersapi.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusersapi.data.User
import com.example.githubusersapi.data.Users
import com.example.githubusersapi.data.UsersApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OverviewViewModel: ViewModel() {

    private val _users = MutableLiveData<List<User>>()

    // The external LiveData interface to the property is immutable, so only this class can modify
    val users: LiveData<List<User>>
        get() = _users

    // Create a Coroutine scope using a job to be able to cancel when needed
    private var viewModelJob = Job()

    // the Coroutine runs using the Main (UI) dispatcher
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getUsers()
    }

    private fun getUsers() {
        coroutineScope.launch {

            try {
                // this will run on a thread managed by Retrofit
                val result = UsersApi.retrofitService.getUsers()

                _users.value = result

            } catch (e: Exception) {
                Log.i("Demo", "exception=${e.message}")
            }
        }
    }


}