package com.ilhamyp.appspenjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.ilhamyp.appspenjualan.databinding.ActivityDetailDataBinding
import com.ilhamyp.appspenjualan.model.History
import com.ilhamyp.appspenjualan.model.Mobil
import com.ilhamyp.appspenjualan.model.Motor
import com.ilhamyp.appspenjualan.viewmodel.HistoryViewModel
import com.ilhamyp.appspenjualan.viewmodel.MobilViewModel
import com.ilhamyp.appspenjualan.viewmodel.MotorViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailDataBinding
    private lateinit var motorViewModel: MotorViewModel
    private lateinit var mobilViewModel: MobilViewModel
    private lateinit var historyViewModel: HistoryViewModel
    private lateinit var motor: Motor
    private lateinit var mobil: Mobil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val typeIntent = intent.getStringExtra("detail_type")
        val idIntent = intent.getIntExtra("data_kendaraan",0)

        motorViewModel = ObtainViewModel.getMotorViewModel(this@DetailDataActivity)
        mobilViewModel = ObtainViewModel.getMobilViewModel(this@DetailDataActivity)
        historyViewModel = ObtainViewModel.getHistoryViewModel(this@DetailDataActivity)

        if(typeIntent == "Motor"){

            CoroutineScope(Dispatchers.IO).launch {
                motor = motorViewModel.getSpecificMotor(idIntent)
                withContext(Dispatchers.Main) {
                    motorSetupView(motor)
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
                        motorViewModel.updateStockMotor(total.toString(), motor.id!!)

                        historyViewModel.insertHistory(History(
                            null,
                            motor.tahunKeluaran,
                            motor.mesin,
                            typeIntent,
                            editTextInput,
                            DateHelper.getCurrentDate()
                        ))

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
                        motorViewModel.updateStockMotor(total.toString(), motor.id!!)
                        val intent = Intent(this, MotorActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }
                    .setNegativeButton("Cancel", null)
                    .create()
                dialog.setView(inputEditTextField, 20,5,15,5)
                dialog.show()
            }

            binding.topAppBar.setNavigationOnClickListener {
                val intent = Intent(this, MotorActivity::class.java)
                startActivity(intent)
                this.finish()
            }

        }else{

            CoroutineScope(Dispatchers.IO).launch {
                mobil = mobilViewModel.getSpecificMobil(idIntent)
                withContext(Dispatchers.Main) {
                    mobilSetupView(mobil)
                }
            }

            binding.btnJual.setOnClickListener {
                val inputEditTextField = EditText(this)
                val dialog = AlertDialog.Builder(this)
                    .setTitle("Masukkan Jumlah Mobil Yang Terjual")
                    .setMessage("Total :")
                    .setView(inputEditTextField)
                    .setPositiveButton("OK") { _, _ ->
                        val editTextInput = inputEditTextField .text.toString()
                        var total = mobil.stock.toInt() - editTextInput.toInt()
                        mobilViewModel.updateStockMobil(total.toString(), mobil.id!!)

                        historyViewModel.insertHistory(History(
                            null,
                            mobil.tahunKeluaran,
                            mobil.mesin,
                            typeIntent!!,
                            editTextInput,
                            DateHelper.getCurrentDate()
                        ))

                        val intent = Intent(this, MobilActivity::class.java)
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
                    .setTitle("Menambahkan Jumlah Stock Mobil")
                    .setMessage("Total :")
                    .setView(inputEditTextField)
                    .setPositiveButton("OK") { _, _ ->
                        val editTextInput = inputEditTextField.text.toString()
                        var total = editTextInput.toInt() + mobil.stock.toInt()
                        mobilViewModel.updateStockMobil(total.toString(), mobil.id!!)
                        val intent = Intent(this, MotorActivity::class.java)
                        startActivity(intent)
                        this.finish()
                    }
                    .setNegativeButton("Cancel", null)
                    .create()
                dialog.setView(inputEditTextField, 20,5,15,5)
                dialog.show()
            }

            binding.topAppBar.setNavigationOnClickListener {
                val intent = Intent(this, MobilActivity::class.java)
                startActivity(intent)
                this.finish()
            }

        }

    }
    private fun motorSetupView(motor: Motor){
        binding.tvDetailTahunKeluaran.setText(motor.tahunKeluaran)
        binding.tvDetailWarna.setText(motor.warna)
        binding.tvDetailHarga.setText(motor.harga)
        binding.tvDetailStock.setText(motor.stock)
        binding.tvDetailMesin.setText(motor.mesin)
        binding.tvDetailFirstData.setText(motor.tipeSuspensi)
        binding.tvDetailSecondData.setText(motor.tipeTransmisi)
        binding.btnJual.setText("Jual Motor")
    }

    private fun mobilSetupView(mobil: Mobil){
        binding.tvDetailTahunKeluaran.setText(mobil.tahunKeluaran)
        binding.tvDetailWarna.setText(mobil.warna)
        binding.tvDetailHarga.setText(mobil.harga)
        binding.tvDetailStock.setText(mobil.stock)
        binding.tvDetailMesin.setText(mobil.mesin)
        binding.tvDetailFirstData.setText(mobil.kapasitasPenumpang)
        binding.tvDetailSecondData.setText(mobil.tipe)
        binding.btnJual.setText("Jual Mobil")
    }
}