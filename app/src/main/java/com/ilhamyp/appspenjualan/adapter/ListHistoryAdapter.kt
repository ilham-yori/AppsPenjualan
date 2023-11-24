package com.ilhamyp.appspenjualan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhamyp.appspenjualan.databinding.ItemRowDataBinding
import com.ilhamyp.appspenjualan.model.History
import com.ilhamyp.appspenjualan.model.Mobil

class ListHistoryAdapter() : RecyclerView.Adapter<ListHistoryAdapter.ListViewHolder>() {

    private val list = ArrayList<History>()

    inner class ListViewHolder(val binding : ItemRowDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            with(binding) {
                tvTahunKeluaran.text = history.tahunKeluaran
                tvMesin.text = history.mesin
                warna.text = "Tanggal Transaksi : "
                tvWarna.text = history.date
                stock.text = "Terjual : "
                tvStock.text = history.totalPenjualan
            }
        }
    }

    override fun onBindViewHolder(holder: ListHistoryAdapter.ListViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHistoryAdapter.ListViewHolder {
        val view = ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    override fun getItemCount() : Int  = list.size

    fun loadListUser(history: List<History>) {
        list.clear()
        list.addAll(history)
        notifyDataSetChanged()
    }
}