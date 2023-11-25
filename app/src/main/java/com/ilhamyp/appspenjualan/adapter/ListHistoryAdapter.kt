package com.ilhamyp.appspenjualan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ilhamyp.appspenjualan.databinding.ItemRowDataBinding
import com.ilhamyp.appspenjualan.model.History

class ListHistoryAdapter() : PagedListAdapter<History,ListHistoryAdapter.ListViewHolder>(HistoryComparator()) {

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
        val history = getItem(position) as History
        holder.bind(history)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHistoryAdapter.ListViewHolder {
        val view = ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    class HistoryComparator : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem.id == newItem.id
        }
    }

}