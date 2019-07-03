package com.bigthinkapps.sweatworks.data.remote

import com.bigthinkapps.sweatworks.data.model.ContactData
import com.bigthinkapps.sweatworks.data.sources.ResultDataSource
import com.bigthinkapps.sweatworks.services.SweatWorksApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class ResultDataSourceImpl(private val sweatWorksApi: SweatWorksApi) : ResultDataSource {

    private var disposable: Disposable? = null

    override fun listAllResults(
        quantity: Int,
        success: (List<ContactData>) -> Unit,
        failure: () -> Unit
    ) {
        disposable = sweatWorksApi.getResults(quantity)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                success(response.results)
            }, {
                it.printStackTrace()
                failure()
            })
    }

    override fun onDispose() {
        disposable?.dispose()
    }
}