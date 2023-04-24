package com.example.picsumgallery.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.FragmentGalleryDetailBinding
import com.example.picsumgallery.network.PicsumApi
import kotlinx.coroutines.launch

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

        lifecycleScope.launch {
            val item = PicsumApi.retrofitService.getItem(galleryId)
            updateUI(item)
        }
    }

    private fun updateUI(galleryItem: Picsum) {
        with(binding) {
            Glide
                .with(root)
                .load(galleryItem.url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(detailImageView)
            detailAuthorTextView.text = galleryItem.author
            detailWidthTextView.text = galleryItem.width.toString()
            detailHeightTextView.text = galleryItem.height.toString()
            detailWebSiteUrlTextView.text = galleryItem.webSiteUrl
            detailUrlTextView.text = galleryItem.url
            detailWebSiteUrlTextView.setOnClickListener {
                val intent = Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse(galleryItem.url)
                        .buildUpon()
                        .build(),
                )
                startActivity(intent)
            }
            detailUrlTextView.setOnClickListener {}
        }
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
