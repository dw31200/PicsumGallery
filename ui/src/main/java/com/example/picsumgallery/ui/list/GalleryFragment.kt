package com.example.picsumgallery.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import com.example.picsumgallery.ui.databinding.FragmentGalleryBinding
import com.example.picsumgallery.ui.list.adapter.GalleryAdapter
import com.example.picsumgallery.ui.list.adapter.GalleryItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class GalleryFragment : Fragment(), GalleryNavigation {
    private var _binding: FragmentGalleryBinding? = null
    private val binding
        get() = _binding!!
    private val viewModel: GalleryViewModel by viewModels()

    //    region fragment lifecycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.vm = viewModel
        binding.navigation = this@GalleryFragment
        binding.lifecycleOwner = this@GalleryFragment
        val pagingAdapter = GalleryAdapter()
        binding.galleryList.adapter = pagingAdapter
        lifecycleScope.launch {
            pagingAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.progressLoading.isVisible = loadStates.refresh is LoadState.Loading
            }
        }
        binding.galleryList.addItemDecoration(GalleryItemDecoration())
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //    endregion
    override fun navigateToDetail(galleryId: Int) {
        val action = GalleryFragmentDirections.actionGalleryFragmentToGalleryDetailFragment(galleryId)
        findNavController().navigate(action)
    }
}
