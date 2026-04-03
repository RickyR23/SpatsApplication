package com.example.spatsapplication.services

import com.example.spatsapplication.models.response.SpotifyUserCurrentTrackResponse
import com.example.spatsapplication.models.response.SpotifyUserProfileResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Header

interface UserInfoService {

    // Spotify User Data
    @GET("v1/me")
    fun getUserProfileData(
        @Header("Authorization") accessToken: String
    ): Observable<SpotifyUserProfileResponse>

    @GET("v1/me")
    fun getUserRecentlyPlayedTracks(
        @Header("Authorization") accessToken: String
    ): Observable<SpotifyUserCurrentTrackResponse>
}