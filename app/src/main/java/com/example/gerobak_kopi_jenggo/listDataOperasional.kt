package com.example.gerobak_kopi_jenggo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.models.menu.masterAdapter
import com.example.gerobak_kopi_jenggo.models.menu.masterModel
import com.example.gerobak_kopi_jenggo.operasional.operasionalAdapter
import com.example.gerobak_kopi_jenggo.operasional.operasionalModel
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.menuAPI
import com.example.gerobak_kopi_jenggo.retrofit.operasionalAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import io.easyprefs.impl.PrefProvider.getPref
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var rv_list_operasional : RecyclerView
class listDataOperasional : AppCompatActivity() {
    private lateinit var  operasionalAdapters: operasionalAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_data_operasional)

        rv_list_operasional = findViewById(R.id.rv_list_operasional)
        rv_list_operasional.setHasFixedSize(true)
        setupRecyclerView()
        getOperasional()

    }
    private fun setupRecyclerView(){
        operasionalAdapters = operasionalAdapter(arrayListOf(), object : masterAdapter.OnAdapterListener{
            override fun onClick(result: masterModel.Result) {
                Toast.makeText(this@listDataOperasional, "Clicked", Toast.LENGTH_SHORT).show()
            }
        })
        rv_list_operasional.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = operasionalAdapters
        }
    }
    fun getOperasional(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(operasionalAPI::class.java)
        retro.getOperasionalCabang(helper().getPref("id_cabang"), helper().getDate()).enqueue(object : Callback<operasionalModel>{
            override fun onResponse(
                call: Call<operasionalModel>,
                response: Response<operasionalModel>
            ) {
                if (response.isSuccessful){
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<operasionalModel>, t: Throwable) {
                showToast(this@listDataOperasional, "Terjadi kesalahan Saat Mengambil data","info")
            }

        })
    }

    private fun showResult(results: operasionalModel){
        for (result in results.result)
            operasionalAdapters.setData(results.result)
    }
}