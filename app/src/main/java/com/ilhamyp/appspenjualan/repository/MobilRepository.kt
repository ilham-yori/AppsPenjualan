package com.ilhamyp.appspenjualan.repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.ilhamyp.appspenjualan.database.MobilDao
import com.ilhamyp.appspenjualan.database.MotorDao
import com.ilhamyp.appspenjualan.database.PenjualanRoomDatabases
import com.ilhamyp.appspenjualan.model.Mobil
import com.ilhamyp.appspenjualan.model.Motor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MobilRepository(application: Application) {

    private val mMobilDao: MobilDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PenjualanRoomDatabases.getDatabase(application)
        mMobilDao = db.mobilDao()
    }
    fun getAllMobil(): LiveData<List<Mobil>> = mMobilDao.getAllMobil()
    fun getSpecificMotor(search: Int) : Mobil = mMobilDao.getSpecificMobil(search)

    fun insert(mobil: Mobil) {
        executorService.execute { mMobilDao.insert(mobil) }
    }
    fun delete(mobil: Mobil) {
        executorService.execute { mMobilDao.delete(mobil) }
    }
    fun update(stock: String, id: Int) {
        executorService.execute { mMobilDao.update(stock, id) }
    }
}