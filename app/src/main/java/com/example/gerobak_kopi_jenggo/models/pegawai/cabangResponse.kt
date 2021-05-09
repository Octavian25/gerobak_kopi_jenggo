package com.example.gerobak_kopi_jenggo.models.pegawai

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class cabangResponse {
    @SerializedName("id_cabang")
    @Expose
    var id_cabang : String ? = null

    @SerializedName("nama_cabang")
    @Expose
    var nama_cabang : String ? = null
}