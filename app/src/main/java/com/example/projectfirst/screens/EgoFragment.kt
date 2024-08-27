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
import com.example.projectfirst.model.BottomNavItems
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.snackbar.Snackbar


class EgoFragment : Fragment() {

    private var _binding: FragmentEgoBinding? = null
    private val binding get() = _binding!!
    private val bottomNav: BottomNavigationView? get() = (activity as? MainActivity)?.bottomNav
    private var counter: Int = 1


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


        with(binding){
            egoSwitch.onCheckedChange(R.id.egoFragment)
            hapinessSwitch.onCheckedChange(R.id.hapinessFragment)
            optimismSwitch.onCheckedChange(R.id.optimismFragment)
            kidnessSwitch.onCheckedChange(R.id.kidnessFragment)
            givingSwitch.onCheckedChange(R.id.givingFragment)
            respectSwitch.onCheckedChange(R.id.respectFragment)
        }

    }

    fun MaterialSwitch.onCheckedChange(id: Int){

        val viewId = this.id
        val viewIdString = requireContext().resources.getResourceEntryName(viewId)

        if(counter <= 4){
            if(viewId == R.id.egoSwitch){
                setOnCheckedChangeListener{ _, isChecked ->
                    if(isChecked){
                        setSwitchesFalse()
                        disableSwitches()
                        bottomNavItemVisibiltyFalse()
                        (activity as? MainActivity)?.bottomNav?.visibility = View.GONE
                        counter++
                    }else{
                        enebleSwitches()
                        setSwitchesFalse()
                        (activity as? MainActivity)?.bottomNav?.visibility = View.VISIBLE
                        counter--
                    }
                }
            }else{
                setOnCheckedChangeListener{_,isChecked ->
                    if(isChecked){
                        counter++
                        addItemBottomNav(BottomNavItems(id, viewIdString, R.drawable.baseline_not_interested_24))
                    }else{
                        (activity as? MainActivity)?.bottomNav?.menu?.findItem(id)?.isVisible = false
                        counter--
                    }
                }
            }
        }else{
            Snackbar.make(requireView(),R.string.limit, Snackbar.LENGTH_SHORT).show()
        }

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

    fun addItemBottomNav(menuItems: BottomNavItems){
        bottomNav!!.menu.add(Menu.NONE, menuItems.id, Menu.NONE, menuItems.title)!!.setIcon(menuItems.icon)
    }

    fun fragmentViewCreated(){
        disableSwitches()
        binding.egoSwitch.isChecked = true
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