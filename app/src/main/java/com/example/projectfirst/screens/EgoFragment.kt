package com.example.projectfirst.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.projectfirst.MainActivity
import com.example.projectfirst.R
import com.example.projectfirst.databinding.FragmentEgoBinding


class EgoFragment : Fragment() {

    private var _binding: FragmentEgoBinding? = null
    private val binding get() = _binding!!
    var counter: Int = 0


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

        binding.egoSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                disableSwitches()
                setSwitchesFalse()
                (activity as? MainActivity)?.bottomNav?.visibility = View.GONE
            } else {
                enebleSwitches()
                (activity as? MainActivity)?.bottomNav?.visibility = View.VISIBLE
                bottomNavItemVisibiltyFalse()
            }
        }

        binding.hapinessSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.hapinessFragment)?.isVisible = true
            }else{
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.hapinessFragment)?.isVisible = false
            }
        }

        binding.givingSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.givingFragment)?.isVisible = true
            }else{
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.givingFragment)?.isVisible = false
            }
        }

        binding.kidnessSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.kidnessFragment)?.isVisible = true
            }else{
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.kidnessFragment)?.isVisible = false
            }
        }

        binding.optimismSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.optimismFragment)?.isVisible = true
            }else{
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.optimismFragment)?.isVisible = false
            }
        }

        binding.respectSwitch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.respectFragment)?.isVisible = true
            }else{
                (activity as? MainActivity)?.bottomNav?.menu?.findItem(R.id.respectFragment)?.isVisible = false
            }
        }
    }

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