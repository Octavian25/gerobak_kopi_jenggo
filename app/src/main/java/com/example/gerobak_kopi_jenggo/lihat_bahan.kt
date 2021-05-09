package com.example.gerobak_kopi_jenggo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.bahan.bahanAdapter
import com.example.gerobak_kopi_jenggo.bahan.bahanModel
import com.example.gerobak_kopi_jenggo.models.menu.masterAdapter
import com.example.gerobak_kopi_jenggo.models.menu.masterModel
import com.example.gerobak_kopi_jenggo.operasional.operasionalAdapter
import com.example.gerobak_kopi_jenggo.operasional.operasionalModel
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.bahanBakuAPI
import com.example.gerobak_kopi_jenggo.retrofit.operasionalAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var rv_list_bahan : RecyclerView
class lihat_bahan : AppCompatActivity() {
    private lateinit var  bahanAdapters: bahanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_bahan)

        rv_list_bahan = findViewById(R.id.rv_list_bahan)
        rv_list_bahan.setHasFixedSize(true)
        setupRecyclerView()
        getBahan()
    }

    private fun setupRecyclerView(){
        bahanAdapters = bahanAdapter(arrayListOf())
        rv_list_bahan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bahanAdapters
        }
    }
    fun getBahan(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(bahanBakuAPI::class.java)
        retro.getBahan(helper().getPref("id_cabang")).enqueue(object : Callback<bahanModel> {
            override fun onResponse(
                call: Call<bahanModel>,
                response: Response<bahanModel>
            ) {
                if (response.isSuccessful){
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<bahanModel>, t: Throwable) {
                showToast(this@lihat_bahan, "Terjadi kesalahan Saat Mengambil data","info")
            }

        })
    }

    private fun showResult(results: bahanModel){
        for (result in results.result)
            bahanAdapters.setData(results.result)
    }
}