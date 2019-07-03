package com.bigthinkapps.sweatworks.ui.detail.viewmodel

import android.app.Activity
import android.app.Application
import android.content.Intent
import android.provider.ContactsContract
import android.view.View
import androidx.lifecycle.AndroidViewModel
import com.bigthinkapps.sweatworks.data.model.ContactData
import com.bigthinkapps.sweatworks.ui.favorite.FavoriteActivity
import com.bigthinkapps.sweatworks.utils.DataSingleton


class DetailUserViewModel(application: Application) : AndroidViewModel(application) {

    fun onSaveContact(contactData: ContactData) = View.OnClickListener {
        val contactIntent = Intent(ContactsContract.Intents.Insert.ACTION)
        contactIntent.type = ContactsContract.RawContacts.CONTENT_TYPE

        contactIntent
            .putExtra(ContactsContract.Intents.Insert.NAME, "${contactData.name.first} ${contactData.name.last}")
            .putExtra(ContactsContract.Intents.Insert.PHONE, contactData.phone)

        it.context.startActivity(contactIntent)
    }

    fun onSaveFavorite(contactData: ContactData) = View.OnClickListener {
        DataSingleton.getInstance().listFavorites.add(contactData)
        val activity = it.context as Activity
        val intent = Intent(activity, FavoriteActivity::class.java)
        activity.startActivity(intent)
        activity.finish()
    }
}