package com.example.gerobak_kopi_jenggo.retrofit

import com.example.gerobak_kopi_jenggo.bahan.bahanModel
import com.example.gerobak_kopi_jenggo.models.bahan.BahanModel
import com.example.gerobak_kopi_jenggo.models.bahan.BahanResponse
import com.example.gerobak_kopi_jenggo.models.login.loginCabangResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface bahanBakuAPI {
    @POST("api/bahan/add")
    fun postBahan(@Body request: BahanModel) : Call<BahanResponse>

    @GET("api/bahan/update/{id_order}/{id_cabang}")
    fun updateOrder(@Path("id_order") id_order : String,@Path("id_cabang") id_cabang : String) : Call<bahanModel>

    @GET("api/bahan/pesanan/{id_cabang}")
    fun getBahan(@Path("id_cabang") id_cabang : String) : Call<bahanModel>

    @GET("api/bahan/pesanan/all/{mulai_dari}&{sampai_dengan}")
    fun getGudang(@Path("mulai_dari") mulai_dari : String,@Path("sampai_dengan") sampai_dengan : String) : Call<bahanModel>

    @GET("api/bahan/pesanan/owner/{mulai_dari}&{sampai_dengan}")
    fun getOwner(@Path("mulai_dari") mulai_dari : String,@Path("sampai_dengan") sampai_dengan : String) : Call<bahanModel>
}