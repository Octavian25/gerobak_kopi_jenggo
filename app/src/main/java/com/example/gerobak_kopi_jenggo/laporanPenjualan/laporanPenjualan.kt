package com.example.gerobak_kopi_jenggo.laporanPenjualan

import android.content.Intent
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.DatePickerFragment
import com.example.gerobak_kopi_jenggo.DatePickerFragmentUntil
import com.example.gerobak_kopi_jenggo.R
import com.example.gerobak_kopi_jenggo.bahan.bahanAdapter
import com.example.gerobak_kopi_jenggo.bahan.bahanModel
import com.example.gerobak_kopi_jenggo.models.penjualan.laporanModel
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.penjualanAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.*


class laporanPenjualan : AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener, DatePickerFragmentUntil.DialogDateListener {
    private val TAG = laporanPenjualan::class.java.simpleName
    private lateinit var rv_list_bahan : RecyclerView
    private lateinit var date_from : Button
    private lateinit var date_until : Button
    private lateinit var laporanPenjualanAdapters: laporanPenjualanAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_laporan_penjualan)
        rv_list_bahan = findViewById(R.id.rv_list_penjualan)
        rv_list_bahan.setHasFixedSize(true)
        date_from = findViewById(R.id.mulai_dari)
        date_until = findViewById(R.id.sampai_dengan)

        date_from.setOnClickListener(this)
        date_until.setOnClickListener(this)
        setupRecyclerView()
        getData(helper().getDate(), helper().getDate())
    }

    private fun setupRecyclerView(){
        laporanPenjualanAdapters = laporanPenjualanAdapter(arrayListOf())
        rv_list_bahan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = laporanPenjualanAdapters
        }
    }

    fun getData(mulai_dari : String, sampai_dengan : String){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(penjualanAPI::class.java)
        retro.getAll(mulai_dari, sampai_dengan).enqueue(object : Callback<laporanModel> {
            override fun onResponse(call: Call<laporanModel>, response: Response<laporanModel>) {
                if (response.isSuccessful){
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<laporanModel>, t: Throwable) {
                TODO("Not yet implemented")
            }
        })

    }

    private fun showResult(results: laporanModel){
        for (result in results.result)
            laporanPenjualanAdapters.setData(results.result)
    }

    override fun onClick(v: View?) {
                when(v?.id){
            R.id.mulai_dari -> {
                val datePickerFragment = DatePickerFragment()
                datePickerFragment.show(supportFragmentManager, "DatePicker" )
            }
            R.id.sampai_dengan -> {
                val datePickerFragmentUntil = DatePickerFragmentUntil()
                datePickerFragmentUntil.show(supportFragmentManager, "DatePicker")
            }
        }
    }
    override fun onDialogDateSet(tag: String?, year: Int, month: Int, dayOfMonth: Int) {

        // Siapkan date formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Set text dari textview once
        date_from.text = dateFormat.format(calendar.time)
    }

    override fun onDialogDateSetUntil(tag: String?, year: Int, month: Int, dayOfMonth: Int) {
        // Siapkan date formatter-nya terlebih dahulu
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMonth)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        // Set text dari textview once
        date_until.text = dateFormat.format(calendar.time)
        laporanPenjualanAdapters.clear()
        getData(date_from.text.toString(), date_until.text.toString())
    }
}