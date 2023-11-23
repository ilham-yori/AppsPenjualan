package com.ilhamyp.appspenjualan.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilhamyp.appspenjualan.model.Mobil
import com.ilhamyp.appspenjualan.model.Motor
import com.ilhamyp.appspenjualan.repository.MobilRepository
import com.ilhamyp.appspenjualan.repository.MotorRepository

class MobilViewModel(application: Application) : ViewModel() {

    private val mMobilRepository: MobilRepository = MobilRepository(application)

    fun getAllMobil(): LiveData<List<Mobil>> = mMobilRepository.getAllMobil()

    fun getSpecificMotor(search: Int): Mobil = mMobilRepository.getSpecificMotor(search)

    fun insert(mobil: Mobil) {
        mMobilRepository.insert(mobil)
    }

    fun update(stock: String, id: Int) {
        mMobilRepository.update(stock,id)
    }

    fun delete(mobil: Mobil) {
        mMobilRepository.delete(mobil)
    }
}