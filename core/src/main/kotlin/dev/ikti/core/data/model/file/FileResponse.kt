package dev.ikti.core.data.model.file

import com.google.gson.annotations.SerializedName

data class FileResponse(
    @SerializedName("url")
    val url: String
)
