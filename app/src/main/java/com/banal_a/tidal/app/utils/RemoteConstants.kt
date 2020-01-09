package com.banal_a.tidal.app.utils

class RemoteContract {

    companion object {
        const val USER_ID = 11237314400
        const val BASE_URL = "https://api.tidal.com/v1"
        const val WEB_TOKEN = "wdgaB1CilGA-S_s2"
        const val HEADER_TOKEN = "x-tidal-token"

        // HEADER


        // URL
        const val LOGIN = "login"
        const val GET_PROFILE = "/v4/users/{userId}/profile"
        const val GET_BEAT = "/v1/beats/users/{userId}"
    }
}