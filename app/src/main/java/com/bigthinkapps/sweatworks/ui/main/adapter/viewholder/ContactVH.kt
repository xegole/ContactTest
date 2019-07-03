package com.bigthinkapps.sweatworks.ui.main.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bigthinkapps.sweatworks.data.model.ContactData
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_contact.view.*

class ContactVH(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setData(person: ContactData, listener: (ContactData) -> Unit) {
        Picasso.get().load(person.picture.medium).into(itemView.imageContact)
        itemView.setOnClickListener {
            listener.invoke(person)
        }
    }
}