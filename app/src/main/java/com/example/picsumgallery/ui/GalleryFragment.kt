package com.example.picsumgallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import com.example.picsumgallery.R
import com.example.picsumgallery.databinding.FragmentGalleryBinding
import com.example.picsumgallery.network.PicsumApi
import com.example.picsumgallery.ui.GalleryDetailFragment.Companion.args
import kotlinx.coroutines.launch

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
        binding.galleryList.addItemDecoration(GalleryItemDecoration())

        lifecycleScope.launch {
            binding.progressLoading.visibility = View.VISIBLE
            val list = PicsumApi.retrofitService.fetchContents()
            binding.progressLoading.visibility = View.GONE
            (binding.galleryList.adapter as GalleryAdapter).fetchData(list)
        }
    }

    private fun adapterOnClick(galleryId: Int) {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out,
            )
            replace<GalleryDetailFragment>(
                R.id.fragment_container,
                args = args(galleryId),
            )
            addToBackStack(null)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
