package com.example.gerobak_kopi_jenggo.models.absensi

data class absensiModel(
    val result: ArrayList<Result>
){
    data class Result(var tanggal_absensi : String = "",
                      var jam : String = "",
                      var nama_pegawai : String = "")
}