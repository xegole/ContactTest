package com.bigthinkapps.sweatworks.utils

import android.text.Editable
import android.text.TextWatcher
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import com.squareup.picasso.Picasso


@BindingAdapter("search_query")
fun setSearchQuery(searchView: AutoCompleteTextView, value: MutableLiveData<String>?) {
    searchView.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            value?.value = s.toString()
        }
    })
}

@BindingAdapter("image_value")
fun setImageValue(imageView: ImageView, value: String?) {
    Picasso.get().load(value).into(imageView)
}