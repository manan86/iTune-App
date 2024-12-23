package com.codegalaxy.ituneapicall.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codegalaxy.ituneapicall.R
import com.codegalaxy.ituneapicall.databinding.ItemViewBinding
import com.codegalaxy.ituneapicall.model.Artist

class TuneAdapter(private val artistList : List<Artist>) : RecyclerView.Adapter<TuneAdapter.viewHodlerClass>()  {

    inner class viewHodlerClass(val binding : ItemViewBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHodlerClass {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemViewBinding.inflate(inflater,parent,false)
        return viewHodlerClass(binding)
    }

    override fun getItemCount() = artistList.size

    override fun onBindViewHolder(holder: viewHodlerClass, position: Int) {
        val list = artistList[position]

        with(holder.binding){
            tvId.text = list.artistId.toString()
            tvName.text = list.artistName
            tvCountry.text = list.country

            Glide.with(holder.itemView.context)
                .load(list.artworkUrl100 ?: R.drawable.ic_launcher_background)
                .into(imageView)

        }
    }
}