package com.ilhamyp.appspenjualan.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class History(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0,
    var tahunKeluaran: String,
    var mesin: String,
    var tipeKendaraan: String,
    var totalPenjualan: String,
    var date: String
) : Parcelable
