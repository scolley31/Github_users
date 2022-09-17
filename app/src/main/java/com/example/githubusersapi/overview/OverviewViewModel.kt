package com.example.githubusersapi.overview

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.githubusersapi.data.Item
import com.example.githubusersapi.data.UsersApi
import com.example.githubusersapi.data.Items
import com.example.githubusersapi.data.NewsDate
import kotlinx.coroutines.*

class OverviewViewModel: ViewModel() {

    companion object {
        const val DIVIDER = "divider"
        const val NEWS = "news"
    }

    private val _news = MutableLiveData<List<DataItem>?>()
    val news: LiveData<List<DataItem>?>
        get() = _news

    private var viewModelJob = Job()

    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private lateinit var result : NewsDate

    init {
        coroutineScope.launch {
            getVectors()
        }
    }

    private suspend fun getVectors() {

        coroutineScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    result = UsersApi.retrofitService.getNews()
                } catch (e: Exception) {
                    Log.i("Demo", "exception=${e.message}")
                }
            }
            _news.value = toDataItem(result)
        }
    }

    fun toDataItem(News: NewsDate?): List<DataItem> {
        val items = mutableListOf<DataItem>()
        News?.let {
            it.getVector?.items?.forEach { item ->
                when(item.type) {
                    DIVIDER -> items.add(DataItem.Divider(item))
                    NEWS -> {
                        if (item.isDateNoMissing()) {
                            items.add(DataItem.News(item))
                        }
                    }
                }
            }
        }
        return items
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

    private fun Item.isDateNoMissing(): Boolean{
        return !(this.appearance?.mainTitle.isNullOrEmpty() || this.appearance?.subTitle.isNullOrEmpty() || this.appearance?.thumbnail.isNullOrEmpty() || this.extra?.created == null)
    }

}