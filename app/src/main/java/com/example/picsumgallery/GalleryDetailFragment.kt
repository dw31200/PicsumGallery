package com.example.picsumgallery

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso

class GalleryDetailFragment : Fragment() {
    interface Callbacks {
        fun onUrlClicked(url: String)
    }

    private var callbacks: Callbacks? = null
    private lateinit var detailImageView: ImageView
    private lateinit var detailAuthorTextView: TextView
    private lateinit var detailWidthTextView: TextView
    private lateinit var detailHeightTextView: TextView
    private lateinit var detailwebSiteUrlTextView: TextView
    private lateinit var detailUrlTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        val view = inflater.inflate(R.layout.fragment_gallery_detail, container, false)

        detailImageView = view.findViewById(R.id.detail_imageView)
        detailAuthorTextView = view.findViewById(R.id.detail_author_textView)
        detailWidthTextView = view.findViewById(R.id.detail_width_textView)
        detailHeightTextView = view.findViewById(R.id.detail_height_textView)
        detailwebSiteUrlTextView = view.findViewById(R.id.detail_webSiteUrl_textView)
        detailwebSiteUrlTextView.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(arguments?.getString("image_webSiteUrl"))
                .buildUpon()
                .build())
            startActivity(intent)
        }
        detailUrlTextView = view.findViewById(R.id.detail_url_textView)
        detailUrlTextView.setOnClickListener {

            callbacks?.onUrlClicked(detailUrlTextView.text.toString())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()
    }

    private fun updateUI() {
        Picasso.get()
            .load(arguments?.getString("image_url"))
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(detailImageView)
        detailAuthorTextView.text = arguments?.getString("image_author")
        detailWidthTextView.text = arguments?.getString("image_width")
        detailHeightTextView.text = arguments?.getString("image_height")
        detailwebSiteUrlTextView.text = arguments?.getString("image_webSiteUrl")
        detailUrlTextView.text = arguments?.getString("image_url")
    }

    companion object {
        fun newInstance(galleryItem: Picsum): GalleryDetailFragment {
            val args = Bundle().apply {
                putString("image_id", galleryItem.id)
                putString("image_author", galleryItem.author)
                putString("image_width", galleryItem.width)
                putString("image_height", galleryItem.height)
                putString("image_webSiteUrl", galleryItem.webSiteUrl)
                putString("image_url", galleryItem.url)
            }
            return GalleryDetailFragment().apply {
                arguments = args
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }
}