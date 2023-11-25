package com.ilhamyp.appspenjualan.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ilhamyp.appspenjualan.model.Mobil
import com.ilhamyp.appspenjualan.repository.MobilRepository

class MobilViewModel(application: Application) : ViewModel() {

    private val mMobilRepository: MobilRepository = MobilRepository(application)

    fun getAllMobil(): LiveData<PagedList<Mobil>> = mMobilRepository.getAllMobil()

    fun getSpecificMobil(search: Int): Mobil = mMobilRepository.getSpecificMobil(search)

    fun insertMobil(mobil: Mobil) {
        mMobilRepository.insertMobil(mobil)
    }

    fun updateStockMobil(stock: String, id: Int) {
        mMobilRepository.updateStockMobil(stock,id)
    }

    fun deleteMobil(mobil: Mobil) {
        mMobilRepository.deleteMobil(mobil)
    }
}