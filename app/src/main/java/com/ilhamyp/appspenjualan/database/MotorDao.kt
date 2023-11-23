package com.ilhamyp.appspenjualan.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.ilhamyp.appspenjualan.model.Motor


@Dao
interface MotorDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(motor: Motor)

    @Query("UPDATE motor SET stock = :stock WHERE motor.id = :id")
    fun update(stock : String, id : Int)

    @Delete
    fun delete(motor: Motor)

    @Query("SELECT * from motor")
    fun getAllMotor(): LiveData<List<Motor>>

    @Query("SELECT * from motor WHERE motor.id = :search")
    fun getSpecificMotor(search : Int): Motor
}