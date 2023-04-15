package com.example.picsumgallery.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.FragmentGalleryBinding
import com.example.picsumgallery.viewmodel.GalleryViewModel

class GalleryFragment : Fragment() {
    private val galleryViewModel: GalleryViewModel by viewModels()
    private lateinit var binding: FragmentGalleryBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val view = binding.root
        binding.galleryList.layoutManager = GridLayoutManager(context, 2)

        return view
    }

    private fun adapterOnClick(picsum: Picsum) {
        val fragment = GalleryDetailFragment.newInstance(picsum)
        parentFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryViewModel.picsumLiveData.observe(
            viewLifecycleOwner,
            Observer { galleryItems ->
                binding.galleryList.adapter = GalleryAdapter(galleryItems) { galleryItems -> adapterOnClick(galleryItems) }
            },
        )
    }
}
