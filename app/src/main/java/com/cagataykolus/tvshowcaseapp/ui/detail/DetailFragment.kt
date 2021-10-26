package com.cagataykolus.tvshowcaseapp.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import coil.load
import coil.transform.CircleCropTransformation
import com.cagataykolus.tvshowcaseapp.R
import com.cagataykolus.tvshowcaseapp.databinding.FragmentDetailBinding
import com.cagataykolus.tvshowcaseapp.model.Episode
import com.cagataykolus.tvshowcaseapp.util.AppUtil
import com.cagataykolus.tvshowcaseapp.util.viewBinding

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private val binding by viewBinding { FragmentDetailBinding.bind(it) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val content = arguments?.getParcelable<Episode>("DETAIL")
        content?.let {
            showDetails(it)
        }
    }

    private fun showDetails(episode: Episode) {
        binding.textviewDetailShowName?.text = episode.tvShowName
        binding.textviewDetailTitle?.text = episode.Title
        binding.textviewDetailSeasonAndEpisode?.text = AppUtil.seasonEpisodeTextGenerator(episode)
        binding.textviewDetailContent?.text = episode.Plot

        binding.imageviewDetailPhoto.load(episode.Poster) {
            placeholder(R.drawable.ic_placeholder)
            error(R.drawable.ic_broken_image)
        }
    }
}