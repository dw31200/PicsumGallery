package com.example.picsumgallery.ui.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.doOnAttach
import androidx.core.view.doOnDetach
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.recyclerview.widget.RecyclerView
import com.example.picsumgallery.data.Picsum
import com.example.picsumgallery.databinding.ListItemGalleryBinding

class GalleryHolder(
    private val binding: ListItemGalleryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private val viewModel by lazy {
        GalleryHolderViewModel()
    }
    private var lifecycleOwner: LifecycleOwner? = null
    private val _galleryItem = MutableLiveData<Picsum>()
    val galleryItem: LiveData<Picsum>
        get() = _galleryItem

    //    todo ViewModel 을 만들고 binding 등 fragment나 Holder binding을 어떻게 가져가나요?
//    todo bind 함수 불리는 상태를 컨트롤하지 못하겠어요.
    fun bind(galleryItem: Picsum, onClick: GalleryAdapter.onItemClickListener?) {
        _galleryItem.value = galleryItem
        with(binding) {
            galleryImage.setOnClickListener {
                onClick?.onClick(galleryItem.id)
            }
        }
    }

    init {
        binding.vm = viewModel
        binding.holder = this@GalleryHolder
        itemView.doOnAttach {
            lifecycleOwner = itemView.findViewTreeLifecycleOwner()
        }
        itemView.doOnDetach {
            lifecycleOwner = null
        }
        binding.lifecycleOwner = lifecycleOwner
    }

    companion object {
        operator fun invoke(parent: ViewGroup): GalleryHolder {
            val binding =
                ListItemGalleryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return GalleryHolder(binding)
        }
    }
}
