package com.example.picsumgallery.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.ui.R
import com.example.picsumgallery.ui.databinding.FragmentGalleryBinding
import com.example.picsumgallery.ui.detail.GalleryDetailFragment
import com.example.picsumgallery.ui.detail.GalleryDetailFragment.Companion.args
import com.example.picsumgallery.ui.list.adapter.GalleryAdapter
import com.example.picsumgallery.ui.list.adapter.GalleryItemDecoration
import dagger.hilt.android.AndroidEntryPoint

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
        binding.galleryList.adapter = GalleryAdapter()
        binding.galleryList.addItemDecoration(GalleryItemDecoration())
        binding.galleryList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                val lastCompletelyVisibleItemPosition = (layoutManager as? GridLayoutManager)?.findLastCompletelyVisibleItemPosition()
                val itemCount = recyclerView.adapter?.itemCount?.minus(1)

                if (lastCompletelyVisibleItemPosition == itemCount) {
                    viewModel.onLoadNextPage()
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //    endregion
    override fun navigateToDetail(galleryId: Int) {
        parentFragmentManager.commit {
            setCustomAnimations(
                R.anim.slide_in,
                R.anim.fade_out,
                R.anim.fade_in,
                R.anim.slide_out,
            )
            add<GalleryDetailFragment>(
                R.id.fragment_container,
                args = args(galleryId),
            )
            addToBackStack(null)
        }
    }
}
