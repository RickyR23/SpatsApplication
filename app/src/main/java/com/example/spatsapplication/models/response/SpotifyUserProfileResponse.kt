package com.example.spatsapplication.models.response

data class SpotifyUserProfileResponse(
    val display_name: String? = null,
    val email: String? = null,
    val external_urls: Map<String, String>? = null,
    val images: List<Image>? = null
) {
    fun getDisplayName(): String? {
        return display_name
    }

    fun getEmail(): String? {
        return email
    }

    fun getExternalUrls(): Map<String, String>? {
        return external_urls
    }

    fun getSpotify(): String? {
        return external_urls?.get("spotify")
    }

    fun getImages(): List<Image>? {
        return images
    }

    data class Image(
        val url: String? = null
    ) {
        fun getUrl(): String? {
            return url
        }
    }
}