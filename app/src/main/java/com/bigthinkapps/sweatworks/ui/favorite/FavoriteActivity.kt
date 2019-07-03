package com.bigthinkapps.sweatworks.ui.favorite

import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bigthinkapps.sweatworks.R
import com.bigthinkapps.sweatworks.databinding.ActivityFavoriteBinding
import com.bigthinkapps.sweatworks.extension.goIntent
import com.bigthinkapps.sweatworks.ui.detail.DetailUserActivity
import com.bigthinkapps.sweatworks.ui.detail.EXTRA_CONTACT_DATA
import com.bigthinkapps.sweatworks.ui.main.adapter.ContactAdapter
import com.bigthinkapps.sweatworks.ui.main.viewmodel.MainViewModel
import com.bigthinkapps.sweatworks.utils.AppConstants
import com.bigthinkapps.sweatworks.utils.DataSingleton
import kotlinx.android.synthetic.main.activity_detail_user.*
import kotlinx.android.synthetic.main.activity_main.*

class FavoriteActivity : AppCompatActivity() {

    private val listHistory: ArrayList<String> = arrayListOf()

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    private val adapter by lazy {
        ContactAdapter {
            val bundle = Bundle()
            bundle.putParcelable(EXTRA_CONTACT_DATA, it)
            goIntent(DetailUserActivity::class.java, bundle, true)
        }
    }

    private val adapterHistory by lazy {
        ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, listHistory)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityFavoriteBinding>(this, R.layout.activity_favorite)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.recyclerContact.adapter = adapter
        binding.searchSuggestion.setAdapter(adapterHistory)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        adapter.setList(DataSingleton.getInstance().listFavorites)

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
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
