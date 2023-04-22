package com.example.picsumgallery.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.FragmentGalleryBinding
import com.example.picsumgallery.network.PicsumApi
import com.example.picsumgallery.ui.GalleryDetailFragment.Companion.args
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.galleryList.adapter = GalleryAdapter { galleryId -> adapterOnClick(galleryId) }
        binding.galleryList.layoutManager = GridLayoutManager(context, 2)

        PicsumApi.retrofitService.fetchContents().enqueue(object : Callback<List<Picsum>> {
            override fun onResponse(call: Call<List<Picsum>>, response: Response<List<Picsum>>) {
                Log.d("GalleryFragment", "Response received ${response.body()}")
                response.body()?.let {
                    (binding.galleryList.adapter as GalleryAdapter).fetchData(it)
                }
            }

            override fun onFailure(call: Call<List<Picsum>>, t: Throwable) {
                Log.e("GalleryFragment", "Failed to fetch image", t)
            }
        })
    }

    private fun adapterOnClick(galleryId: Int) {
        parentFragmentManager.commit {
            replace(
                R.id.fragment_container,
                GalleryDetailFragment().apply {
                    arguments = args(galleryId)
                },
            )
            addToBackStack(null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
