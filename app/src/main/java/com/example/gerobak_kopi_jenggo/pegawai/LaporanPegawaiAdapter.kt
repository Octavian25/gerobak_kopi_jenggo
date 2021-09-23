package com.example.gerobak_kopi_jenggo.pegawai

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.R

class LaporanPegawaiAdapter (var results: ArrayList<PegawaiModel.Result>) :
    RecyclerView.Adapter<LaporanPegawaiAdapter.bahanViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: LaporanPegawaiAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    var TOTAL_GAJI : Int = 0
    class bahanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nama_pegawai : TextView = view.findViewById(R.id.list_nama_pegawai_laporan)
        var lokasi : TextView = view.findViewById(R.id.list_lokasi)
        var btn_delete : ImageButton = view.findViewById(R.id.btn_delete)
        var btn_update : ImageButton = view.findViewById(R.id.btn_edit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bahanViewHolder = bahanViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_laporan_pegawai, parent, false))

    override fun onBindViewHolder(holder: bahanViewHolder, position: Int) {
        val result = results[position]
        holder.nama_pegawai.text = result.nama_pegawai
        holder.lokasi.text = result.id_cabang
        holder.btn_delete.setOnClickListener { onItemClickCallback.onItemClicked(results[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return results.size
    }
    fun setData(data: List<PegawaiModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        TOTAL_GAJI = data.sumBy { it.gaji.toInt() }
        notifyDataSetChanged()
    }
    fun clear(){
        val size = this.results.size
        this.results.clear()
//        notifyItemRangeRemoved(0, size)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: PegawaiModel.Result)
    }
}