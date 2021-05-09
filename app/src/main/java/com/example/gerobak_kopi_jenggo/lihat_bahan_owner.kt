package com.example.gerobak_kopi_jenggo

import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.bahan.bahanAdapter
import com.example.gerobak_kopi_jenggo.bahan.bahanModel
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.bahanBakuAPI
import com.example.gerobak_kopi_jenggo.utility.helper
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class lihat_bahan_owner : AppCompatActivity(), View.OnClickListener, DatePickerFragment.DialogDateListener, DatePickerFragmentUntil.DialogDateListener {
    private lateinit var  bahanAdapters: bahanAdapter
    private lateinit var rv_list_bahan : RecyclerView
    private lateinit var date_from : Button
    private lateinit var date_until : Button
    private lateinit var textView : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lihat_bahan_owner)

        date_from = findViewById(R.id.mulai_dari)
        date_until = findViewById(R.id.sampai_dengan)
        textView = findViewById(R.id.textView2)
        rv_list_bahan = findViewById(R.id.rv_list_bahan)
        rv_list_bahan.setHasFixedSize(true)
        setupRecyclerView()
        getBahan(helper().getDate(), helper().getDate())

        date_from.setOnClickListener(this)
        date_until.setOnClickListener(this)

    }

    private fun setupRecyclerView(){
        bahanAdapters = bahanAdapter(arrayListOf())
        rv_list_bahan.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = bahanAdapters
        }
        bahanAdapters.setOnItemClickCallback(object : bahanAdapter.OnItemClickCallback {
            override fun onItemClicked(data: bahanModel.Result) {
                onClickWhatsApp(data.house_blend, data.choco, data.vanilla, data.single_origin, data.coffe_blend)
            }
        })
    }
    fun getBahan(mulai_dari: String, sampai_dengan: String){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(bahanBakuAPI::class.java)
        retro.getOwner(mulai_dari, sampai_dengan).enqueue(object : Callback<bahanModel> {
            override fun onResponse(
                call: Call<bahanModel>,
                response: Response<bahanModel>
            ) {
                if (response.isSuccessful) {
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<bahanModel>, t: Throwable) {
                showToast(this@lihat_bahan_owner, "Terjadi kesalahan Saat Mengambil data", "info")
            }

        })
    }

    private fun showResult(results: bahanModel){
        for (result in results.result)
            bahanAdapters.setData(results.result)
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
        bahanAdapters.clear()
        getBahan(date_from.text.toString(), date_until.text.toString())
    }

    private fun onClickWhatsApp(house_blend : String, choco : String,vanilla : String,  single_origin : String,coffe_blend:String ) {
        val pm = packageManager
        try {
            val waIntent = Intent(Intent.ACTION_SEND)
            waIntent.type = "text/plain"
            val text = """
                Geroba Kopi Jenggo : 
                List Pesanan Bahan Baku
                
                House Blend : $house_blend Pkg,
                Choco : $choco Pkg,
                Vanilla : $vanilla Pkg,
                Single Origin : $single_origin Pkg,
                Coffe Blend : $coffe_blend Pkg,
          
                
                Terima Kasih
            """.trimIndent()
            val info = pm.getPackageInfo("com.whatsapp", PackageManager.GET_META_DATA)
            //Check if package exists or not. If not then code
            //in catch block will be called
            waIntent.setPackage("com.whatsapp")
            waIntent.putExtra(Intent.EXTRA_TEXT, text)
            startActivity(Intent.createChooser(waIntent, "Share with"))
        } catch (e: PackageManager.NameNotFoundException) {
            Toast.makeText(this, "WhatsApp not Installed", Toast.LENGTH_SHORT)
                .show()
        }
    }
}