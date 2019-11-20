package dev.moataz.flickrim.view.adapter

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dev.moataz.flickrim.model.data.Photo
import dev.moataz.flickrim.R
import kotlinx.android.synthetic.main.adapter_row.view.*
import java.io.ByteArrayOutputStream
import java.lang.Exception

class PhotosAdapter(private val onPhotoClicked: (imageUrl: String) -> Unit)
    : PagedListAdapter<Photo, PhotosAdapter.MyViewHolder>(
    DiffUtilCallBack()
) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_row, parent
            , false)
        val holder = MyViewHolder(view)

        holder.itemView.iv_image.setOnClickListener {
            if (holder.adapterPosition != RecyclerView.NO_POSITION){
                onPhotoClicked.invoke(getItem(holder.adapterPosition)?.getImageUrl()?:"")
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        getItem(position)?.let { holder.bindPhoto(it) }
    }


    class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val ivImage = itemView.iv_image
        val tvTitle = itemView.tv_title

        fun bindPhoto(photo: Photo){
            with(photo){
                Picasso.get()
                    .load(getImageUrl())
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.placeholder)
                    .fit().centerCrop()
                    .into(ivImage, object : Callback {
                        override fun onSuccess() {
                            if(image == null) {
                                val bpd = ivImage.drawable as BitmapDrawable
                                val stream = ByteArrayOutputStream()
                                bpd.bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
                                image = stream.toByteArray()
                            }

                        }

                        override fun onError(e: Exception?) {
                        }

                    })
            }


        }
    }

}