package com.bigthinkapps.sweatworks.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.bigthinkapps.sweatworks.R
import com.bigthinkapps.sweatworks.data.model.ContactData
import com.bigthinkapps.sweatworks.ui.main.adapter.viewholder.ContactVH

class ContactAdapter(private val listener: (ContactData) -> Unit = {}) : RecyclerView.Adapter<ContactVH>(), Filterable {

    private var contactList: List<ContactData> = emptyList()
    private var filteredData: List<ContactData> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactVH {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_contact, parent, false)
        return ContactVH(itemView)
    }

    override fun getItemCount() = filteredData.size

    override fun onBindViewHolder(holder: ContactVH, position: Int) {
        holder.setData(filteredData[position], listener)
    }

    fun setList(contactList: List<ContactData>) {
        this.contactList = contactList
        this.filteredData = contactList
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val filterString = charSequence.toString()
                val results = FilterResults()
                val filterDataList = arrayListOf<ContactData>()
                if (filterString.isNotEmpty()) {
                    for (data in contactList) {
                        if (data.name.first.toLowerCase().contains(filterString.toLowerCase())) {
                            filterDataList.add(data)
                        }
                    }
                    results.values = filterDataList
                } else {
                    results.values = contactList
                }

                return results
            }

            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                filteredData = filterResults.values as List<ContactData>
                notifyDataSetChanged()
            }
        }
    }
}