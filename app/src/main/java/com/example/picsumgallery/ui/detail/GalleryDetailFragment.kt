package com.example.picsumgallery.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.picsumgallery.databinding.FragmentGalleryDetailBinding

class GalleryDetailFragment : Fragment() {
    private var _binding: FragmentGalleryDetailBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel by lazy {
        GalleryDetailViewModel(GalleryDetailModel(), getImageId(arguments))
    }

    // region fragment lifecycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentGalleryDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.fragment = this@GalleryDetailFragment
        binding.lifecycleOwner = this@GalleryDetailFragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun showWebSite(webSiteUrl: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(webSiteUrl),
        )
        startActivity(intent)
    }

    // endregion
    companion object {
        private const val BUNDLE_ID = "image_id"
        fun args(galleryId: Int): Bundle {
            val args = Bundle().apply {
                putInt(BUNDLE_ID, galleryId)
            }
            return args
        }

        fun getImageId(args: Bundle?): Int {
            return args?.getInt(BUNDLE_ID) ?: -1
        }
    }
}
