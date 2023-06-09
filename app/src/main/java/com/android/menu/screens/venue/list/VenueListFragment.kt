package com.android.menu.screens.venue.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.menu.R
import com.android.menu.databinding.FragmentVenueListBinding
import com.android.menu.screens.venue.VenueViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class VenueListFragment : Fragment() {

    private var binding: FragmentVenueListBinding? = null
    private val viewModel: VenueViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVenueListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        setupObservers()
        getVenues()
    }

    private fun setupViews() {
        binding?.apply {

            context?.let { context ->
                recyclerView.layoutManager = LinearLayoutManager(context)
                val itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
                AppCompatResources.getDrawable(
                    context,
                    R.drawable.item_divider
                )?.let { drawable ->
                    itemDecoration.setDrawable(
                        drawable
                    )
                }
                recyclerView.addItemDecoration(itemDecoration)
                recyclerView.setHasFixedSize(true)
            }
        }
    }

    private fun setupObservers() {
        viewModel.venues.observe(viewLifecycleOwner) {
            it?.let { list ->
                binding?.recyclerView?.adapter = VenueListAdapter(list) { venue ->
                    val bundle = bundleOf("venue_item" to venue)
                    findNavController().navigate(
                        R.id.action_fragment_venue_list_to_fragment_venue_details,
                        bundle
                    )
                }
            }
        }
    }

    private fun getVenues() {
        viewModel.getVenues()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}
