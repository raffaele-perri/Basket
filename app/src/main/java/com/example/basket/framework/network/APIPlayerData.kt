package com.example.basket.framework.network

import com.example.app_domain.models.Player
import com.google.gson.annotations.SerializedName

data class APIPlayerData (
    @SerializedName("data") val data: List<Player>
)