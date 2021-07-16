package com.pep.paxserver.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.os.Process
import android.util.Log
import dagger.hilt.android.AndroidEntryPoint
import pl.pep.peplinker.PePLinkerBinder

@AndroidEntryPoint
class PaxServerRemoteService : Service() {

    companion object {
        private const val TAG = "PaxServerRemoteService"
    }

    private var linkerBinder: PePLinkerBinder? = null

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate()")
        linkerBinder = PePLinkerBinder.Factory.newBinder()
        linkerBinder?.registerObject(paxServerRemoteService)
    }

    override fun onBind(intent: Intent?): IBinder? {
        Log.d(TAG, "onBind()")
        return linkerBinder
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy()")
        super.onDestroy()
        linkerBinder?.unRegisterObject(paxServerRemoteService)
    }

    private val paxServerRemoteService: IPaxServerRemoteService = object : IPaxServerRemoteService {
        override fun getPid(): Int {
            Log.d(TAG, "server getPid()")
            return Process.myPid()
        }

        override fun getAppPid(): AppPid {
            Log.d(TAG, "server getAppPid()")
            val result = AppPid("PaxServer", Process.myPid())
            Log.d(TAG, "server sends $result")
            return result
        }

    }
}