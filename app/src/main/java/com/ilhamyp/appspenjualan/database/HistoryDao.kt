package com.ilhamyp.appspenjualan.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ilhamyp.appspenjualan.model.History

@Dao
interface HistoryDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertHistory(history: History)

    @Query("SELECT * from history")
    fun getAllHistory(): LiveData<List<History>>

    @Query("SELECT SUM(totalPenjualan) FROM history WHERE tipeKendaraan = :tipeKendaraan")
    fun countPenjualan(tipeKendaraan : String): String

}