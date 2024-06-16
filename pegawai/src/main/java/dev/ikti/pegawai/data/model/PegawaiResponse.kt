package dev.ikti.pegawai.data.model

import com.google.gson.annotations.SerializedName

data class PegawaiResponse(
    @SerializedName("pegawai")
    val pegawai: String,
    @SerializedName("akun")
    val akun: String,
    @SerializedName("nip")
    val nip: String,
    @SerializedName("nik")
    val nik: String,
    @SerializedName("nama")
    val nama: String,
    @SerializedName("jenis_kelamin")
    val jenisKelamin: String,
    @SerializedName("tempat_lahir")
    val tempatLahir: String,
    @SerializedName("tanggal_lahir")
    val tanggalLahir: String,
    @SerializedName("agama")
    val agama: String,
    @SerializedName("pendidikan")
    val pendidikan: String,
    @SerializedName("jabatan")
    val jabatan: String,
    @SerializedName("departemen")
    val departemen: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("jenis_pegawai")
    val jenisPegawai: String,
    @SerializedName("telepon")
    val telepon: String,
    @SerializedName("tanggal_masuk")
    val tanggalMasuk: String
)
