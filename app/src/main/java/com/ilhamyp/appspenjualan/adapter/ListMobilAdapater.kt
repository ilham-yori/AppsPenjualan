package com.ilhamyp.appspenjualan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhamyp.appspenjualan.databinding.ItemRowDataBinding
import com.ilhamyp.appspenjualan.model.Mobil
import com.ilhamyp.appspenjualan.model.Motor

class ListMobilAdapater() : RecyclerView.Adapter<ListMobilAdapater.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val list = ArrayList<Mobil>()

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
        holder.bind(list[position])
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListMobilAdapater.ListViewHolder {
        val view = ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    override fun getItemCount() : Int  = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Mobil)
    }

    fun loadListUser(mobil: List<Mobil>) {
        list.clear()
        list.addAll(mobil)
        notifyDataSetChanged()
    }
}