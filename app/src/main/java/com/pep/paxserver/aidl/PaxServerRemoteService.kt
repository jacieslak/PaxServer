package com.pep.paxserver.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import dagger.hilt.android.AndroidEntryPoint
import pl.pep.peplinker.PePLinkerBinder

@AndroidEntryPoint
class PaxServerRemoteService : Service() {

    private var linkerBinder: PePLinkerBinder? = null

    override fun onCreate() {
        super.onCreate()
        linkerBinder = PePLinkerBinder.Factory.newBinder()
        linkerBinder?.registerObject(paxServerRemoteService)
    }

    override fun onBind(intent: Intent?): IBinder? {
        return linkerBinder
    }

    override fun onDestroy() {
        super.onDestroy()
        linkerBinder?.unRegisterObject(paxServerRemoteService)
    }

    private val paxServerRemoteService: IPaxServerRemoteService = object : IPaxServerRemoteService {
        override fun getPid(): Int {
            return Process.myPid()
        }

    }
}