package com.example.gerobak_kopi_jenggo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import com.example.gerobak_kopi_jenggo.models.barang.barangResponse
import com.example.gerobak_kopi_jenggo.models.menu.menuModel
import com.example.gerobak_kopi_jenggo.models.menu.menuResponse
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.barangAPI
import com.example.gerobak_kopi_jenggo.retrofit.menuAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import com.prathameshmore.toastylibrary.Toasty
import io.easyprefs.Prefs
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var nama_menu : EditText
private lateinit var harga_menu : EditText
private lateinit var btn_master : Button
private lateinit var id_barang : Spinner
private lateinit var tv_detail : TextView
private var selectedRadio : Int = 0
private lateinit var radioGroup : RadioGroup
private lateinit var selectedRadioButton: RadioButton
private lateinit var cv_lihat_menu: CardView
class kelola_data_master : AppCompatActivity(), View.OnClickListener {
    private var jenis : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kelola_data_master)

        nama_menu = findViewById(R.id.et_nama_menu)
        harga_menu = findViewById(R.id.et_harga_menu)
        btn_master = findViewById(R.id.btn_data_master)
        id_barang = findViewById(R.id.spinner_id_barang)
        tv_detail= findViewById(R.id.detail_data_master)
        radioGroup = findViewById(R.id.rg_jenis_menu)
        cv_lihat_menu = findViewById(R.id.cv_lihat_menu)
        tv_detail.text = helper().convertDate()
        btn_master.setOnClickListener(this)
        var tanggal : String = helper().getDate().toString()
        var id_cabang : String = Prefs.read().content("id_cabang", "KOSONG")
        getBarang()
        tv_detail.setText("$tanggal ( $id_cabang )")
        radioGroup.setOnCheckedChangeListener { group, checkedId ->
            val radio: RadioButton = findViewById(checkedId)
            jenis = radio.text.toString()
        }
        cv_lihat_menu.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_data_master -> {
                if (validate()) postMenu()  else null
            }
            R.id.cv_lihat_menu -> {
                val intent = Intent(this@kelola_data_master, lihatDataMaster::class.java)
                startActivity(intent)
            }
        }
    }

    fun validate() : Boolean{
        if (nama_menu.text.isEmpty()){
            showToast(this@kelola_data_master, "Mohon isi Nama Menu", "error")
            return  false
        } else if (harga_menu.text.isEmpty()){
            showToast(this@kelola_data_master, "Mohon isi Harga Menu", "error")
            return false
        } else {
            return true
        }
    }

    fun getBarang(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(barangAPI::class.java)
        retro.getBarang().enqueue(object : Callback<List<barangResponse>>{
            override fun onResponse(
                call: Call<List<barangResponse>>,
                response: Response<List<barangResponse>>
            ) {
                var data : MutableList<String> = ArrayList()
                val id = response.body()
                for(id_barang in id!!){
                    var namanya = id_barang.nama_barang.toString()
                    var idnya = id_barang.id_barang.toString()
                    data.add("$idnya - $namanya")
                }
                id_barang.adapter = ArrayAdapter<String>(this@kelola_data_master, R.layout.support_simple_spinner_dropdown_item, data)
            }

            override fun onFailure(call: Call<List<barangResponse>>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })
    }
    fun postMenu(){
        Log.d(TAG, "postMenu: " + Prefs.read().content("id_cabang", "KOSONG"))
        val menuReq  = menuModel()
        menuReq.id_barang = id_barang.selectedItem.toString().split("-")[0]
        menuReq.harga_barang = harga_menu.text.toString().toInt()
        menuReq.id_cabang = Prefs.read().content("id_cabang", "KOSONG");
        menuReq.nama_barang = nama_menu.text.toString()
        menuReq.jenis = jenis
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(menuAPI::class.java)
        retro.postMenu(menuReq).enqueue(object : Callback<menuResponse>{
            override fun onResponse(call: Call<menuResponse>, response: Response<menuResponse>) {
                showToast(this@kelola_data_master, "Berhasil Menambahkan Data", "success")
                resetField()

            }

            override fun onFailure(call: Call<menuResponse>, t: Throwable) {
                showToast(this@kelola_data_master, "Gagal menambahkan Data", "error")
                TODO("Not yet implemented")
            }

        })
    }

    fun resetField(){
        nama_menu.setText("");
        harga_menu.setText("")
    }

}