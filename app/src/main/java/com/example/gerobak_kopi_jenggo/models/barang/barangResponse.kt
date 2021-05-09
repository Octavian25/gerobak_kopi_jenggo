package com.example.gerobak_kopi_jenggo.models.barang

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class barangResponse {
    @SerializedName("id_barang")
    @Expose
    var id_barang : String ? = null

    @SerializedName("nama_barang")
    @Expose
    var nama_barang : String ? = null

    @SerializedName("qty")
    @Expose
    var qty : String ? = null

    @SerializedName("harga")
    @Expose
    var harga : String ? = null
}