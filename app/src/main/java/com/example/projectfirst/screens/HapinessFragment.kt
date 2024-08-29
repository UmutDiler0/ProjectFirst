package com.example.projectfirst.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import app.rive.runtime.kotlin.core.Rive
import com.example.projectfirst.MainActivity
import com.example.projectfirst.R
import com.example.projectfirst.databinding.FragmentHapinessBinding
import com.google.android.material.bottomnavigation.BottomNavigationView


class HapinessFragment : Fragment() {

    private var _binding: FragmentHapinessBinding? = null
    private val binding get() = _binding!!
    private val bottomNav: BottomNavigationView? get() = (activity as? MainActivity)?.bottomNav


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHapinessBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Rive.init(requireContext())

        bottomNav!!.setBackgroundColor(ContextCompat.getColor(requireContext(),R.color.hapinessColor1))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}