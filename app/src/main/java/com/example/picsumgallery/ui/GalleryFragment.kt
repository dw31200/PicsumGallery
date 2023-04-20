package com.example.picsumgallery.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.FragmentGalleryBinding
import com.example.picsumgallery.network.PicsumApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryFragment : Fragment() {
    private lateinit var binding: FragmentGalleryBinding
    private var galleryItems: List<Picsum> = emptyList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        binding.galleryList.layoutManager = GridLayoutManager(context, 2)

        return binding.root
    }

    private fun adapterOnClick(picsum: Picsum) {
        val fragment = GalleryDetailFragment.newInstance(picsum)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        val mAdapter = GalleryAdapter { galleryItems -> adapterOnClick(galleryItems) }
        binding.galleryList.adapter = mAdapter

        PicsumApi.retrofitService.fetchContents().enqueue(object : Callback<List<Picsum>> {
            override fun onResponse(call: Call<List<Picsum>>, response: Response<List<Picsum>>) {
                Log.d("GalleryFragment", "Response received ${response.body()}")
                mAdapter.galleryItems = response.body() ?: emptyList()
                mAdapter.fetchData()
            }

            override fun onFailure(call: Call<List<Picsum>>, t: Throwable) {
                Log.e("GalleryFragment", "Failed to fetch image", t)
            }
        })
    }
}
