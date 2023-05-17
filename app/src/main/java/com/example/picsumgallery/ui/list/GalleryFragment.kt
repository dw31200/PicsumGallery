package com.example.picsumgallery.ui.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.R
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.FragmentGalleryBinding
import com.example.picsumgallery.ui.detail.GalleryDetailFragment
import com.example.picsumgallery.ui.detail.GalleryDetailFragment.Companion.args
import com.example.picsumgallery.ui.list.adapter.GalleryAdapter
import com.example.picsumgallery.ui.list.adapter.GalleryItemDecoration
import com.example.picsumgallery.ui.list.contract.GalleryContract
import com.example.picsumgallery.ui.list.contract.GalleryPresenter
import kotlinx.coroutines.CoroutineScope

class GalleryFragment : Fragment(), GalleryContract.View {
    private var _binding: FragmentGalleryBinding? = null
    private val binding
        get() = _binding!!
    private lateinit var presenter: GalleryPresenter

    //    region fragment lifecycle
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        presenter = GalleryPresenter(this)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        super.onViewCreated(view, savedInstanceState)
        binding.galleryList.adapter = GalleryAdapter { galleryId -> presenter.onItemClicked(galleryId) }
        binding.galleryList.addItemDecoration(GalleryItemDecoration())
        binding.galleryList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = recyclerView.layoutManager
                val lastVisibleItemPosition = (layoutManager as GridLayoutManager).findLastVisibleItemPosition()
                val itemCount = recyclerView.adapter?.itemCount?.minus(1)

                if (lastVisibleItemPosition == itemCount) {
                    presenter.onLoadNextPage()
                }
            }
        })

        presenter.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    //    endregion
    //    region GalleryContract.View
    override val coroutineScope: CoroutineScope
        get() = this@GalleryFragment.lifecycleScope

    override fun setList(list: List<Picsum>) {
        (binding.galleryList.adapter as GalleryAdapter).fetchData(list)
    }

    override fun addList(list: List<Picsum>) {
        (binding.galleryList.adapter as GalleryAdapter).addData(list)
    }

    override fun navigateToDetail(galleryId: Int) {
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

    override fun showProgress() {
        binding.progressLoading.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        binding.progressLoading.visibility = View.GONE
    }
    //    endregion
}
