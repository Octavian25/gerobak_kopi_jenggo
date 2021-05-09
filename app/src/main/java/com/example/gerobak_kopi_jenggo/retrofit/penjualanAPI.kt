package com.example.gerobak_kopi_jenggo.retrofit


import com.example.gerobak_kopi_jenggo.models.menu.masterModel
import com.example.gerobak_kopi_jenggo.models.menu.menuModel
import com.example.gerobak_kopi_jenggo.models.menu.menuResponse
import com.example.gerobak_kopi_jenggo.models.penjualan.*
import retrofit2.Call
import retrofit2.http.*

interface penjualanAPI {
    @GET("api/menu/get/cabangList/{id_cabang}")
    fun getMenu(@Path("id_cabang") id_cabang : String) : Call<List<listResponse>>


    @DELETE("/api/order/cart/{id}")
    fun deleteCart(@Path("id") id : String) : Call<cartResponse>

    @POST("api/order/add")
    fun postCart(@Body request : penjualanModel) : Call<cartResponse>

    @POST("api/order/order_pesanan")
    fun postHead(@Body request : headModel) : Call<cartResponse>

    @GET("api/order/cart")
    fun getCart() : Call<cartModel>

    @GET("api/order/owner/{mulai_dari}&{sampai_dengan}")
    fun getAll(@Path("mulai_dari") mulai_dari : String,@Path("sampai_dengan") sampai_dengan : String) : Call<laporanModel>
}