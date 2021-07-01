package com.example.basket.framework.network

import com.example.app_domain.models.APIPlayerData
import com.example.app_domain.models.APITeamData
import com.example.app_domain.models.Player
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface IBasketAPI {

    @GET("teams")
    suspend fun getTeams() : Response<APITeamData>
    @GET("players")
    suspend fun getPlayers() : Response<APIPlayerData>
    @GET("players/{id}")
    suspend fun getPlayerById(@Path("id") id : Int) : Response<Player>
    @GET("players")
    suspend fun getPlayers(@Query("page") page:Int,
                           @Query("per_page") perPage:Int) : Response<APIPlayerData>
    @GET("players")
    suspend fun getPlayers(@Query("page") page:Int, @Query("per_page") perPage:Int,
                           @Query("search") search:String) : Response<APIPlayerData>
}