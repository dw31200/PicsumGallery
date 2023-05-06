package com.example.picsumgallery.ui.detail

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
import com.example.picsumgallery.ui.detail.contract.GalleryDetailContract
import com.example.picsumgallery.ui.detail.contract.GalleryDetailPresenter
import kotlinx.coroutines.CoroutineScope

class GalleryDetailFragment : Fragment(), GalleryDetailContract.View {
    private var _binding: FragmentGalleryDetailBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var presenter: GalleryDetailPresenter

    // region fragment lifecycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentGalleryDetailBinding.inflate(inflater, container, false)
        presenter = GalleryDetailPresenter(this)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val galleryId = arguments?.getInt("image_id") ?: 0

        presenter.start(galleryId)
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
    // endregion

    // region GalleryDetailContract.View
    override val coroutineScope: CoroutineScope
        get() = this@GalleryDetailFragment.lifecycleScope

    override fun setItem(galleryItem: Picsum) {
        updateUI(galleryItem)
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
                presenter.onUrlClicked(galleryItem)
            }
            detailUrlTextView.setOnClickListener {}
        }
    }

    override fun showWebSite(galleryItem: Picsum) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(galleryItem.webSiteUrl)
                .buildUpon()
                .build(),
        )
        startActivity(intent)
    }
    // endregion
}
