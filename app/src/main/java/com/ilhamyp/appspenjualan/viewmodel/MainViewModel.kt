package com.ilhamyp.appspenjualan.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilhamyp.appspenjualan.model.Motor
import com.ilhamyp.appspenjualan.repository.MotorRepository

class MainViewModel(application: Application) : ViewModel() {
    private val mMotorRepository: MotorRepository = MotorRepository(application)
    fun getAllMotor(): LiveData<List<Motor>> = mMotorRepository.getAllMotor()
}