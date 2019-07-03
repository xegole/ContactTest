package com.bigthinkapps.sweatworks.ui.main.viewmodel

import android.app.Application
import android.content.Intent
import android.view.View
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.bigthinkapps.sweatworks.data.model.ContactData
import com.bigthinkapps.sweatworks.data.remote.ResultDataSourceImpl
import com.bigthinkapps.sweatworks.data.repository.ResultRepository
import com.bigthinkapps.sweatworks.services.SweatWorksService
import com.bigthinkapps.sweatworks.ui.favorite.FavoriteActivity

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private val sweatWorksService by lazy {
        SweatWorksService.getService()
    }

    private val repository by lazy {
        ResultRepository(ResultDataSourceImpl(sweatWorksService))
    }

    val successResult = MutableLiveData<List<ContactData>>()
    val searchQuery = MutableLiveData<String>()

    fun loadResults(quantity: Int) {
        repository.listAllResults(quantity,
            {
                successResult.value = it
            }, {

            })
    }

    fun onFavoritesScreen() = View.OnClickListener {
        val intent = Intent(it.context, FavoriteActivity::class.java)
        it.context.startActivity(intent)
    }

}