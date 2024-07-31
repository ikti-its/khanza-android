package dev.ikti.kehadiran.data.model

import com.google.gson.annotations.SerializedName

data class TukarRequest(
    @SerializedName("id_sender")
    val sender: String,
    @SerializedName("id_recipient")
    val recipient: String,
    @SerializedName("id_hari")
    val hari: Int,
    @SerializedName("id_shift_sender")
    val senderShift: String,
    @SerializedName("id_shift_recipient")
    val recipientShift: String,
    @SerializedName("status")
    val status: String
)
