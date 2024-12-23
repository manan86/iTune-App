package com.codegalaxy.ituneapicall.model

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search")
    suspend fun getData(
        @Query("term") t : String
    ) : Response<ArtistData>
}