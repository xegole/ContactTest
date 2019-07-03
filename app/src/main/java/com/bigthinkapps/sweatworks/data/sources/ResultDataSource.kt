package com.bigthinkapps.sweatworks.data.sources

import com.bigthinkapps.sweatworks.data.model.ContactData

interface ResultDataSource {

    fun listAllResults(
        quantity: Int,
        success: (List<ContactData>) -> Unit,
        failure: () -> Unit
    )

    fun onDispose()
}