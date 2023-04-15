package com.assesment.grayhat.Fragments.tabFragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.assesment.grayhat.R
import com.assesment.grayhat.adapters.ItemAdapter
import com.assesment.grayhat.adapters.ItemClickListener
import com.assesment.grayhat.data.Model.Items
import com.assesment.grayhat.databinding.FragmentSmartwatchBinding

class SmartwatchFragment(val data: List<Items>) : Fragment(), ItemClickListener {

    lateinit var binding: FragmentSmartwatchBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSmartwatchBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = ItemAdapter(data, this)

    }

    override fun onItemClick(item: Items) {
        val bundle = Bundle().apply {
            item.apply {
                putString("name", name)
                putString("cat", category)
                putString("link", imgURL)
                if (price != null) {
                    putDouble("price", price)
                }
            }
        }
        val nav = findNavController()
        nav.navigate(R.id.action_homeFragment_to_detailFragment, bundle)
    }
}