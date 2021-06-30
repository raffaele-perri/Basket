package com.example.app_domain.models

import com.google.gson.annotations.SerializedName

data class Team (
    @SerializedName("id") val id : Int, @SerializedName("abbreviation") val abbreviation : String,
    @SerializedName("city") val city : String, @SerializedName("conference") val conference : String,
    @SerializedName("division") val division: String, @SerializedName("name") val name:String,
    @SerializedName("fullName") val fullName : String
)