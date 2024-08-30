package com.example.projectfirst.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.projectfirst.R

sealed class Screens(
    val route: String,
    val title: String,
    val icon: ImageVector,

    ) {
    object Ego : Screens("home", "Home", Icons.Default.Home)
    object Happy : Screens("happy", "Happy", Icons.Default.Face)
    object Kindness : Screens("kindness", "Kindness", Icons.Default.Favorite)
    object Respect : Screens("respect", "Respect", Icons.Default.Person)
    object Giving : Screens("giving", "Giving", Icons.Default.Done)
    object Optimism : Screens("optimism", "Optimism", Icons.Default.ThumbUp)
}
