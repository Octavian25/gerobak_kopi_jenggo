package com.example.gerobak_kopi_jenggo.retrofit

import com.example.gerobak_kopi_jenggo.models.barang.barangModel
import com.example.gerobak_kopi_jenggo.models.barang.barangResponse
import com.example.gerobak_kopi_jenggo.models.operasional.OperasionalModel
import com.example.gerobak_kopi_jenggo.models.operasional.OperasionalResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface barangAPI {
    @POST("api/barang/add")
    fun postBarang(@Body request: barangModel) : Call<barangResponse>
    @GET("api/barang/all")
    fun getBarang() : Call<List<barangResponse>>
}