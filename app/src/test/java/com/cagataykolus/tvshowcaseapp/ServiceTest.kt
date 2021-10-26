package com.cagataykolus.tvshowcaseapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.cagataykolus.tvshowcaseapp.data.remote.api.TVShowCaseApiService
import com.cagataykolus.tvshowcaseapp.model.Episode
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.core.IsNull.notNullValue
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/**
 * Created by Çağatay Kölüş on 24.10.2021.
 * cagataykolus@gmail.com
 */

@RunWith(JUnit4::class)
class ServiceTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: TVShowCaseApiService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun createService() {
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            )
            .build()
            .create(TVShowCaseApiService::class.java)
    }

    @After
    fun stopService() {
        mockWebServer.shutdown()
    }

    @Test
    fun getTVShowCaseTest() = runBlocking {
        enqueueResponse("testdata.json")
        val tvShowCase = service.getTVShowCase().body()
        assertThat(tvShowCase, notNullValue())
        assertThat(tvShowCase!!.seasons.size, `is`(4))

        val episodes = mutableListOf<Episode>()
        tvShowCase.seasons.forEach { season ->
            season.episodes?.forEach { episodes.add(it) }
        }
        assertThat(episodes.size, `is`(32))
        assertThat(tvShowCase.seasons[0].episodes?.get(0)?.Title, `is`("Minimum Viable Product"))
    }

    private fun enqueueResponse(fileName: String, headers: Map<String, String> = emptyMap()) {
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for ((key, value) in headers) {
            mockResponse.addHeader(key, value)
        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }
}
