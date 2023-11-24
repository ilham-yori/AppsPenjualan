package com.ilhamyp.appspenjualan.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilhamyp.appspenjualan.model.Motor
import com.ilhamyp.appspenjualan.repository.MotorRepository

class MotorViewModel(application: Application) : ViewModel() {

    private val mMotorRepository: MotorRepository = MotorRepository(application)

    fun getAllMotor(): LiveData<List<Motor>> = mMotorRepository.getAllMotor()

    fun getSpecificMotor(search: Int): Motor = mMotorRepository.getSpecificMotor(search)

    fun insertMotor(motor: Motor) {
        mMotorRepository.insertMotor(motor)
    }

    fun updateStockMotor(stock: String, id: Int) {
        mMotorRepository.updateStockMotor(stock,id)
    }

    fun deleteMotor(motor: Motor) {
        mMotorRepository.deleteMotor(motor)
    }
}