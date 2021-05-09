package com.example.gerobak_kopi_jenggo.models.penjualan

data class cartModel(
    val result: ArrayList<Result>
){
    data class Result(var nama_barang : String = "",
                      var total : String = "",
                      var id : String = "",
                      var qty : String = "")
}