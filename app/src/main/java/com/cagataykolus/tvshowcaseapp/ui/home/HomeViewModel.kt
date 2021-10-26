package com.cagataykolus.tvshowcaseapp.ui.home

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cagataykolus.tvshowcaseapp.data.repository.EpisodeRepository
import com.cagataykolus.tvshowcaseapp.model.Episode
import com.cagataykolus.tvshowcaseapp.model.State
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val episodeRepository: EpisodeRepository) : ViewModel() {
    private val _episodesLiveData = MutableLiveData<State<List<Episode>>>()
    val episodesLiveData: LiveData<State<List<Episode>>> = _episodesLiveData

    fun getEpisodes() {
        viewModelScope.launch {
            episodeRepository.deleteAllEpisodes()
                .onStart {}
                .map {}
                .collect {}
        }

        val timer = object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {}
            override fun onFinish() {
                viewModelScope.launch {
                    episodeRepository.getAllEpisodes()
                        .onStart { _episodesLiveData.value = State.loading() }
                        .map { resource -> State.fromResource(resource) }
                        .collect { state -> _episodesLiveData.value = state }
                }
            }
        }
        timer.start()
    }
}