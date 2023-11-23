package com.ilhamyp.appspenjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.lifecycle.ViewModelProvider
import com.ilhamyp.appspenjualan.databinding.ActivityAddMotorBinding
import com.ilhamyp.appspenjualan.databinding.ActivityMainBinding
import com.ilhamyp.appspenjualan.model.Motor
import com.ilhamyp.appspenjualan.viewmodel.MotorViewModel


class AddMotorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMotorBinding
    private lateinit var motorAddViewModel: MotorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMotorBinding.inflate(layoutInflater)
        setContentView(binding.root)
        motorAddViewModel = obtainViewModel(this@AddMotorActivity)

        binding.addDataMotorButton.setOnClickListener {
            val tahun = binding.tahunKendaranEditText.text.toString()
            val warna = binding.warnaEditText.text.toString()
            val harga = binding.hargaEditText.text.toString()
            val stock = binding.stockEditText.text.toString()
            val mesin = binding.mesinEditText.text.toString()
            val suspensi = binding.suspensiEditText.text.toString()
            val transmisi = binding.transmisiEditText.text.toString()

            motorAddViewModel.insert(Motor(null,tahun,warna,harga,stock,mesin,suspensi,transmisi))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.topAppBar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }

    private fun obtainViewModel(activity: AppCompatActivity): MotorViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MotorViewModel::class.java]
    }
}