package dev.ikti.auth.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(
    @SerializedName("nip")
    val nip: String,
    @SerializedName("password")
    val password: String
)
