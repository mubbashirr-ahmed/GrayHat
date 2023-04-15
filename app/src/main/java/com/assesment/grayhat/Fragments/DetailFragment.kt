package com.assesment.grayhat.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.net.toUri
import coil.load
import com.assesment.grayhat.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment() {
    lateinit var binding: FragmentDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.apply {
            binding.apply {
                tvNameCat.text = "${getString("name")} ${getString("cat")}"
                tvPrice.text = getString("price")
                val url = getString("link")
                url?.let {
                    val imgUri = url.toUri().buildUpon()?.scheme("https")?.build()
                    ivDisplay.load(imgUri)
                }
            }
        }
    }


}