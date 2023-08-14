package com.example.picsumgallery.ui.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.picsumgallery.ui.databinding.FragmentLikeBinding
import com.example.picsumgallery.ui.like.adpater.LikeAdapter
import com.example.picsumgallery.ui.like.adpater.LikeItemDecoration
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikeFragment : Fragment(), LikeNavigation {
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
        binding.navigation = this@LikeFragment
        binding.galleryLikeitemList.adapter = LikeAdapter()
        binding.galleryLikeitemList.addItemDecoration(LikeItemDecoration())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun navigateToDetail(galleryId: Int) {
        val action = LikeFragmentDirections.actionLikeFragmentToGalleryDetailFragment(galleryId)
        findNavController().navigate(action)
    }
}
