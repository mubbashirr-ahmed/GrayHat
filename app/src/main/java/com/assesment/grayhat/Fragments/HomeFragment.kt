package com.assesment.grayhat.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.assesment.grayhat.Fragments.ViewModels.ItemsViewModel
import com.assesment.grayhat.Fragments.tabFragments.CasioFragment
import com.assesment.grayhat.Fragments.tabFragments.SmartwatchFragment
import com.assesment.grayhat.Fragments.tabFragments.TistoFragment
import com.assesment.grayhat.adapters.ViewPagerAdapter
import com.assesment.grayhat.data.Model.Items
import com.assesment.grayhat.databinding.FragmentHomeBinding
import com.assesment.grayhat.util.UiState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: ItemsViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAllItems()
        viewModel.items.observe(viewLifecycleOwner){ state ->
            when(state){
               is UiState.Loading -> {
                   binding.progressCircular.visibility = View.VISIBLE
                   binding.viewPager.visibility = View.GONE
                   binding.tvError.visibility = View.GONE
               }
               is UiState.Success -> {
                   binding.viewPager.visibility =View.VISIBLE
                   binding.progressCircular.visibility = View.GONE
                   binding.tvError.visibility = View.GONE
                   setData(state.data)

               }
               is UiState.Failure -> {
                   binding.viewPager.visibility = View.GONE
                   binding.progressCircular.visibility = View.GONE
                   binding.tvError.visibility = View.VISIBLE
               }
           }
        }

    }

    private fun setData(data:List<Items>) {
        val adapter = ViewPagerAdapter(requireActivity().supportFragmentManager)
        adapter.apply {
            addFragment(SmartwatchFragment(data), "Smart Watch")
            addFragment(CasioFragment(), "Casio")
            addFragment(TistoFragment(), "Tisto")
        }
        binding.viewPager.adapter = adapter
        binding.tabs.setupWithViewPager(binding.viewPager)

    }


}