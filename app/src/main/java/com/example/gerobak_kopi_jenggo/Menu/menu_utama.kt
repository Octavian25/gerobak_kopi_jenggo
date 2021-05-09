package com.example.gerobak_kopi_jenggo.Menu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.*
import com.example.gerobak_kopi_jenggo.laporanPenjualan.laporanPenjualan
import com.example.gerobak_kopi_jenggo.operasional.sumOperasionalModel
import com.example.gerobak_kopi_jenggo.pegawai.Laporan_penggajian
import com.example.gerobak_kopi_jenggo.pegawai.tambahPegawai
import com.example.gerobak_kopi_jenggo.penjualan.Penjualan
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.operasionalAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import io.easyprefs.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class menu_utama : AppCompatActivity() {
    private lateinit var rv_menu : RecyclerView
    private lateinit var uang_keluar : TextView
    private lateinit var uang_masuk : TextView
    private lateinit var pendapatan_harian : TextView
    private lateinit var logout : Button
    private  var TAG :String = "Menu Utama"
    private var list: ArrayList<Menu> = arrayListOf()
    private var id_cabang : String = "";
    private var token : String = "";
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_utama)

        rv_menu = findViewById(R.id.rv_menu);
        rv_menu.setHasFixedSize(true)
        uang_keluar = findViewById(R.id.uang_keluar)
        uang_masuk = findViewById(R.id.uang_masuk)
        pendapatan_harian = findViewById(R.id.tv_pendapatan_harian)
        logout = findViewById(R.id.logout)
        var level = helper().getPref("level");
        when(level){
            "gudang" -> list.addAll(MenuGudang.listData)
            "owner" -> list.addAll(MenuOwner.listData)
            "cabang" -> list.addAll(MenuData.listData)
        }
        getSumOperasional()
        getSumPendapatan()
        rv_menu.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val gridMenu = MenuAdapter(list)
        rv_menu.adapter = gridMenu
        gridMenu.OnItemClickCallback(object : MenuAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Menu) {
                when(data.name){
                    "Biaya Operasional" -> {
                        val intent = Intent(this@menu_utama, Biaya_operasional::class.java)
                        startActivity(intent)
                    }
                    "Master Data" -> {
                        val intent = Intent(this@menu_utama, kelola_data_master::class.java)
                        startActivity(intent)
                    }
                    "Absen" -> {
                        val intent = Intent(this@menu_utama, Absensi::class.java)
                        startActivity(intent)
                    }
                    "Order Bahan" -> {
                        val intent = Intent(this@menu_utama, Bahan_Baku::class.java )
                        startActivity(intent)
                    }
                    "Penjualan" -> {
                        val intent = Intent(this@menu_utama, Penjualan::class.java);
                        startActivity(intent)
                    }
                    "Laporan" -> {
                        val intent = Intent(this@menu_utama, laporanPenjualan::class.java);
                        startActivity(intent)
                    }
                    "Order Pesanan" -> {
                        val intent = Intent(this@menu_utama, lihat_bahan_gudang::class.java)
                        startActivity(intent)
                    }
                    "Laporan Gudang" -> {
                        val intent = Intent(this@menu_utama, lihat_bahan_owner::class.java)
                        startActivity(intent)
                    }
                    "Laporan Penjualan" -> {
                        val intent = Intent(this@menu_utama, laporanPenjualan::class.java)
                        startActivity(intent)
                    }
                    "Laporan Penggajian" -> {
                        val intent = Intent(this@menu_utama, Laporan_penggajian::class.java)
                        startActivity(intent)
                    }
                    "Tambah Pegawai" -> {
                        val intent = Intent(this@menu_utama, tambahPegawai::class.java)
                        startActivity(intent)
                    } "Tambah Cabang" -> {
                        val intent = Intent(this@menu_utama, tambahCabang::class.java)
                        startActivity(intent)
                    }
                }
            }

        })

        logout.setOnClickListener {
            val intent = Intent(this@menu_utama, MainActivity::class.java);
            startActivity(intent);
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        getSumPendapatan()
        getSumOperasional()
    }

    fun getSumOperasional(){
        var retro = Retro().postRetroClient("https://riniocta.my.id/").create(operasionalAPI::class.java)
        retro.getSumCabang(helper().getPref("id_cabang"),  helper().getDate()).enqueue(object : Callback<sumOperasionalModel>{
            override fun onResponse(
                call: Call<sumOperasionalModel>,
                response: Response<sumOperasionalModel>
            ) {
                var data = response.body();
                uang_keluar.text = helper().rupiah(data?.harga.toString().toDouble())
            }

            override fun onFailure(call: Call<sumOperasionalModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }
    fun getSumPendapatan(){
        var retro = Retro().postRetroClient("https://riniocta.my.id/").create(operasionalAPI::class.java)
        retro.getPendapatan(helper().getPref("id_cabang"), helper().getDate()).enqueue(object : Callback<sumOperasionalModel>{
            override fun onResponse(
                call: Call<sumOperasionalModel>,
                response: Response<sumOperasionalModel>
            ) {
                var data = response.body();
                uang_masuk.text = helper().rupiah(data?.harga.toString().toDouble())
                pendapatan_harian.text = helper().rupiah(data?.harga.toString().toDouble())

            }

            override fun onFailure(call: Call<sumOperasionalModel>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler().postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }

}