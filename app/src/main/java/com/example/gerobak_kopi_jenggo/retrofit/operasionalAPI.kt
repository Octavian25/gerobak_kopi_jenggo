package com.example.gerobak_kopi_jenggo.retrofit

import com.example.gerobak_kopi_jenggo.models.operasional.OperasionalModel
import com.example.gerobak_kopi_jenggo.models.operasional.OperasionalResponse
import com.example.gerobak_kopi_jenggo.operasional.operasionalModel
import com.example.gerobak_kopi_jenggo.operasional.sumOperasionalModel
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface operasionalAPI {
    @POST("api/operasional/add")
    fun postOperasional(@Body request: OperasionalModel) : Call<OperasionalResponse>

    @GET("api/operasional/all")
    fun getOperasional() : Call<operasionalModel>

    @GET("api/operasional/pengeluaran/harian/{id_cabang}&{tanggal}")
    fun getSumCabang(@Path("id_cabang") id_cabang : String, @Path("tanggal") tanggal: String) : Call<sumOperasionalModel>


    @GET("api/operasional/pendapatan/harian/{id_cabang}&{tanggal}")
    fun getPendapatan(@Path("id_cabang") id_cabang : String, @Path("tanggal") tanggal: String) : Call<sumOperasionalModel>


    @GET("api/operasional/{id_cabang}&{tanggal}")
    fun getOperasionalCabang(@Path("id_cabang") id_cabang : String, @Path("tanggal") tanggal : String) : Call<operasionalModel>

}