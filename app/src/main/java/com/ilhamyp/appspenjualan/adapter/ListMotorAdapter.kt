package com.ilhamyp.appspenjualan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.ilhamyp.appspenjualan.databinding.ItemRowDataBinding
import com.ilhamyp.appspenjualan.model.Motor

class ListMotorAdapter () : PagedListAdapter<Motor, ListMotorAdapter.ListViewHolder>(MotorComparator()) {

    private lateinit var onItemClickCallback: OnItemClickCallback

    inner class ListViewHolder(val binding : ItemRowDataBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(motor : Motor) {
            with(binding) {
                tvTahunKeluaran.text = motor.tahunKeluaran
                tvMesin.text = motor.mesin
                tvWarna.text = motor.warna
                tvStock.text = motor.stock
            }

            binding.root.setOnClickListener{
                onItemClickCallback.onItemClicked(motor)
            }
        }
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val motor = getItem(position) as Motor
        holder.bind(motor)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Motor)
    }

    class MotorComparator : DiffUtil.ItemCallback<Motor>() {
        override fun areItemsTheSame(oldItem: Motor, newItem: Motor): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Motor, newItem: Motor): Boolean {
            return oldItem.id == newItem.id
        }
    }
}