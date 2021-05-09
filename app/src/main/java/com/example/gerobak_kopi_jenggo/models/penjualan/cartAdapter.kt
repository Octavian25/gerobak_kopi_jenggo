package com.example.gerobak_kopi_jenggo.models.penjualan

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.gerobak_kopi_jenggo.R
import com.example.gerobak_kopi_jenggo.retrofit.Retro
import com.example.gerobak_kopi_jenggo.retrofit.penjualanAPI
import com.example.gerobak_kopi_jenggo.utility.showToast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private val TAG = cartAdapter::class.java.simpleName
class cartAdapter(var results : ArrayList<cartModel.Result>, val listener: OnAdapterListener) :
    RecyclerView.Adapter<cartAdapter.masterViewHolder>() {

    class masterViewHolder (val view : View) : RecyclerView.ViewHolder(view) {
        var nama_menu : TextView = view.findViewById(R.id.list_nama);
        var qty : TextView = view.findViewById(R.id.list_qty)
        var delete : ImageView = view.findViewById(R.id.list_delete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = masterViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.list_cart, parent, false))

    override fun onBindViewHolder(holder: masterViewHolder, position: Int) {
        val result = results[position]
        Log.d(TAG, "onBindViewHolder: " + result.nama_barang)
        holder.nama_menu.text = result.nama_barang
        holder.qty.text = result.qty
        holder.delete.setOnClickListener {
            postCart(result.id)
        }
    }

    override fun getItemCount(): Int {
        return results.size
    }
    fun setData(data: List<cartModel.Result>){
        this.results.clear()
        this.results.addAll(data)
        notifyDataSetChanged()
    }
    interface OnAdapterListener {
        fun onClick(result: cartModel.Result)
    }

    private fun postCart(id:String){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(penjualanAPI::class.java)
        retro.deleteCart(id).enqueue(object : Callback<cartResponse> {
            override fun onResponse(call: Call<cartResponse>, response: Response<cartResponse>) {
                getData()
            }

            override fun onFailure(call: Call<cartResponse>, t: Throwable) {

            }

        })
    }
    fun getData(){
        val retro = Retro().postRetroClient("https://riniocta.my.id/").create(penjualanAPI::class.java)
        retro.getCart().enqueue(object : Callback<cartModel>{
            override fun onResponse(call: Call<cartModel>, response: Response<cartModel>) {
                Log.d(com.example.gerobak_kopi_jenggo.TAG, "onResponse: " + response.body().toString())
                results.clear()
                notifyDataSetChanged()
                if(response.isSuccessful){
                    showResult(response.body()!!)
                }
            }

            override fun onFailure(call: Call<cartModel>, t: Throwable) {
                Log.d(com.example.gerobak_kopi_jenggo.TAG, "onFailure: " + t.message.toString())
            }

        })

    }
    private fun showResult(results: cartModel){
        for (result in results.result)
           setData(results.result)
    }

    fun reloadRecycler(){
            this.results.clear()
            notifyDataSetChanged()
    }
}