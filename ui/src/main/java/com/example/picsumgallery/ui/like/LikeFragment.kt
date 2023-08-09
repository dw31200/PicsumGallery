package com.example.picsumgallery.ui.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.picsumgallery.ui.databinding.FragmentLikeBinding
import com.example.picsumgallery.ui.list.adapter.GalleryAdapter
import com.example.picsumgallery.ui.list.adapter.GalleryItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeFragment : Fragment() {
    private var _binding: FragmentLikeBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: LikeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.lifecycleOwner = this@LikeFragment
        binding.galleryLikeitemList.adapter = GalleryAdapter()
        binding.galleryLikeitemList.addItemDecoration(GalleryItemDecoration())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
