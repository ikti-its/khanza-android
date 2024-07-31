package dev.ikti.kehadiran.domain.model

data class DaftarJadwal(
    val id: String,
    val pegawaiId: String,
    val pegawai: String,
    val shiftId: String,
    val masuk: String,
    val pulang: String
)
