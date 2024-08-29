package com.example.projectfirst.screens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import app.rive.runtime.kotlin.core.ExperimentalAssetLoader
import app.rive.runtime.kotlin.core.Rive
import com.example.projectfirst.MainActivity
import com.example.projectfirst.R
import com.example.projectfirst.databinding.FragmentEgoBinding
import com.example.projectfirst.model.BottomNavItems
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.snackbar.Snackbar

class EgoFragment : Fragment() {

    private var _binding: FragmentEgoBinding? = null
    private val binding get() = _binding!!
    private val bottomNav: BottomNavigationView? get() = (activity as? MainActivity)?.bottomNav
    private lateinit var switches: List<MaterialSwitch>
    private var MAX_ITEM_SIZE = 4
    private var rootView: View? = null
    private val stateMachineName = "State Machine 1"

    companion object  {
        const val STATE_MACHINE = "State Machine 1"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEgoBinding.inflate(inflater, container, false)
        Rive.init(requireContext())
        if (rootView == null) {
            rootView = binding.root
        }
        return binding.root
    }

    @OptIn(ExperimentalAssetLoader::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        switches = listOf(
            binding.hapinessSwitch,
            binding.optimismSwitch,
            binding.kidnessSwitch,
            binding.givingSwitch,
            binding.respectSwitch
        )

        fragmentViewCreated()
        disableSwitches()

        bottomNav!!.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.egoBackground))

        with(binding) {
            egoSwitch.onCheckedChange(R.id.egoFragment)
            hapinessSwitch.onCheckedChange(R.id.hapinessFragment)
            optimismSwitch.onCheckedChange(R.id.optimismFragment)
            kidnessSwitch.onCheckedChange(R.id.kidnessFragment)
            givingSwitch.onCheckedChange(R.id.givingFragment)
            respectSwitch.onCheckedChange(R.id.respectFragment)
            firstTorch.setRiveResource(R.raw.torch,stateMachineName = stateMachineName)
            secondTorch.setRiveResource(R.raw.torch,stateMachineName = stateMachineName)
            thirdTorch.setRiveResource(R.raw.torch,stateMachineName = stateMachineName)
            fourthTorch.setRiveResource(R.raw.torch,stateMachineName = stateMachineName)
        }
    }

    private fun MaterialSwitch.onCheckedChange(id: Int) {

        val viewId = this.id
        val viewIdString = requireContext().resources.getResourceEntryName(viewId)

        if (viewId == R.id.egoSwitch) {
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    setSwitchesFalse()
                    disableSwitches()
                    removeItemBottomNav(
                        BottomNavItems(
                            id,
                            viewIdString,
                        )
                    )
                    (activity as? MainActivity)?.bottomNav?.visibility = View.GONE
                } else {
                    enebleSwitches()
                    addItemBottomNav(
                        BottomNavItems(
                            id,
                            viewIdString,
                        )
                    )
                    (activity as? MainActivity)?.bottomNav?.visibility = View.VISIBLE
                }
            }
        } else {
            setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    lightTorch()
                    addItemBottomNav(
                        BottomNavItems(
                            id,
                            viewIdString
                        )
                    )
                } else {
                    (activity as? MainActivity)?.bottomNav?.menu?.findItem(id)?.isVisible = false
                    removeItemBottomNav(BottomNavItems(id, viewIdString))
                }
            }
        }
    }

    private fun lightTorch(){
        when(bottomNav!!.menu.size()){
            1 ->{
                binding.firstTorch.setNumberState(stateMachineName,"Fire",1F)
            }
            2 ->{
                binding.secondTorch.setNumberState(stateMachineName,"Fire",1F)
            }
            3 ->{
                binding.thirdTorch.setNumberState(stateMachineName,"Fire",1F)
            }
            4 ->{
                binding.fourthTorch.setNumberState(stateMachineName,"Fire",1F)
            }
        }
    }

    private fun offTorch(){

    }

    private fun enebleSwitches() {
        for (switch in switches) {
            switch.isEnabled = true
        }
    }

    private fun disableSwitches() {
        for (switch in switches) {
            switch.isEnabled = false
            setSwitchesFalse()
        }
        (activity as? MainActivity)?.bottomNav?.visibility = View.VISIBLE
    }

    private fun setSwitchesFalse() {
        for (switch in switches) {
            switch.isChecked = false
        }
    }

    private fun addItemBottomNav(menuItems: BottomNavItems) {
        if (bottomNav!!.menu.size() <= MAX_ITEM_SIZE) {
            if (bottomNav!!.menu.findItem(menuItems.id) != null) {
                return
            } else {
                when (menuItems.id) {
                    R.id.hapinessFragment -> bottomNav!!.menu.add(
                        Menu.NONE,
                        menuItems.id,
                        Menu.NONE,
                        menuItems.title
                    )!!
                        .setIcon(R.drawable.happy)

                    R.id.givingFragment -> bottomNav!!.menu.add(
                        Menu.NONE,
                        menuItems.id,
                        Menu.NONE,
                        menuItems.title
                    )!!
                        .setIcon(R.drawable.gift)

                    R.id.optimismFragment -> bottomNav!!.menu.add(
                        Menu.NONE,
                        menuItems.id,
                        Menu.NONE,
                        menuItems.title
                    )!!
                        .setIcon(R.drawable.like)

                    R.id.respectFragment -> bottomNav!!.menu.add(
                        Menu.NONE,
                        menuItems.id,
                        Menu.NONE,
                        menuItems.title
                    )!!
                        .setIcon(R.drawable.respect)

                    R.id.kidnessFragment -> bottomNav!!.menu.add(
                        Menu.NONE,
                        menuItems.id,
                        Menu.NONE,
                        menuItems.title
                    )!!
                        .setIcon(R.drawable.honesty)

                    else -> {
                        bottomNav!!.menu.add(Menu.NONE, menuItems.id, Menu.NONE, menuItems.title)!!
                            .setIcon(R.drawable.love_yourself)
                    }
                }
            }
        } else {
            Snackbar.make(binding.root, "Bottom Nav only have 5 items", Snackbar.LENGTH_SHORT)
                .show()
            return
        }
    }

    private fun removeItemBottomNav(menuItems: BottomNavItems) {
        if (bottomNav!!.menu.findItem(menuItems.id) == null) {
            return
        } else {
            bottomNav!!.menu.removeItem(menuItems.id)
        }

    }

    private fun fragmentViewCreated() {
        disableSwitches()
        binding.egoSwitch.isChecked = true
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}