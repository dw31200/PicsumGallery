package com.example.picsumgallery.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.FragmentGalleryDetailBinding
import com.squareup.picasso.Picasso

class GalleryDetailFragment : Fragment() {
    private lateinit var binding: FragmentGalleryDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGalleryDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.detailWebSiteUrlTextView.setOnClickListener {
            val intent = Intent(
                Intent.ACTION_VIEW,
                Uri.parse(arguments?.getString("image_webSiteUrl"))
                    .buildUpon()
                    .build(),
            )
            startActivity(intent)
        }
        binding.detailUrlTextView.setOnClickListener {}

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    private fun updateUI() {
        Picasso.get()
            .load(arguments?.getString("image_url"))
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.detailImageView)
        binding.detailAuthorTextView.text = arguments?.getString("image_author")
        binding.detailWidthTextView.text = arguments?.getInt("image_width").toString()
        binding.detailHeightTextView.text = arguments?.getInt("image_height").toString()
        binding.detailWebSiteUrlTextView.text = arguments?.getString("image_webSiteUrl")
        binding.detailUrlTextView.text = arguments?.getString("image_url")
    }

    companion object {
        fun newInstance(galleryItem: Picsum): GalleryDetailFragment {
            val args = Bundle().apply {
                putInt("image_id", galleryItem.id)
                putString("image_author", galleryItem.author)
                putInt("image_width", galleryItem.width)
                putInt("image_height", galleryItem.height)
                putString("image_webSiteUrl", galleryItem.webSiteUrl)
                putString("image_url", galleryItem.url)
            }
            return GalleryDetailFragment().apply {
                arguments = args
            }
        }
    }
}
