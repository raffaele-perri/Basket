package com.example.basket.framework.network

import com.example.app_domain.models.Player
import com.example.app_domain.models.Team
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface IBasketAPI {

    @GET("teams")
    suspend fun getTeams() : Response<APITeamData>
    @GET("players")
    suspend fun getPlayers() : Response<List<Player>>
}