package com.ilhamyp.appspenjualan

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.ilhamyp.appspenjualan.viewmodel.HistoryViewModel
import com.ilhamyp.appspenjualan.viewmodel.MobilViewModel
import com.ilhamyp.appspenjualan.viewmodel.MotorViewModel

object ObtainViewModel {

    fun getMotorViewModel(activity: AppCompatActivity): MotorViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MotorViewModel::class.java]
    }

    fun getMobilViewModel(activity: AppCompatActivity): MobilViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[MobilViewModel::class.java]
    }

    fun getHistoryViewModel(activity: AppCompatActivity): HistoryViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[HistoryViewModel::class.java]
    }

}