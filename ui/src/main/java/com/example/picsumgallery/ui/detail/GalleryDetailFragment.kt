package com.example.picsumgallery.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.picsumgallery.ui.databinding.FragmentGalleryDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryDetailFragment : Fragment(), GalleryDetailShowWebSite {
    private var _binding: FragmentGalleryDetailBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: GalleryDetailViewModel by viewModels()

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
        binding.showWebSite = this@GalleryDetailFragment
        binding.lifecycleOwner = this@GalleryDetailFragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // endregion
    override fun showWebSite(url: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(url),
        )
        startActivity(intent)
    }
}
