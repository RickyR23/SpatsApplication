package com.example.spatsapplication.models.response

data class SpotifyAccessTokenResponse(
    val access_token: String? = null,
    val token_type: String? = null,
    val expires_in: Int = 0,
    val refresh_token: String? = null
)