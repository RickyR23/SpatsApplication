package com.example.spatsapplication.services

import com.example.spatsapplication.models.response.SpotifyAccessTokenResponse
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthService {

    // Spotify Auth Tokens --------------
    @FormUrlEncoded
    @POST("api/token")
    fun getAccessToken(
        @Field("grant_type") grantTypem: String,
        @Field("code") code: String,
        @Field("redirect_uri") redirectUri: String,
        @Header("Authorization") authorizationHeader: String
    ): Observable<SpotifyAccessTokenResponse>

    @FormUrlEncoded
    @POST("api/token")
    fun refreshAccessToken(
        @Field("grant_type") grantType: String,
        @Field("refresh_token") refreshToken: String,
        @Header("Authorization") authorizationHeader: String
    ): Observable<SpotifyAccessTokenResponse>
}