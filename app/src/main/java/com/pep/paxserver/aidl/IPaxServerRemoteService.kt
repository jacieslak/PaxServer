package com.pep.paxserver.aidl

import com.pep.models.AppPid
import pl.pep.peplinker.annotation.RemoteInterface

@RemoteInterface
interface IPaxServerRemoteService {
    fun getPid(): Int

    fun getAppPid(): AppPid
}