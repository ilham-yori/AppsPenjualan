package com.ilhamyp.appspenjualan.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ilhamyp.appspenjualan.model.Motor

@Database(entities = [Motor::class], version = 1)
abstract class PenjualanRoomDatabases : RoomDatabase() {

    abstract fun motorDao(): MotorDao

    companion object {
        @Volatile
        private var INSTANCE: PenjualanRoomDatabases? = null
        @JvmStatic
        fun getDatabase(context: Context): PenjualanRoomDatabases {
            if (INSTANCE == null) {
                synchronized(PenjualanRoomDatabases::class.java) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                        PenjualanRoomDatabases::class.java, "kendaraan_database")
                        .build()
                }
            }
            return INSTANCE as PenjualanRoomDatabases
        }
    }

}