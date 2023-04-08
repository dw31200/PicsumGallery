package com.example.picsumgallery

import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class GalleryFragment : Fragment() {
    interface Callbacks {
        fun onImageSelected(galleryItem: Picsum)
    }

    private var callbacks: Callbacks? = null
    private lateinit var galleryViewModel: GalleryViewModel
    private lateinit var galleryRecyclerView: RecyclerView
    private lateinit var imageDownloader: ImageDownloader<GalleryHolder>

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        retainInstance = true

        galleryViewModel = ViewModelProvider(this).get(GalleryViewModel::class.java)
        val responseHandler = Handler()
        imageDownloader =
            ImageDownloader(responseHandler) { galleryHolder, bitmap ->
                val drawable = BitmapDrawable(resources, bitmap)
                galleryHolder.bindDrawable(drawable)
            }
        lifecycle.addObserver(imageDownloader.fragmentLifecycleObserver)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        viewLifecycleOwner.lifecycle.addObserver(
            imageDownloader.viewLifecycleObserver
        )
        val view = inflater.inflate(R.layout.fragment_gallery, container, false)

        galleryRecyclerView = view.findViewById(R.id.gallery_list)
        galleryRecyclerView.layoutManager = GridLayoutManager(context, 2)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        galleryViewModel.picsumLiveData.observe(
            viewLifecycleOwner,
            Observer { galleryItems ->
                galleryRecyclerView.adapter = GalleryAdapter(galleryItems)
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewLifecycleOwner.lifecycle.removeObserver(
            imageDownloader.viewLifecycleObserver
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycle.removeObserver(
            imageDownloader.fragmentLifecycleObserver
        )
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance() = GalleryFragment()
    }

    private inner class GalleryHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private lateinit var galleryItem: Picsum
        private val galleryImageView: ImageView = itemView.findViewById(R.id.gallery_image)
        private val galleryAuthorTextView: TextView = itemView.findViewById(R.id.gallery_author)
        val bindDrawable: (Drawable) -> Unit = galleryImageView::setImageDrawable

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(galleryItem: Picsum) {
            this.galleryItem = galleryItem
            galleryAuthorTextView.text = this.galleryItem.author
        }

        fun bindGalleryItem(galleryItem: Picsum) {
            Picasso.get()
                .load(galleryItem.url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(galleryImageView)
        }

        override fun onClick(view: View) {
            callbacks?.onImageSelected(galleryItem)
        }
    }

    private inner class GalleryAdapter(var galleryItems: List<Picsum>) : RecyclerView.Adapter<GalleryHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_gallery, parent, false)
            return GalleryHolder(view)
        }

        override fun onBindViewHolder(holder: GalleryHolder, position: Int) {
            val galleryItem = galleryItems[position]
            val galleryHolder: Drawable = ContextCompat.getDrawable(
                requireContext(),
                R.drawable.ic_launcher_foreground
            ) ?: ColorDrawable()
            holder.bindDrawable(galleryHolder)

            holder.bindGalleryItem(galleryItem)
//            imageDownloader.queueImage(holder, galleryItem.url)
            holder.bind(galleryItem)
        }

        override fun getItemCount(): Int = galleryItems.size
    }
}