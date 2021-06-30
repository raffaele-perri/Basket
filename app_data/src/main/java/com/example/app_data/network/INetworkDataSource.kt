package com.example.app_data.network

import com.example.app_domain.models.Player
import com.example.app_domain.models.Team

interface INetworkDataSource {

    suspend fun getTeams(): List<Team>
    suspend fun getPlayers(): List<Player>
}