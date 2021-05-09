package com.example.gerobak_kopi_jenggo.models.penjualan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class headModel {
    @SerializedName("tanggal")
    @Expose
    var tanggal : String ? = null

    @SerializedName("id_cabang")
    @Expose
    var id_cabang : String ? = null

    @SerializedName("nama_customer")
    @Expose
    var nama_customer : String ? = null
}