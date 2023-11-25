package com.ilhamyp.appspenjualan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ilhamyp.appspenjualan.databinding.ItemRowDataBinding
import com.ilhamyp.appspenjualan.model.Mobil

class ListMobilAdapater() : PagedListAdapter<Mobil, ListMobilAdapater.ListViewHolder>(MobilComparator()) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class ListViewHolder(val binding : ItemRowDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(mobil : Mobil) {
            with(binding) {
                tvTahunKeluaran.text = mobil.tahunKeluaran
                tvMesin.text = mobil.mesin
                tvWarna.text = mobil.warna
                tvStock.text = mobil.stock
            }

            binding.root.setOnClickListener{
                onItemClickCallback.onItemClicked(mobil)
            }
        }
    }

    override fun onBindViewHolder(holder: ListMobilAdapater.ListViewHolder, position: Int) {
        val mobil = getItem(position) as Mobil
        holder.bind(mobil)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMobilAdapater.ListViewHolder {
        val view = ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Mobil)
    }

    class MobilComparator : DiffUtil.ItemCallback<Mobil>() {
        override fun areItemsTheSame(oldItem: Mobil, newItem: Mobil): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Mobil, newItem: Mobil): Boolean {
            return oldItem.id == newItem.id
        }
    }

}