package com.example.gerobak_kopi_jenggo.pegawai

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.R

class PegawaiAdapter (var results: ArrayList<PegawaiModel.Result>) :
    RecyclerView.Adapter<PegawaiAdapter.bahanViewHolder>() {
    private var onItemClickCallback: PegawaiAdapter.OnItemClickCallback? = null
    var TOTAL_GAJI : Int = 0
    class bahanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nama_pegawai : TextView = view.findViewById(R.id.list_nama_pegawai_laporan)
        var lokasi : TextView = view.findViewById(R.id.list_lokasi)
        var gaji : TextView = view.findViewById(R.id.list_gaji)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): bahanViewHolder = bahanViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_pegawai, parent, false))

    override fun onBindViewHolder(holder: bahanViewHolder, position: Int) {
        val result = results[position]
        holder.nama_pegawai.text = result.nama_pegawai
        holder.lokasi.text = result.id_cabang
        holder.gaji.text = result.gaji

        holder.itemView.setOnClickListener { onItemClickCallback?.onItemClicked(result) }
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


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: PegawaiModel.Result)
    }
}