package com.example.app_data.implementation

import com.example.app_data.network.INetworkDataSource
import com.example.app_domain.interfaces.IBasketRepository
import com.example.app_domain.models.Player
import com.example.app_domain.models.Team
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BasketRepository @Inject constructor(private val networkDataSource: INetworkDataSource): IBasketRepository {
    override suspend fun getTeams(): List<Team> {
        return networkDataSource.getTeams()
    }

    override suspend fun getPlayers(): List<Player> {
        return networkDataSource.getPlayers()
    }

    override suspend fun getPlayers(query: String): List<Player> {
        TODO("Not yet implemented")
    }
}

