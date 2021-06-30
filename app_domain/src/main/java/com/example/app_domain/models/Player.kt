package com.example.app_domain.models

import com.google.gson.annotations.SerializedName

data class Player (
    @SerializedName("id") val id : Int, @SerializedName("first_name") val firstName : String,
    @SerializedName("height_feet") val  height_feet: Int, @SerializedName("height_inches") val height_inches : Int,
    @SerializedName("last_name") val lastName: String, @SerializedName("position") val position:String,
    @SerializedName("weight_pounds") val weightPounds : Int, @SerializedName("team") val team: Team
)