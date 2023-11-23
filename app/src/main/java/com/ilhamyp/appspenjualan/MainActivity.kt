package com.ilhamyp.appspenjualan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.ilhamyp.appspenjualan.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        changeFragment(MotorFragment())

        binding.bottomNavigationView.setOnItemSelectedListener {
            when(it.itemId){
                R.id.Motor -> changeFragment(MotorFragment())
                R.id.Mobil -> changeFragment(MobilFragment())
                R.id.History -> changeFragment(HistoryFragment())
                else ->{

                }
            }
            true
        }

        binding.topAppBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.add_motor -> {
                    val intent = Intent(this, AddMotorActivity::class.java)
                    startActivity(intent)
                    true
                }

                else -> {
                    false

                }
            }
        }
    }

    private fun changeFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()
    }
}