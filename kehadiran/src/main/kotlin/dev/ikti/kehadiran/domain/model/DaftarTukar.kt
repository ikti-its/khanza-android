package dev.ikti.kehadiran.domain.model

data class DaftarTukar(
    val id: String,
    val nama: String,
    val sender: String,
    val recipient: String,
    val hari: Int,
    val senderShift: String,
    val recipientShift: String,
    val status: String
)
