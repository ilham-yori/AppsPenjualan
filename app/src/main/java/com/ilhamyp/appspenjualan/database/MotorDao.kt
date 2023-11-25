package com.ilhamyp.appspenjualan.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilhamyp.appspenjualan.model.Motor


@Dao
interface MotorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMotor(motor: Motor)

    @Query("UPDATE motor SET stock = :stock WHERE motor.id = :id")
    fun updateStockMotor(stock : String, id : Int)

    @Delete
    fun deleteMotor(motor: Motor)

    @Query("SELECT * from motor ORDER BY id ASC")
    fun getAllMotor(): DataSource.Factory<Int, Motor>

    @Query("SELECT * from motor WHERE motor.id = :search")
    fun getSpecificMotor(search : Int): Motor
}