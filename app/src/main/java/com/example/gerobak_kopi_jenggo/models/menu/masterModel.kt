package com.example.gerobak_kopi_jenggo.models.menu

data class masterModel(
    val result: ArrayList<Result>
){
    data class Result(var nama_barang : String = "",
                      var harga_barang : String = "",
                      var jenis : String = "")
}
