package com.ilhamyp.appspenjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import com.ilhamyp.appspenjualan.databinding.ActivityAddMotorBinding
import com.ilhamyp.appspenjualan.databinding.ActivityMainBinding


class AddMotorActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMotorBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMotorBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.topAppBar.setNavigationOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
    }
}