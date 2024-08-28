package com.example.projectfirst.model

data class BottomNavItems(
    val id: Int,
    val title: String,
    val icon: Int? = null
)

data class EgoUIState(
    val id: Int = 0,
    val isChecked: Boolean = false
)