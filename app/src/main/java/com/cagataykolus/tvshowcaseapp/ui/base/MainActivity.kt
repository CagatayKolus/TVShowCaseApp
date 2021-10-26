package com.cagataykolus.tvshowcaseapp.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cagataykolus.tvshowcaseapp.databinding.ActivityMainBinding
import com.cagataykolus.tvshowcaseapp.util.viewBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */
@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val binding by viewBinding { ActivityMainBinding.inflate(it) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}