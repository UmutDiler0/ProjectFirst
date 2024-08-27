package com.example.projectfirst.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import com.example.projectfirst.MainActivity
import com.example.projectfirst.R
import com.example.projectfirst.databinding.FragmentEgoBinding
import com.google.android.material.materialswitch.MaterialSwitch


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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragmentViewCreated()
        disableSwitches()
        bottomNavItemVisibiltyFalse()

        binding.egoSwitch.setItemVisibility(R.id.egoFragment)

        binding.hapinessSwitch.setItemVisibility(R.id.hapinessFragment)

        binding.givingSwitch.setItemVisibility(R.id.givingFragment)

        binding.kidnessSwitch.setItemVisibility(R.id.kidnessFragment)

        binding.optimismSwitch.setItemVisibility(R.id.optimismFragment)

        binding.respectSwitch.setItemVisibility(R.id.respectFragment)
    }

    //material switch yapılacak ve ext func olarak yazılacak
    fun MaterialSwitch.setItemVisibility(id: Int){

        setOnCheckedChangeListener { _, isChecked ->
            if(id == R.id.egoFragment){
                if(isChecked){
                    disableSwitches()
                    bottomNavItemVisibiltyFalse()
                    (activity as? MainActivity)?.bottomNav?.visibility = View.VISIBLE
                }else{
                    enebleSwitches()
                    setSwitchesFalse()
                    (activity as? MainActivity)?.bottomNav?.visibility = View.VISIBLE
                }
            }
            (activity as? MainActivity)?.bottomNav?.menu?.findItem(id)?.isVisible = isChecked
        }

    }
    // ego açıp diğer switchleri açtıpımızda ekran değişip geri gelince bottom nav nasıl olmalı

    fun fragmentViewCreated(){
        disableSwitches()
        binding.egoSwitch.isChecked = true
    }

    fun enebleSwitches(){
        with(binding){
            hapinessSwitch.isEnabled = true
            optimismSwitch.isEnabled = true
            kidnessSwitch.isEnabled = true
            givingSwitch.isEnabled = true
            respectSwitch.isEnabled = true
        }
    }

    fun disableSwitches(){
        with(binding){
            hapinessSwitch.isEnabled = false
            optimismSwitch.isEnabled = false
            kidnessSwitch.isEnabled = false
            givingSwitch.isEnabled = false
            respectSwitch.isEnabled = false
            (activity as? MainActivity)?.bottomNav?.visibility = View.GONE
        }
    }

    fun bottomNavItemVisibiltyFalse(){
        (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.hapinessFragment)?.isVisible = false
        (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.optimismFragment)?.isVisible = false
        (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.kidnessFragment)?.isVisible = false
        (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.givingFragment)?.isVisible = false
        (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.respectFragment)?.isVisible = false
    }

    fun setSwitchesFalse(){
        with(binding){
            hapinessSwitch.isChecked = false
            optimismSwitch.isChecked = false
            kidnessSwitch.isChecked = false
            givingSwitch.isChecked = false
            respectSwitch.isChecked = false
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onStart() {
        super.onStart()
        fragmentViewCreated()
    }
}