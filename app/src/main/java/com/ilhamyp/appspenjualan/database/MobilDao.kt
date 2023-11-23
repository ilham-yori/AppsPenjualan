package com.ilhamyp.appspenjualan.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilhamyp.appspenjualan.model.Mobil
import com.ilhamyp.appspenjualan.model.Motor

@Dao
interface MobilDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(mobil: Mobil)

    @Query("UPDATE mobil SET stock = :stock WHERE mobil.id = :id")
    fun update(stock : String, id : Int)

    @Delete
    fun delete(mobil: Mobil)

    @Query("SELECT * from mobil")
    fun getAllMobil(): LiveData<List<Mobil>>

    @Query("SELECT * from mobil WHERE mobil.id = :search")
    fun getSpecificMobil(search : Int): Mobil
}