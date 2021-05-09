package com.example.gerobak_kopi_jenggo.models.pegawai

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class pegawaiResponse {
    @SerializedName("nama_pegawai")
    @Expose
    var nama_pegawai : String ? = null

    @SerializedName("id_pegawai")
    @Expose
    var id_pegawai : String ? = null

    @SerializedName("alamat")
    @Expose
    var alamat : String ? = null

    @SerializedName("jenis_kelamin")
    @Expose
    var jenis_kelamin : String ? = null

    @SerializedName("gaji")
    @Expose
    var gaji : String ? = null

    @SerializedName("no_telp")
    @Expose
    var no_telp : String ? = null

    @SerializedName("id_cabang")
    @Expose
    var id_cabang : String ? = null
}