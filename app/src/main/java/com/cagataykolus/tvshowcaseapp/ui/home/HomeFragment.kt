package com.cagataykolus.tvshowcaseapp.ui.home

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.cagataykolus.tvshowcaseapp.R
import com.cagataykolus.tvshowcaseapp.databinding.FragmentHomeBinding
import com.cagataykolus.tvshowcaseapp.model.Episode
import com.cagataykolus.tvshowcaseapp.model.State
import com.cagataykolus.tvshowcaseapp.ui.home.adapter.HomeAdapter
import com.cagataykolus.tvshowcaseapp.util.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@AndroidEntryPoint
class HomeFragment : Fragment(R.layout.fragment_home) {
    private val binding by viewBinding { FragmentHomeBinding.bind(it) }
    private val viewModel by viewModels<HomeViewModel>()
    private val adapter = HomeAdapter(this::onItemClicked)

    override fun onStart() {
        super.onStart()

        observeEpisodes()
        handleNetworkChanges()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
    }

    /**
     * Observe data.
     */
    private fun observeEpisodes() {
        viewModel.episodesLiveData.observe(viewLifecycleOwner) { state ->
            hideKeyboard()
            when (state) {
                is State.Loading -> {
                    showLoading(true)
                }
                is State.Success -> {
                    if (state.data.isNotEmpty()) {
                        adapter.submitList(state.data.toMutableList())
                        showLoading(false)
                    }
                }
                is State.Error -> {
                    Toast.makeText(requireContext(), state.message, Toast.LENGTH_SHORT).show()
                    showLoading(false)
                }
            }
        }
    }

    private fun getEpisodes() = viewModel.getEpisodes()

    /**
     * Initialize recyclerview with values.
     */
    private fun initView() {
        binding.run {
            recyclerviewHomeList.adapter = adapter
            swiperefreshlayoutHomeRefresh.setOnRefreshListener { getEpisodes() }
        }

        viewModel.episodesLiveData.value?.let { currentState ->
            if (!currentState.isSuccessful()) {
                getEpisodes()
            }
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.swiperefreshlayoutHomeRefresh.isRefreshing = isLoading
    }

    /**
     * If click any item, navigate to detail fragment.
     */
    private fun onItemClicked(episode: Episode) {
        findNavController().navigate(
            R.id.action_homeFragment_to_detailFragment,
            bundleOf(
                "DETAIL" to episode
            )
        )
    }

    /**
     * Observes network changes.
     */
    private fun handleNetworkChanges() {
        NetworkUtils.getNetworkLiveData(requireContext()).observe(this) { isConnected ->
            if (!isConnected) {
                binding.textviewHomeNetworkStatus.text =
                    getString(R.string.internet_connectivity_fail)
                binding.linearlayoutHomeNetworkStatus.apply {
                    show()
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_fail
                        )
                    )
                }
            } else {
                if (viewModel.episodesLiveData.value is State.Error || adapter.itemCount == 0) {
                    getEpisodes()
                }
                binding.textviewHomeNetworkStatus.text =
                    getString(R.string.internet_connectivity_success)
                binding.linearlayoutHomeNetworkStatus.apply {
                    setBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.connectivity_success
                        )
                    )

                    animate()
                        .alpha(1f)
                        .setStartDelay(1000L)
                        .setDuration(1000L)
                        .setListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                hide()
                            }
                        })
                }
            }
        }
    }
}