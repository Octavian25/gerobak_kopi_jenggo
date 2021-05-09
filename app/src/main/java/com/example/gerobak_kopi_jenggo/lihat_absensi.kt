package com.example.gerobak_kopi_jenggo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.models.absensi.absensiAdapter
import com.example.gerobak_kopi_jenggo.models.absensi.absensiModel
import com.example.gerobak_kopi_jenggo.models.menu.masterAdapter
import com.example.gerobak_kopi_jenggo.models.menu.masterModel
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.absensiAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var rv_list_absensi : RecyclerView
private lateinit var tv_tanggal : TextView
class lihat_absensi : AppCompatActivity() {
    private lateinit var  absensiAdapters: absensiAdapter;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_absensi)

        rv_list_absensi = findViewById(R.id.rv_list_absensi);
        rv_list_absensi.setHasFixedSize(true);
        getData()
        setupRecyclerView()
//        val tanggal = helper().getDate()
//        tv_tanggal = findViewById(R.id.detail_data_lihat_absensi)
//        tv_tanggal.text = "$tanggal"
    }
    private fun setupRecyclerView(){
        absensiAdapters = absensiAdapter(arrayListOf(), object : absensiAdapter.OnAdapterListener{
            override fun onClick(result: absensiModel.Result) {
                showToast(this@lihat_absensi, "Hello There", "info")
            }
        })
        rv_list_absensi.apply {
            layoutManager = LinearLayoutManager(this@lihat_absensi);
            adapter = absensiAdapters
        }
    }
    fun getData(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(absensiAPI::class.java)
        retro.getAbsensi().enqueue(object : Callback<absensiModel> {
            override fun onResponse(call: Call<absensiModel>, response: Response<absensiModel>) {
                Log.d(TAG, "onResponse: " + response.body().toString())
                if(response.isSuccessful){
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<absensiModel>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message.toString())
                showToast(this@lihat_absensi, "Data Absensi Kosong", "info")
            }

        })

    }
   private fun showResult(results: absensiModel){
       for (result in results.result)
           absensiAdapters.setData(results.result)
   }
}