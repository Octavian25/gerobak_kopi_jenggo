package com.example.gerobak_kopi_jenggo.laporanPenjualan

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.R
import com.example.gerobak_kopi_jenggo.bahan.bahanAdapter
import com.example.gerobak_kopi_jenggo.bahan.bahanModel
import com.example.gerobak_kopi_jenggo.models.penjualan.laporanModel

class laporanPenjualanAdapter(var results: ArrayList<laporanModel.Result>) : RecyclerView.Adapter<laporanPenjualanAdapter.laporanPenjualanViewHolder>() {
    private var onItemClickCallback: laporanPenjualanAdapter.OnItemClickCallback? = null
    class laporanPenjualanViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var nama_barang : TextView = view.findViewById(R.id.lp_nama_barang)
        var harga_barang : TextView = view.findViewById(R.id.lp_harga_barang)
        var id_cabang : TextView = view.findViewById(R.id.lp_id_cabang)
        var qty : TextView = view.findViewById(R.id.lp_qty)
        var total : TextView = view.findViewById(R.id.lp_total)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): laporanPenjualanAdapter.laporanPenjualanViewHolder =
        laporanPenjualanAdapter.laporanPenjualanViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.list_penjualan, parent, false)
        )

    override fun onBindViewHolder(holder: laporanPenjualanAdapter.laporanPenjualanViewHolder, position: Int) {
        val result = results[position]
        holder.nama_barang.text = result.nama_barang
        holder.harga_barang.text = result.harga_barang
        holder.id_cabang.text = result.id_cabang
        holder.qty.text = result.qty
        holder.total.text = result.total
        holder.itemView.setOnClickListener { onItemClickCallback?.onItemClicked(result) }
    }

    override fun getItemCount(): Int {
        return results.size
    }
    fun setData(data: List<laporanModel.Result>){
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


    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }
    interface OnItemClickCallback {
        fun onItemClicked(data: laporanModel.Result)
    }
    
}