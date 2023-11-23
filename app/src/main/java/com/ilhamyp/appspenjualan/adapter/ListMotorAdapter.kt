package com.ilhamyp.appspenjualan.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ilhamyp.appspenjualan.databinding.ItemRowDataBinding
import com.ilhamyp.appspenjualan.model.Motor

class ListMotorAdapter () : RecyclerView.Adapter<ListMotorAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val list = ArrayList<Motor>()

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
        holder.bind(list[position])
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view = ItemRowDataBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder((view))
    }

    override fun getItemCount() : Int  = list.size

    interface OnItemClickCallback {
        fun onItemClicked(data: Motor)
    }

    fun loadListUser(users: List<Motor>) {
        list.clear()
        list.addAll(users)
        notifyDataSetChanged()
    }
}