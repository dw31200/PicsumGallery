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

    override fun setItem(prevItem: Picsum?, currentItem: Picsum?, nextItem: Picsum?) {
        with(binding) {
            if (currentItem != null) {
                Glide
                    .with(root)
                    .load(currentItem.url)
                    .into(detailImageView)
                Glide
                    .with(root)
                    .load(currentItem.url)
                    .into(currentImageView)
                detailAuthorTextView.text = currentItem.author
                detailWidthTextView.text = currentItem.width.toString()
                detailHeightTextView.text = currentItem.height.toString()
                detailWebSiteUrlTextView.text = currentItem.webSiteUrl
                detailUrlTextView.text = currentItem.url
                detailWebSiteUrlTextView.setOnClickListener {
                    presenter.onUrlClicked(currentItem)
                }
                detailUrlTextView.setOnClickListener {}
                prevButton.setOnClickListener {
                    presenter.onPrevButtonClicked(currentItem.id)
                }
                nextButton.setOnClickListener {
                    presenter.onNextButtonClicked(currentItem.id)
                }
            }
            if (prevItem != null) {
                prevImageView.visibility = View.VISIBLE
                Glide
                    .with(root)
                    .load(prevItem.url)
                    .into(prevImageView)
            } else {
                prevImageView.visibility = View.INVISIBLE
            }
            if (nextItem != null) {
                nextImageView.visibility = View.VISIBLE
                Glide
                    .with(root)
                    .load(nextItem.url)
                    .into(nextImageView)
            } else {
                nextImageView.visibility = View.INVISIBLE
            }
        }
    }

    override fun showWebSite(galleryItem: Picsum) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(galleryItem.webSiteUrl),
        )
        startActivity(intent)
    }
    // endregion
}
