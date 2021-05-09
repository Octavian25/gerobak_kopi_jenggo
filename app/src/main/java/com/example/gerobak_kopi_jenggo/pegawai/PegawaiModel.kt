package com.example.gerobak_kopi_jenggo.pegawai

data class PegawaiModel (
    var result : ArrayList<Result>){
    data class Result(
        var nama_pegawai : String = "",
        var id_cabang : String  ="",
        var gaji : String = "",
    )
}