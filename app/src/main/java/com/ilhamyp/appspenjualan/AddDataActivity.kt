package com.ilhamyp.appspenjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ilhamyp.appspenjualan.databinding.ActivityAddDataBinding
import com.ilhamyp.appspenjualan.model.Mobil
import com.ilhamyp.appspenjualan.model.Motor
import com.ilhamyp.appspenjualan.viewmodel.MobilViewModel
import com.ilhamyp.appspenjualan.viewmodel.MotorViewModel

class AddDataActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddDataBinding
    private lateinit var motorViewModel: MotorViewModel
    private lateinit var mobilViewModel: MobilViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val typeIntent = intent.getStringExtra("info_type")

        if (typeIntent == "Motor"){
            motorViewModel = obtainMotorViewModel(this@AddDataActivity)
            binding.addDataButton.setOnClickListener {

                motorViewModel.insert(Motor(null,
                    binding.tahunKendaranEditText.text.toString(),
                    binding.warnaEditText.text.toString(),
                    binding.hargaEditText.text.toString(),
                    binding.stockEditText.text.toString(),
                    binding.mesinEditText.text.toString(),
                    binding.firstDataEditText.text.toString(),
                    binding.secondDataEditText.text.toString()
                ))

                val intent = Intent(this, MotorActivity::class.java)
                startActivity(intent)
                this.finish()
            }

            binding.topAppBar.setNavigationOnClickListener {
                val intent = Intent(this, MotorActivity::class.java)
                startActivity(intent)
                this.finish()
            }
        }else{
            mobilViewSetup()
            mobilViewModel = obtainMobilViewModel(this@AddDataActivity)

            binding.addDataButton.setOnClickListener {
                mobilViewModel.insert(Mobil(null,
                    binding.tahunKendaranEditText.text.toString(),
                    binding.warnaEditText.text.toString(),
                    binding.hargaEditText.text.toString(),
                    binding.stockEditText.text.toString(),
                    binding.mesinEditText.text.toString(),
                    binding.firstDataEditText.text.toString(),
                    binding.secondDataEditText.text.toString()
                ))

                val intent = Intent(this, MobilActivity::class.java)
                startActivity(intent)
                this.finish()

            }

            binding.topAppBar.setNavigationOnClickListener {
                val intent = Intent(this, MobilActivity::class.java)
                startActivity(intent)
                this.finish()
            }
        }

    }

    private fun obtainMotorViewModel(activity: AppCompatActivity): MotorViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MotorViewModel::class.java]
    }

    private fun obtainMobilViewModel(activity: AppCompatActivity): MobilViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MobilViewModel::class.java]
    }

    private fun mobilViewSetup(){
        binding.topAppBar.title = "Form Penambahan Data Mobil"
        binding.firstDataTextInputLayout.setHint(R.string.kapasitas_penumpang)
        binding.secondDataTextInputLayout.setHint(R.string.tipe)
        binding.addDataButton.setText(R.string.add_mobil)
    }
}