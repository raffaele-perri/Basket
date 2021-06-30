package com.example.basket.framework.network

import android.util.Log
import com.example.app_data.network.INetworkDataSource
import com.example.app_domain.models.Player
import com.example.app_domain.models.Team
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NetworkDataSource @Inject constructor(private val basketApi: IBasketAPI): INetworkDataSource {
    override suspend fun getTeams(): List<Team> {
        return try {
            val response = basketApi.getTeams()
            if (response.isSuccessful) {
                Log.d("RESPONSE", "onResponse: ${response.body()!!}")
                response.body()?.data
            } else
                emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }!!
    }

    override suspend fun getPlayers(): List<Player> {
        return try {
            val response = basketApi.getPlayers()
            if (response.isSuccessful) {
                Log.d("RESPONSE", "onResponse: ${response.body()!!}")
                response.body()?.data
            } else
                emptyList()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }!!
    }

    override suspend fun getPlayerById(id: Int): Player {
        return try {
            val response = basketApi.getPlayerById(id)
            if (response.isSuccessful) {
                Log.d("RESPONSE", "onResponse: ${response.body()!!}")
                response.body()
            } else
                Player(0,"",0,0,"","",0,Team(0,"","","","","",""))
        } catch (e: Exception) {
            e.printStackTrace()
            Player(0,"",0,0,"","",0,Team(0,"","","","","",""))
        }!!
    }

}
