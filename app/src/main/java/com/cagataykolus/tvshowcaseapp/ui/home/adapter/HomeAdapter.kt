package com.cagataykolus.tvshowcaseapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.cagataykolus.tvshowcaseapp.databinding.ItemEpisodeBinding
import com.cagataykolus.tvshowcaseapp.model.Episode
import com.cagataykolus.tvshowcaseapp.ui.home.viewholder.HomeViewHolder

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
class HomeAdapter(
    private val onItemClicked: (Episode) -> Unit
) : ListAdapter<Episode, HomeViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = HomeViewHolder(
        ItemEpisodeBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Episode>() {
            override fun areItemsTheSame(oldItem: Episode, newItem: Episode): Boolean =
                oldItem.imdbID == newItem.imdbID

            override fun areContentsTheSame(oldItem: Episode, newItem: Episode): Boolean =
                oldItem == newItem
        }
    }
}
