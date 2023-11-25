package com.ilhamyp.appspenjualan.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ilhamyp.appspenjualan.database.MotorDao
import com.ilhamyp.appspenjualan.database.PenjualanRoomDatabases
import com.ilhamyp.appspenjualan.model.Motor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MotorRepository(application: Application) {

    private val mMotorDao: MotorDao
    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    init {
        val db = PenjualanRoomDatabases.getDatabase(application)
        mMotorDao = db.motorDao()
    }

    fun getAllMotor(): LiveData<PagedList<Motor>> {

        val motor = mMotorDao.getAllMotor()

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setInitialLoadSizeHint(30)
            .setPageSize(10)
            .build()

        return LivePagedListBuilder(motor, config).build()
    }

    fun getSpecificMotor(search: Int) : Motor = mMotorDao.getSpecificMotor(search)

    fun insertMotor(motor: Motor) {
        executorService.execute { mMotorDao.insertMotor(motor) }
    }

    fun deleteMotor(motor: Motor) {
        executorService.execute { mMotorDao.deleteMotor(motor) }
    }

    fun updateStockMotor(stock: String, id: Int) {
        executorService.execute { mMotorDao.updateStockMotor(stock, id) }
    }

}