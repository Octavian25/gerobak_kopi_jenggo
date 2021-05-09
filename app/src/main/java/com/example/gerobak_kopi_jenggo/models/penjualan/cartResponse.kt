package com.example.gerobak_kopi_jenggo.models.penjualan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class cartResponse {
    @SerializedName("nama_barang")
    @Expose
    var nama_barang : String ? = null

    @SerializedName("qty")
    @Expose
    var qty : String ? = null

    @SerializedName("total")
    @Expose
    var total : String ? = null
}