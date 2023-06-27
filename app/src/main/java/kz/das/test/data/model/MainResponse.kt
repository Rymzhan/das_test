package kz.das.test.data.model

import com.google.gson.annotations.SerializedName

data class MainResponse(
    @SerializedName("avatar")
    val avatar: String,
    @SerializedName("createdAt")
    val createdAt: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String
)