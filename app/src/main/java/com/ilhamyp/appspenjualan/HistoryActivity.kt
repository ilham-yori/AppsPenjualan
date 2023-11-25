package com.ilhamyp.appspenjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamyp.appspenjualan.adapter.ListHistoryAdapter
import com.ilhamyp.appspenjualan.databinding.ActivityHistoryBinding
import com.ilhamyp.appspenjualan.viewmodel.HistoryViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HistoryActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistoryBinding
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var adapter: ListHistoryAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        historyViewModel = ObtainViewModel.getHistoryViewModel(this@HistoryActivity)

        adapter = ListHistoryAdapter()
        binding?.rvHistory?.layoutManager = LinearLayoutManager(this)
        binding?.rvHistory?.adapter = adapter

        historyViewModel.getAllHistory().observe(this){ historyList ->
            if (historyList != null) {
                adapter.submitList(historyList)
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            val totalMotor = historyViewModel.countPenjualan("Motor")
            val totalMobil = historyViewModel.countPenjualan("Mobil")
            withContext(Dispatchers.Main) {
                binding.tvTotalMotor.text = totalMotor
                binding.tvTotalMobil.text = totalMobil
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }
}