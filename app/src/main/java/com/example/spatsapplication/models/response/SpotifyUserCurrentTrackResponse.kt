package com.example.spatsapplication.models.response

data class SpotifyUserCurrentTrackResponse(
    val items: List<Item>? = null
) {
    fun getLastPlayedTrack(): Item? {
        return if (!items.isNullOrEmpty()) items[0] else null
    }

    data class Item(
        val track: Track? = null
    )

    data class Track(
        val name: String? = null,
        val artists: List<Artist>? = null,
        val album: Album? = null
    ) {
        fun getFirstArtist(): String {
            return if (!artists.isNullOrEmpty()) artists[0].name ?: "Unknown Artist" else "Unknown Artist"
        }
    }

    data class Artist(
        val name: String? = null
    )

    data class Album(
        val name: String? = null,
        val images: List<Image>? = null
    ) {
        fun getAlbumImage(): String? {
            return if (!images.isNullOrEmpty()) images[0].url else null
        }
    }

    data class Image(
        val url: String? = null
    )
}