package com.bigthinkapps.sweatworks.utils

import com.bigthinkapps.sweatworks.data.model.ContactData

class DataSingleton {

    val listFavorites: ArrayList<ContactData> = arrayListOf()

    companion object {

        @Volatile
        private var INSTANCE: DataSingleton? = null

        fun getInstance(): DataSingleton =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: DataSingleton().also { INSTANCE = it }
            }
    }
}