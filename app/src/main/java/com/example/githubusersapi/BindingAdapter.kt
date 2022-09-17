package com.example.githubusersapi

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.githubusersapi.overview.DataItem
import com.example.githubusersapi.overview.OverviewAdapter


@BindingAdapter("news")
fun bindRecyclerView(recyclerView: RecyclerView, news: List<DataItem>?) {
    news?.let {
        recyclerView.adapter?.apply {
            when (this) {
                is OverviewAdapter -> {
                    notifyDataSetChanged()
                    submitList(it)
                }
            }
        }
    }
}

@BindingAdapter("glide")
fun glidingImage(imgView: ImageView, url: String?) {
    url?.let {
        val uri = it.toUri().buildUpon().build()

        Glide.with(imgView.context)
            .load(uri).apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_background)
            )
            .into(imgView)
    }

}