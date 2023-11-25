package com.ilhamyp.appspenjualan.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ilhamyp.appspenjualan.database.HistoryDao
import com.ilhamyp.appspenjualan.database.PenjualanRoomDatabases
import com.ilhamyp.appspenjualan.model.History
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class HistoryRepository(application: Application) {

    private val mHistoryDao: HistoryDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PenjualanRoomDatabases.getDatabase(application)
        mHistoryDao = db.historyDao()
    }

    fun getAllHistory(): LiveData<PagedList<History>> {

        val history = mHistoryDao.getAllHistory()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(30)
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(history, config).build()
    }

    fun insertHistory(history: History) {
        executorService.execute { mHistoryDao.insertHistory(history) }
    }

    fun countPenjualan(tipeKendaran: String): String = mHistoryDao.countPenjualan(tipeKendaran)

}