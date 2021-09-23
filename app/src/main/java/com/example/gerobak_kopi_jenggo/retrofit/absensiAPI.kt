package com.example.gerobak_kopi_jenggo.retrofit

import com.example.gerobak_kopi_jenggo.models.absensi.absensiModel
import com.example.gerobak_kopi_jenggo.models.absensi.absensiResponse
import com.example.gerobak_kopi_jenggo.models.pegawai.pegawaiResponse
import com.example.gerobak_kopi_jenggo.pegawai.PegawaiModel
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface absensiAPI {
    @GET("api/get/absensi/today")
    fun getAbsensi() : Call<absensiModel>

    @GET("api/get/pegawai/bycabang/{id_cabang}")
    fun getPegawai(@Path("id_cabang") id_cabang : String) : Call<List<pegawaiResponse>>

    @POST("api/absensi/{id_pegawai}")
    fun postAbsensi(@Path("id_pegawai") id_pegawai : String) : Call<absensiResponse>

    @GET("api/get/pegawai")
    fun getPegawai() : Call<PegawaiModel>

    @DELETE("api/delete/pegawai/{id_pegawai}")
    fun deletePegawai(@Path("id_pegawai") id_pegawai: String) : Call<PegawaiModel>
}