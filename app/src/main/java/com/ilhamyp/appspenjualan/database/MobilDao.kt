package com.ilhamyp.appspenjualan.database

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilhamyp.appspenjualan.model.Mobil

@Dao
interface MobilDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertMobil(mobil: Mobil)

    @Query("UPDATE mobil SET stock = :stock WHERE mobil.id = :id")
    fun updateStockMobil(stock : String, id : Int)

    @Delete
    fun deleteMobil(mobil: Mobil)

    @Query("SELECT * from mobil ORDER BY id ASC")
    fun getAllMobil(): DataSource.Factory<Int, Mobil>

    @Query("SELECT * from mobil WHERE mobil.id = :search")
    fun getSpecificMobil(search : Int): Mobil
}