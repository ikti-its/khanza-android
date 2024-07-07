package dev.ikti.kehadiran.domain.model

data class PeninjauanPerizinan(
    val id: String,
    val pegawai: String,
    val nama: String,
    val mulai: String,
    val selesai: String,
    val alasan: String,
    val status: String
)
