package com.example.gerobak_kopi_jenggo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.models.menu.masterModel
import com.example.gerobak_kopi_jenggo.models.menu.masterAdapter
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.menuAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import io.easyprefs.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var rv_list_master_data : RecyclerView
class lihatDataMaster : AppCompatActivity(), View.OnClickListener {
    private lateinit var  masterAdapters: masterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_data_master)

        rv_list_master_data = findViewById(R.id.rv_list_master_data)
        rv_list_master_data.setHasFixedSize(true)
        setupRecyclerView()
        getData()

    }
    private fun setupRecyclerView(){
        masterAdapters = masterAdapter(arrayListOf(), object : masterAdapter.OnAdapterListener{
            override fun onClick(result: masterModel.Result) {
                Toast.makeText(this@lihatDataMaster, "Clicked", Toast.LENGTH_SHORT).show()
            }
        })
        rv_list_master_data.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = masterAdapters
        }
    }
    fun getData(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(menuAPI::class.java)
        retro.getMenu(helper().getPref("id_cabang")).enqueue(object : Callback<masterModel>{
            override fun onResponse(call: Call<masterModel>, response: Response<masterModel>) {
                Log.d(TAG, "onResponse: " + response.body().toString())
                if(response.isSuccessful){
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<masterModel>, t: Throwable) {
                Log.d(TAG, "onFailure: " + t.message.toString())
               showToast(this@lihatDataMaster, "Data Menu Kosong", "info")
            }

        })

    }
    private fun showResult(results: masterModel){
        for (result in results.result)
        masterAdapters.setData(results.result)
    }



    override fun onClick(v: View?) {
        when(v?.id){

        }
    }
}