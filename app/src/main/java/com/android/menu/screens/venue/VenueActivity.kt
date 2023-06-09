package com.android.menu.screens.venue

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.android.menu.databinding.ActivityVenueBinding
import com.android.menu.screens.BaseActivity
import com.android.menu.viewModel.BaseViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class VenueActivity : BaseActivity() {

    private lateinit var binding: ActivityVenueBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVenueBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
    }

    override val viewModel: VenueViewModel by inject()

    private fun setObservers() {
        viewModel.loadingVisibility.observe(this) { visible ->
            binding.groupProgress.visibility = if (visible) View.VISIBLE else View.GONE
        }
    }
}
