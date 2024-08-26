package com.example.projectfirst.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectfirst.databinding.FragmentEgoBinding


class EgoFragment : Fragment() {

    private var _binding: FragmentEgoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEgoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.egoSwitch.isChecked = true

        if(binding.egoSwitch.isChecked) {
            binding.hapinessSwitch.isEnabled = false
            binding.optimismSwitch.isEnabled = false
            binding.kidnessSwitch.isEnabled = false
            binding.givingSwitch.isEnabled = false
            binding.respectSwitch.isEnabled = false
        }

        binding.egoSwitch.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                binding.hapinessSwitch.isEnabled = false
                binding.optimismSwitch.isEnabled = false
                binding.kidnessSwitch.isEnabled = false
                binding.givingSwitch.isEnabled = false
                binding.respectSwitch.isEnabled = false
            }else{
                binding.hapinessSwitch.isEnabled = true
                binding.optimismSwitch.isEnabled = true
                binding.kidnessSwitch.isEnabled = true
                binding.givingSwitch.isEnabled = true
                binding.respectSwitch.isEnabled = true
            }
        }
    }




}