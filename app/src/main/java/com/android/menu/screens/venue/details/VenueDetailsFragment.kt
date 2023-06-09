package com.android.menu.screens.venue.details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.android.menu.R
import com.android.menu.databinding.FragmentVenueDetailsBinding
import com.android.menu.screens.login.LoginActivity
import com.android.menu.screens.venue.VenueViewModel
import com.android.model.local.Venue
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VenueDetailsFragment : Fragment() {

    private var binding: FragmentVenueDetailsBinding? = null
    private val viewModel: VenueViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVenueDetailsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
    }

    private fun setupViews() {

        val venue: Venue = arguments?.getSerializable("venue_item") as Venue
        binding?.apply {
            textName.text = venue.name
            textDescription.text = venue.description
            context?.let { context ->
                Glide.with(context).load(venue.image).into(imageVenue)
                textOpenStatus.setBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        (if (venue.isOpen)
                            R.color.orange
                        else
                            R.color.closed_grey)
                    )
                )
                textOpenStatus.text = if (venue.isOpen) "OPEN" else "CURRENTLY CLOSED"
            }

            buttonLogout.setOnClickListener {
                logoutUser()
            }
        }
    }

    private fun setupObservers() {
        viewModel.isUserLoggedOut.observe(viewLifecycleOwner) {
            if (it) {
                activity?.startActivity(Intent(context, LoginActivity::class.java))
                activity?.finish()
            }

        }
    }

    private fun logoutUser() {
        viewModel.logoutUser()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}