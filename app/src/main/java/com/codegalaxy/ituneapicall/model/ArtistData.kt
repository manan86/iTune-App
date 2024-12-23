package com.codegalaxy.ituneapicall.model

import com.google.gson.annotations.SerializedName

data class ArtistData(
    val resultCount : String,
    @SerializedName("results")
    val results : List<Artist>
)

data class Artist(
    val artistId : Int,
    val artistName : String,
    val country : String,
    val artworkUrl100 : String
)