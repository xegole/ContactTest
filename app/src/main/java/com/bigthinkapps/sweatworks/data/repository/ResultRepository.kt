package com.bigthinkapps.sweatworks.data.repository

import com.bigthinkapps.sweatworks.data.model.ContactData
import com.bigthinkapps.sweatworks.data.sources.ResultDataSource

class ResultRepository(private val resultDataSource: ResultDataSource) : ResultDataSource {

    override fun listAllResults(quantity: Int, success: (List<ContactData>) -> Unit, failure: () -> Unit) {
        resultDataSource.listAllResults(quantity, success, failure)
    }

    override fun onDispose() {
        resultDataSource.onDispose()
    }
}