package com.example.projectfirst.screens.ui.theme

import androidx.lifecycle.ViewModel
import com.example.projectfirst.model.SwitchElements
import kotlinx.coroutines.flow.MutableStateFlow

class ProjectViewModel: ViewModel() {

    val _switchState = MutableStateFlow<SwitchElements>(SwitchElements("", false))
    val switchState = _switchState

}