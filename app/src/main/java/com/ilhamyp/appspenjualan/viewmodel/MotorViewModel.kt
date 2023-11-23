package com.ilhamyp.appspenjualan.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilhamyp.appspenjualan.model.Motor
import com.ilhamyp.appspenjualan.repository.MotorRepository

class MotorViewModel(application: Application) : ViewModel() {

    private val mMotorRepository: MotorRepository = MotorRepository(application)

    fun getAllMotor(): LiveData<List<Motor>> = mMotorRepository.getAllMotor()

    fun insert(motor: Motor) {
        mMotorRepository.insert(motor)
    }

    fun update(motor: Motor) {
        mMotorRepository.update(motor)
    }

    fun delete(motor: Motor) {
        mMotorRepository.delete(motor)
    }
}