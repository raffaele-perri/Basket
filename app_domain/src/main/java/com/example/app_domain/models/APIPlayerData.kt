package com.example.app_domain.models

import com.google.gson.annotations.SerializedName

data class APIPlayerData (
    @SerializedName("data") val data: List<Player>,
    @SerializedName("meta") val meta: Meta
)

data class Meta(
    @SerializedName("total_pages") val totalPages : Int,
    @SerializedName("current_page") val currentPage : Int,
    @SerializedName("next_page") val nextPage : Int,
    @SerializedName("per_page") val perPage : Int,
    @SerializedName("total_count") val totalCount : Int,
    )
