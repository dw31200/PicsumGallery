package com.example.picsumgallery.ui.like

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.picsumgallery.ui.databinding.FragmentLikeBinding

class LikeFragment : Fragment() {
    private var _binding: FragmentLikeBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }
}
