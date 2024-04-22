package dev.ikti.core.data.model

import com.google.gson.annotations.SerializedName

data class BaseResponse<out T>(
    @SerializedName("code")
    val code: Int,
    @SerializedName("status")
    val status: String,
    @SerializedName("data")
    val data: T
)
