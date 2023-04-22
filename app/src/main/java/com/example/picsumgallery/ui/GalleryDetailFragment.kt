package com.example.picsumgallery.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.FragmentGalleryDetailBinding
import com.example.picsumgallery.network.PicsumApi
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GalleryDetailFragment : Fragment() {
    private var _binding: FragmentGalleryDetailBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentGalleryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val galleryId = arguments?.getInt("image_id") ?: 0

        PicsumApi.retrofitService.getItem(galleryId).enqueue(object : Callback<Picsum> {
            override fun onResponse(call: Call<Picsum>, response: Response<Picsum>) {
                Log.d("GalleryDetailFragment", "Response received ${response.body()}")
                response.body()?.let {
                    updateUI(it)
                }
            }

            override fun onFailure(call: Call<Picsum>, t: Throwable) {
                Log.e("GalleryDetailFragment", "Failed to fetch image", t)
            }
        })
        binding.detailWebSiteUrlTextView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(binding.detailWebSiteUrlTextView.text.toString())
                    .buildUpon()
                    .build(),
            )
            startActivity(intent)
        }
        binding.detailUrlTextView.setOnClickListener {}
    }

    private fun updateUI(galleryItem: Picsum) {
        Picasso.get()
            .load(galleryItem.url)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.detailImageView)
        binding.detailAuthorTextView.text = galleryItem.author
        binding.detailWidthTextView.text = galleryItem.width.toString()
        binding.detailHeightTextView.text = galleryItem.height.toString()
        binding.detailWebSiteUrlTextView.text = galleryItem.webSiteUrl
        binding.detailUrlTextView.text = galleryItem.url
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        const val BUNDLE_ID = "image_id"
        fun args(galleryId: Int): Bundle {
            val args = Bundle().apply {
                putInt(BUNDLE_ID, galleryId)
            }
            return args
        }
    }
}
