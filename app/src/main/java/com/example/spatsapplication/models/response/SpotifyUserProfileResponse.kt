package com.example.spatsapplication.models.response

import com.google.gson.annotations.SerializedName

data class SpotifyUserProfileResponse(
    @SerializedName("display_name")
    val displayName: String?,

    @SerializedName("email")
    val email: String?,

    @SerializedName("external_urls")
    val externalUrls: Map<String, String>?,

    @SerializedName("images")
    val images: List<Image>?
) {
    val spotify: String?
        get() = externalUrls?.get("spotify")

    data class Image(
        @SerializedName("url")
        val url: String?
    )
}