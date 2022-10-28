package com.example.twofragmentcontainer.network

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

private const val BASE_URL = "https://api.football-data.org/v4/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
//    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addConverterFactory(ScalarsConverterFactory.create())
    .build()


interface FootballApiService {
    @Headers(
        "X-Auth-Token: 2f67f03237b64b439d968fd328c4aa77")
    @GET("competitions/{code}/standings")
    suspend fun getTeams(@Path("code") leagueCode: String): String

    @Headers(
        "X-Auth-Token: 2f67f03237b64b439d968fd328c4aa77")
    @GET("competitions/{code}/teams")
    suspend fun getPlayers(@Path("code") leagueCode: String): String
}

object FootballApi {
    val retrofitService: FootballApiService by lazy {retrofit.create(FootballApiService::class.java)}
}