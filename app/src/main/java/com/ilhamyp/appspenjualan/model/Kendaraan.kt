package com.ilhamyp.appspenjualan.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
open class Kendaraan(
    open var tahunKeluaran: String,
    open var warna: String,
    open var harga: String,
    open var stock: String
) : Parcelable
