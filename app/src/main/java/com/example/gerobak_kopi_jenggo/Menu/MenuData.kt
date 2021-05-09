package com.example.gerobak_kopi_jenggo.Menu

import com.example.gerobak_kopi_jenggo.R

object MenuData {
    private val nama = arrayOf(
        "Master Data",
        "Penjualan",
        "Order Bahan",
    "Absen",
        "Biaya Operasional"
    )
    private val gambar = arrayOf(
        R.drawable.kelola_data_master,
        R.drawable.transaksi,
        R.drawable.laporan_penjualan,
        R.drawable.pesan_bahanbaku,
        R.drawable.absen,
        R.drawable.biaya_operasional
    )

    val listData: ArrayList<Menu>
        get() {
            val list = arrayListOf<Menu>()
            for (index in nama.indices){
                val menu = Menu()
                menu.name = nama[index]
                menu.gambar = gambar[index]
                list.add(menu)
            }
            return list
        }
}