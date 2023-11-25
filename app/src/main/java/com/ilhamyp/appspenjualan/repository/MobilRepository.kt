package com.ilhamyp.appspenjualan.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ilhamyp.appspenjualan.database.MobilDao
import com.ilhamyp.appspenjualan.database.PenjualanRoomDatabases
import com.ilhamyp.appspenjualan.model.Mobil
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MobilRepository(application: Application) {

    private val mMobilDao: MobilDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PenjualanRoomDatabases.getDatabase(application)
        mMobilDao = db.mobilDao()
    }

    fun getAllMobil(): LiveData<PagedList<Mobil>> {

        val mobil = mMobilDao.getAllMobil()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(30)
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(mobil, config).build()
    }

    fun getSpecificMobil(search: Int) : Mobil = mMobilDao.getSpecificMobil(search)

    fun insertMobil(mobil: Mobil) {
        executorService.execute { mMobilDao.insertMobil(mobil) }
    }
    fun deleteMobil(mobil: Mobil) {
        executorService.execute { mMobilDao.deleteMobil(mobil) }
    }

    fun updateStockMobil(stock: String, id: Int) {
        executorService.execute { mMobilDao.updateStockMobil(stock, id) }
    }

}