package com.example.gerobak_kopi_jenggo.models.penjualan

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class penjualanModel {
    @SerializedName("id_barang")
    @Expose
    var id_barang : String ? = null

    @SerializedName("qty")
    @Expose
    var qty : String ? = null
}