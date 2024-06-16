package dev.ikti.pegawai.domain.model

data class Ketersediaan(
    val nama: String,
    val foto: String,
    val nip: String,
    val telepon: String,
    val jabatan: String,
    val departemen: String,
    val alamat: String,
    val distance: Double,
    val available: String,
    val isExpanded: Boolean
)
