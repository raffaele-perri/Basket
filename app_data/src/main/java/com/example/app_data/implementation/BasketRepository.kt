package com.example.app_data.implementation

import com.example.app_data.network.INetworkDataSource
import com.example.app_domain.interfaces.IBasketRepository
import com.example.app_domain.models.APIPlayerData
import com.example.app_domain.models.Player
import com.example.app_domain.models.Team
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BasketRepository @Inject constructor(private val networkDataSource: INetworkDataSource): IBasketRepository {
    override suspend fun getTeams(): List<Team> {
        return networkDataSource.getTeams()
    }

    override suspend fun getPlayers(): APIPlayerData {
        return networkDataSource.getPlayers()
    }

    override suspend fun getPlayers(page: Int, perPage: Int): APIPlayerData {
        return networkDataSource.getPlayers(page,perPage)
    }

    override suspend fun getPlayers(page: Int, perPage: Int,search:String): APIPlayerData {
        return networkDataSource.getPlayers(page,perPage,search)
    }

    override suspend fun getPlayerById(id:Int): Player {
        return networkDataSource.getPlayerById(id)
    }
}

