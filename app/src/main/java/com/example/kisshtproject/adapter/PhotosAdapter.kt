package com.example.kisshtproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kisshtproject.R
import com.example.kisshtproject.model.ResPhotos
import com.example.kisshtproject.viewholder.PhotosViewHolder

class PhotosAdapter(private val mContext: Context) : RecyclerView.Adapter<PhotosViewHolder>() {
    private val photosLst = ArrayList<ResPhotos>()

    fun setData(lst: ArrayList<ResPhotos>) {
        if (lst.isNotEmpty()) {
            photosLst.clear()
            photosLst.addAll(lst)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.item_photo, parent, false)
        return PhotosViewHolder(mContext, view)
    }

    override fun onBindViewHolder(holder: PhotosViewHolder, position: Int) {
        holder.bindData(photosLst.get(position))
    }

    override fun getItemCount(): Int {
        return photosLst.size
    }
}