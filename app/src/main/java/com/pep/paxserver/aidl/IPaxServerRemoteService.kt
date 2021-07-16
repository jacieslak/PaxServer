package com.pep.paxserver.aidl

import pl.pep.peplinker.annotation.RemoteInterface

@RemoteInterface
interface IPaxServerRemoteService {
    fun getPid(): Int
}