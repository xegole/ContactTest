package com.bigthinkapps.sweatworks.ui.main

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bigthinkapps.sweatworks.R
import com.bigthinkapps.sweatworks.databinding.ActivityMainBinding
import com.bigthinkapps.sweatworks.extension.goIntent
import com.bigthinkapps.sweatworks.ui.detail.DetailUserActivity
import com.bigthinkapps.sweatworks.ui.detail.EXTRA_CONTACT_DATA
import com.bigthinkapps.sweatworks.ui.main.adapter.ContactAdapter
import com.bigthinkapps.sweatworks.ui.main.viewmodel.MainViewModel
import com.bigthinkapps.sweatworks.utils.AppConstants
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val listHistory: ArrayList<String> = arrayListOf()

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private val adapter by lazy {
        ContactAdapter {
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_CONTACT_DATA, it)
            goIntent(DetailUserActivity::class.java, bundle)
        }
    }

    private val adapterHistory by lazy {
        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listHistory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerContact.adapter = adapter
        binding.searchSuggestion.setAdapter(adapterHistory)

        viewModel.successResult.observe(this, Observer {
            adapter.setList(it)
        })

        searchSuggestion.setOnEditorActionListener { _, actionId, _ ->
            return@setOnEditorActionListener when (actionId) {
                EditorInfo.IME_ACTION_SEARCH -> {
                    val searchQuery = viewModel.searchQuery.value ?: AppConstants.EMPTY_STRING
                    adapter.filter.filter(searchQuery)
                    if (searchQuery.length > 2) {
                        adapterHistory.add(searchQuery)
                    }
                    true
                }
                else -> false
            }
        }

        viewModel.loadResults(AppConstants.INT_FIRST)
    }
}
