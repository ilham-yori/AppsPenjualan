package com.ilhamyp.appspenjualan.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize


@Entity
@Parcelize
data class Motor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = 0,

    override var tahunKeluaran: String,
    override var warna: String,
    override var harga: String,
    override var stock: String,

    var mesin: String,
    var tipeSuspensi: String,
    var tipeTransmisi: String,
): Parcelable, Kendaraan(tahunKeluaran, warna, harga, stock)
