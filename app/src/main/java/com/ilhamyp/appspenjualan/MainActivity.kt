package com.ilhamyp.appspenjualan

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.ilhamyp.appspenjualan.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.motorButton.setOnClickListener {
            val intent = Intent(this, MotorActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.mobilButton.setOnClickListener {
            val intent = Intent(this, MobilActivity::class.java)
            startActivity(intent)
            this.finish()
        }

        binding.historyButton.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}