package com.ilhamyp.appspenjualan.repository

import android.app.Application
import androidx.lifecycle.LiveData
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
    fun getAllMotor(): LiveData<List<Motor>> = mMotorDao.getAllMotor()

    fun getSpecificMotor(search: Int) : Motor = mMotorDao.getSpecificMotor(search)

    fun insert(motor: Motor) {
        executorService.execute { mMotorDao.insert(motor) }
    }
    fun delete(motor: Motor) {
        executorService.execute { mMotorDao.delete(motor) }
    }
    fun update(stock: String, id: Int) {
        executorService.execute { mMotorDao.update(stock, id) }
    }
}