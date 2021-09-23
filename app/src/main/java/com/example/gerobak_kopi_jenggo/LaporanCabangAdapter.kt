package com.example.gerobak_kopi_jenggo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.models.cabang.cabangModel

class LaporanCabangAdapter (var results: ArrayList<CabangModels.Result>) :
    RecyclerView.Adapter<LaporanCabangAdapter.cabangViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: LaporanCabangAdapter.OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    class cabangViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nama_cabang : TextView = view.findViewById(R.id.list_nama_cabang)
        var btn_delete : ImageButton = view.findViewById(R.id.btn_delete_cabang)
        var btn_update : ImageButton = view.findViewById(R.id.btn_edit_cabang)

    }

    override fun onBindViewHolder(holder: cabangViewHolder, position: Int) {
        val result = results[position]
        holder.nama_cabang.text = result.nama_cabang
        holder.btn_delete.setOnClickListener { onItemClickCallback.onItemClicked(results[holder.adapterPosition]) }
    }

    override fun getItemCount(): Int {
        return results.size
    }
    fun setData(data: List<CabangModels.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }
    fun clear(){
        val size = this.results.size
        this.results.clear()
//        notifyItemRangeRemoved(0, size)
        notifyDataSetChanged()
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: CabangModels.Result)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): cabangViewHolder = ca
}