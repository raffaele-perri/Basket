package com.example.app_data.network

import com.example.app_domain.models.APIPlayerData
import com.example.app_domain.models.Player
import com.example.app_domain.models.Team

interface INetworkDataSource {

    suspend fun getTeams(): List<Team>
    suspend fun getPlayers(): APIPlayerData
    suspend fun getPlayerById(id: Int): Player
    suspend fun getPlayers(page:Int, perPage:Int): APIPlayerData
    suspend fun getPlayers(page:Int, perPage:Int,search:String): APIPlayerData
}