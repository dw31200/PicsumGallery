package com.example.picsumgallery.ui

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding
import com.example.picsumgallery.network.PicsumApi
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryHolder(val binding: ListItemGalleryBinding) : RecyclerView.ViewHolder(binding.root) {
    private lateinit var galleryItem: Picsum

    fun bind(galleryId: Int) {
        PicsumApi.retrofitService.getItem(galleryId).enqueue(object : Callback<Picsum> {
            override fun onResponse(call: Call<Picsum>, response: Response<Picsum>) {
                Log.d("GalleryHolder", "Response received ${response.body()}")
                response.body()?.let {
                    galleryItem = it
                    binding.galleryAuthor.text = galleryItem.author
                    Picasso.get()
                        .load(galleryItem.url)
                        .placeholder(R.drawable.ic_launcher_foreground)
                        .into(binding.galleryImage)
                }
            }

            override fun onFailure(call: Call<Picsum>, t: Throwable) {
                Log.e("GalleryHolder", "Failed to fetch image", t)
            }
        })
    }
}
