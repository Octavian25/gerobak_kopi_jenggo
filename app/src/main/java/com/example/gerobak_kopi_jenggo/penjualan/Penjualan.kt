package com.example.gerobak_kopi_jenggo.penjualan

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.R
import com.example.gerobak_kopi_jenggo.models.barang.barangResponse
import com.example.gerobak_kopi_jenggo.models.menu.masterAdapter
import com.example.gerobak_kopi_jenggo.models.menu.masterModel
import com.example.gerobak_kopi_jenggo.models.menu.menuModel
import com.example.gerobak_kopi_jenggo.models.menu.menuResponse
import com.example.gerobak_kopi_jenggo.models.penjualan.*
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.barangAPI
import com.example.gerobak_kopi_jenggo.retrofit.menuAPI
import com.example.gerobak_kopi_jenggo.retrofit.penjualanAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import io.easyprefs.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Penjualan : AppCompatActivity(), View.OnClickListener {
    private lateinit var id_barang : Spinner
    private lateinit var qty : EditText
    private lateinit var tambah: Button
    private lateinit var simpan: Button
    private lateinit var customer : EditText
    private lateinit var  cartAdapters: cartAdapter
    private lateinit var rv_list_master_data : RecyclerView
    private val TAG = Penjualan::class.java.simpleName
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_penjualan)
        id_barang = findViewById(R.id.menu_order);
        qty = findViewById(R.id.menu_qty)
        tambah = findViewById(R.id.menu_tambah_cart)
        simpan = findViewById(R.id.menu_simpan_order)
        rv_list_master_data = findViewById(R.id.rv_list_cart)
        customer = findViewById(R.id.nama_customer)
        rv_list_master_data.setHasFixedSize(true)
        setupRecyclerView()
        getData()
        getBarang()

        tambah.setOnClickListener(this)
        simpan.setOnClickListener(this)
    }


    private fun getBarang(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(penjualanAPI::class.java)
        retro.getMenu(helper().getPref("id_cabang")).enqueue(object : Callback<List<listResponse>> {
            override fun onResponse(
                call: Call<List<listResponse>>,
                response: Response<List<listResponse>>
            ) {
                var data : MutableList<String> = ArrayList()
                val id = response.body()
                for(id_barang in id!!){
                    var namanya = id_barang.nama_barang.toString()
                    var id_menu = id_barang.id_menu.toString()
                    data.add("$id_menu - $namanya")
                }
                id_barang.adapter = ArrayAdapter<String>(this@Penjualan, R.layout.support_simple_spinner_dropdown_item, data)
            }

            override fun onFailure(call: Call<List<listResponse>>, t: Throwable) {
                Toast.makeText(this@Penjualan, "GAGAL AMBIL DATA", Toast.LENGTH_LONG).show()
            }
        })
    }

    fun postCart(){
        val menuReq  = penjualanModel()
        menuReq.id_barang = id_barang.selectedItem.toString().split("-")[0]
        menuReq.qty = qty.text.toString();
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(penjualanAPI::class.java)
        retro.postCart(menuReq).enqueue(object : Callback<cartResponse>{
            override fun onResponse(call: Call<cartResponse>, response: Response<cartResponse>) {
                showToast(this@Penjualan, "Berhasil Menambahkan Data", "success")
                getData()
            }

            override fun onFailure(call: Call<cartResponse>, t: Throwable) {
                showToast(this@Penjualan, "Gagal menambahkan Data", "error")
                TODO("Not yet implemented")
            }

        })
    }

    fun postHead(){
        val menuReq  = headModel()
        menuReq.tanggal = helper().getDate()
        menuReq.id_cabang = helper().getPref("id_cabang")
        menuReq.nama_customer = customer.text.toString()
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(penjualanAPI::class.java)
        retro.postHead(menuReq).enqueue(object : Callback<cartResponse>{
            override fun onResponse(call: Call<cartResponse>, response: Response<cartResponse>) {
                showToast(this@Penjualan, "Berhasil Menambahkan Data", "success")
                cartAdapters.reloadRecycler()
                qty.setText("")
                customer.setText("")
                getData()
            }

            override fun onFailure(call: Call<cartResponse>, t: Throwable) {
                showToast(this@Penjualan, "Gagal menambahkan Data", "error")
                TODO("Not yet implemented")
            }

        })
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.menu_tambah_cart -> {
                postCart()
            }
            R.id.menu_simpan_order -> {
                postHead()
            }
        }
    }

    private fun setupRecyclerView(){
        cartAdapters = cartAdapter(arrayListOf(), object : cartAdapter.OnAdapterListener{
            override fun onClick(result: cartModel.Result) {
                Log.d(TAG, "onClick: " + result.id)
            }
        })
        rv_list_master_data.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = cartAdapters
        }
    }
    fun getData(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(penjualanAPI::class.java)
        retro.getCart().enqueue(object : Callback<cartModel>{
            override fun onResponse(call: Call<cartModel>, response: Response<cartModel>) {
                Log.d(com.example.gerobak_kopi_jenggo.TAG, "onResponse: " + response.body().toString())
                if(response.isSuccessful){
                    showResult(response.body()!!)

                }
            }

            override fun onFailure(call: Call<cartModel>, t: Throwable) {
                Log.d(com.example.gerobak_kopi_jenggo.TAG, "onFailure: " + t.message.toString())
                showToast(this@Penjualan, "Data Menu Kosong", "info")
            }

        })

    }
    private fun showResult(results: cartModel){
        for (result in results.result)
            cartAdapters.setData(results.result)
    }
}