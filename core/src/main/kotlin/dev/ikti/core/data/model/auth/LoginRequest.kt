package dev.ikti.core.data.model.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LoginRequest(
    @Json(name = "nip") val nip: String,
    @Json(name = "password") val password: String
)
