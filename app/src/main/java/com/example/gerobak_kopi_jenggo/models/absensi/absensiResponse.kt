package com.example.gerobak_kopi_jenggo.models.absensi

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class absensiResponse {
    @SerializedName("nama_pegawai")
    @Expose
    var nama_pegawai : String ? = null

    @SerializedName("tanggal_absensi")
    @Expose
    var tanggal_absensi : String ? = null

    @SerializedName("jam")
    @Expose
    var jam : String ? = null

    @SerializedName("status")
    @Expose
    var status : String ? = null
}