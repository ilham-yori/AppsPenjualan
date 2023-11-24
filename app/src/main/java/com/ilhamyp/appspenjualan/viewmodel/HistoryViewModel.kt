package com.ilhamyp.appspenjualan.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.ilhamyp.appspenjualan.model.History
import com.ilhamyp.appspenjualan.repository.HistoryRepository
import com.ilhamyp.appspenjualan.repository.MobilRepository

class HistoryViewModel(application: Application) : ViewModel() {

    private val mHistoryRepository: HistoryRepository = HistoryRepository(application)

    fun getAllHistory(): LiveData<List<History>> = mHistoryRepository.getAllHistory()

    fun insertHistory(history: History) {
        mHistoryRepository.insertHistory(history)
    }

    fun countPenjualan(tipeKendaran: String): String = mHistoryRepository.countPenjualan(tipeKendaran)

}