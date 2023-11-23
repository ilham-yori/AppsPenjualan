package com.ilhamyp.appspenjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ilhamyp.appspenjualan.adapter.ListMotorAdapter
import com.ilhamyp.appspenjualan.databinding.ActivityMotorBinding
import com.ilhamyp.appspenjualan.model.Motor
import com.ilhamyp.appspenjualan.viewmodel.MotorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MotorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMotorBinding
    private lateinit var motorViewModel: MotorViewModel
    private lateinit var adapter: ListMotorAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = ListMotorAdapter()
        binding?.rvMotor?.layoutManager = LinearLayoutManager(this)
        binding?.rvMotor?.setHasFixedSize(true)
        binding?.rvMotor?.adapter = adapter

        motorViewModel = obtainMaiModel(this@MotorActivity)
        motorViewModel.getAllMotor().observe(this){ motorList ->
            if (motorList != null) {
                adapter.loadListUser(motorList)
            }
        }


        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : ListMotorAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Motor) {
                val intent = Intent(this@MotorActivity, DetailDataActivity::class.java)
                intent.putExtra("detail_type","Motor")
                intent.putExtra("data_kendaraan",data.id)
                startActivity(intent)
            }
        })

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddDataActivity::class.java)
            intent.putExtra("info_type", "Motor")
            startActivity(intent)
            this.finish()
        }

        binding.topAppBar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    private fun obtainMaiModel(activity: AppCompatActivity): MotorViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MotorViewModel::class.java]
    }


}