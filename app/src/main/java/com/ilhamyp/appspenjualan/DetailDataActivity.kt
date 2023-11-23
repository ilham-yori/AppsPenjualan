package com.ilhamyp.appspenjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.ilhamyp.appspenjualan.databinding.ActivityDetailDataBinding
import com.ilhamyp.appspenjualan.model.Motor
import com.ilhamyp.appspenjualan.viewmodel.MotorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDataBinding
    private lateinit var motorViewModel: MotorViewModel
    private lateinit var motor: Motor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val typeIntent = intent.getStringExtra("detail_type")
        val idIntent = intent.getIntExtra("data_kendaraan",0)
        motorViewModel = obtainMaiModel(this@DetailDataActivity)

        if(typeIntent == "Motor"){
            CoroutineScope(Dispatchers.IO).launch {
                val check = motorViewModel.getSpecificMotor(idIntent)
                withContext(Dispatchers.Main) {
                    if (check == null) {
                        Log.d("Error Data", "Data Not Found")
                    } else {
                        motor = check
                        binding.tvDetailTahunKeluaran.setText(check.tahunKeluaran)
                        binding.tvDetailWarna.setText(check.warna)
                        binding.tvDetailHarga.setText(check.harga)
                        binding.tvDetailStock.setText(check.stock)
                        binding.tvDetailMesin.setText(check.mesin)
                        binding.tvDetailFirstData.setText(check.tipeSuspensi)
                        binding.tvDetailSecondData.setText(check.tipeTransmisi)
                        binding.btnJual.setText("Jual Motor")
                    }
                }
            }

            binding.btnJual.setOnClickListener {
                val inputEditTextField = EditText(this)
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Masukkan Jumlah Motor Yang Terjual")
                    .setMessage("Total :")
                    .setView(inputEditTextField)
                    .setPositiveButton("OK") { _, _ ->
                        val editTextInput = inputEditTextField .text.toString()
                        var total = motor.stock.toInt() - editTextInput.toInt()
                        motorViewModel.update(total.toString(), motor.id!!)
                        val intent = Intent(this, MotorActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }
                    .setNegativeButton("Cancel", null)
                    .create()
                dialog.setView(inputEditTextField, 20,5,15,5)
                dialog.show()
            }

            binding.floatingActionButton.setOnClickListener {
                val inputEditTextField = EditText(this)
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Menambahkan Jumlah Stock Motor")
                    .setMessage("Total :")
                    .setView(inputEditTextField)
                    .setPositiveButton("OK") { _, _ ->
                        val editTextInput = inputEditTextField.text.toString()
                        var total = editTextInput.toInt() + motor.stock.toInt()
                        motorViewModel.update(total.toString(), motor.id!!)
                        val intent = Intent(this, MotorActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }
                    .setNegativeButton("Cancel", null)
                    .create()
                dialog.setView(inputEditTextField, 20,5,15,5)
                dialog.show()
            }
        }

        binding.topAppBar.setNavigationOnClickListener {
            val intent = Intent(this, MotorActivity::class.java)
            startActivity(intent)
            this.finish()
        }

    }


    private fun obtainMaiModel(activity: AppCompatActivity): MotorViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MotorViewModel::class.java]
    }
}