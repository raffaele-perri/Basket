package com.example.app_domain.interfaces

import com.example.app_domain.models.Player
import com.example.app_domain.models.Team

interface IBasketRepository {

    suspend fun getTeams() : List<Team>
    suspend fun getPlayers() : List<Player>
    suspend fun getPlayerById(id: Int) : Player

}