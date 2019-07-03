package com.bigthinkapps.sweatworks.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.bigthinkapps.sweatworks.R
import com.bigthinkapps.sweatworks.data.model.ContactData
import com.bigthinkapps.sweatworks.databinding.ActivityDetailUserBinding
import com.bigthinkapps.sweatworks.ui.detail.viewmodel.DetailUserViewModel
import kotlinx.android.synthetic.main.activity_detail_user.*

const val EXTRA_CONTACT_DATA = "extra_contact_data"

class DetailUserActivity : AppCompatActivity() {

    private val viewModel by lazy {
        ViewModelProviders.of(this).get(DetailUserViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<ActivityDetailUserBinding>(this, R.layout.activity_detail_user)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val data = intent?.extras?.getParcelable<ContactData>(EXTRA_CONTACT_DATA)
        binding.contactData = data

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}