package com.ilhamyp.appspenjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamyp.appspenjualan.adapter.ListMobilAdapater
import com.ilhamyp.appspenjualan.databinding.ActivityMobilBinding
import com.ilhamyp.appspenjualan.model.Mobil
import com.ilhamyp.appspenjualan.viewmodel.MobilViewModel

class MobilActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMobilBinding
    private lateinit var mobilViewModel: MobilViewModel
    private lateinit var adapter: ListMobilAdapater

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMobilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListMobilAdapater()
        binding?.rvMobil?.layoutManager = LinearLayoutManager(this)
        binding?.rvMobil?.setHasFixedSize(true)
        binding?.rvMobil?.adapter = adapter

        mobilViewModel = ObtainViewModel.getMobilViewModel(this@MobilActivity)
        mobilViewModel.getAllMobil().observe(this){ mobilList ->
            if (mobilList != null) {
                adapter.loadListUser(mobilList)
            }
        }

        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : ListMobilAdapater.OnItemClickCallback {
            override fun onItemClicked(data: Mobil) {
                val intent = Intent(this@MobilActivity, DetailDataActivity::class.java)
                intent.putExtra("detail_type","Mobil")
                intent.putExtra("data_kendaraan",data.id)
                startActivity(intent)
            }
        })

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddDataActivity::class.java)
            intent.putExtra("info_type", "Mobil")
            startActivity(intent)
            this.finish()
        }

        binding.topAppBar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}