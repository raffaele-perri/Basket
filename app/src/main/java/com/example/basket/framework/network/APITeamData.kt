package com.example.basket.framework.network

import com.example.app_domain.models.Team
import com.google.gson.annotations.SerializedName

data class APITeamData(
    @SerializedName("data") val data: List<Team>
)