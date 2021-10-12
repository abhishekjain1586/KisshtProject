package com.example.kisshtproject.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.kisshtproject.R
import com.example.kisshtproject.model.ResPhotos
import com.example.kisshtproject.utils.Utils
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_photo.view.*

class PhotosViewHolder(private val context: Context, private val view: View) : RecyclerView.ViewHolder(view) {

    fun bindData(obj: ResPhotos) {
        obj.urls?.thumb?.let {
            Picasso.with(context).load(it)
                .resize(Utils.convertDpToPx(context.resources, context.resources.getDimension(R.dimen.dimen_200dp))
                    .toInt(),
                    Utils.convertDpToPx(context.resources, context.resources.getDimension(R.dimen.dimen_100dp))
                        .toInt()
                )
                .centerInside()
                .into(view.ivThumbnail, object : Callback {
                    override fun onSuccess() {

                    }
                    override fun onError() {

                    }
                })
        }
    }
}