package com.cagataykolus.tvshowcaseapp.ui.home.viewholder

import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cagataykolus.tvshowcaseapp.R
import com.cagataykolus.tvshowcaseapp.databinding.ItemEpisodeBinding
import com.cagataykolus.tvshowcaseapp.model.Episode
import com.cagataykolus.tvshowcaseapp.util.AppUtil

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
class HomeViewHolder(private val binding: ItemEpisodeBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(episode: Episode, onItemClicked: (Episode) -> Unit) {
        binding.textviewEpisodeShowName.text = episode.tvShowName?.capitalize()
        binding.textviewEpisodeTitle.text = episode.Title
        binding.textviewEpisodeSeasonAndEpisode.text = AppUtil.seasonEpisodeTextGenerator(episode)

        binding.imageviewEpisodePoster.load(episode.Poster) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken_image)
        }

        binding.root.setOnClickListener {
            onItemClicked(episode)
        }
    }
}