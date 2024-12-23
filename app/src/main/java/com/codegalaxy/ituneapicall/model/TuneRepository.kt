package com.codegalaxy.ituneapicall.model

import retrofit2.Response
import javax.inject.Inject

class TuneRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun fetchArtist(term : String) : Response<ArtistData>{
        return apiService.getData(t = term)
    }
}