package com.example.app_domain.models

import com.google.gson.annotations.SerializedName

data class APITeamData(
    @SerializedName("data") val data: List<Team>
)