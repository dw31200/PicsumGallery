package com.example.picsumgallery.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.picsumgallery.databinding.FragmentGalleryDetailBinding
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
        // TODO xml에서 interface를 넣어줬는데 interface를 상속받은 프레그먼트를 넣는 원리를 이해 못하겠어요.
        binding.showWebSite = this@GalleryDetailFragment
        binding.lifecycleOwner = this@GalleryDetailFragment
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    // endregion
    override fun showWebSite(webSiteUrl: String) {
        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse(webSiteUrl),
        )
        startActivity(intent)
    }

    companion object {
        private const val BUNDLE_ID = "image_id"
        fun args(galleryId: Int): Bundle {
            val args = Bundle().apply {
                putInt(BUNDLE_ID, galleryId)
            }
            return args
        }

        // TODO fragment의 함수를 provides 하는 방법이 있나요?
        fun providesGalleryId(args: Bundle?): Int {
            return args?.getInt(BUNDLE_ID) ?: -1
        }
    }
}
