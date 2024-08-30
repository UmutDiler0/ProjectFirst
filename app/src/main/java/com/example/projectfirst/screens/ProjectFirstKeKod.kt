package com.example.projectfirst.screens

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.projectfirst.model.SwitchData
import com.example.projectfirst.model.SwitchElements
import com.example.projectfirst.screens.ui.theme.GivingScreen
import com.example.projectfirst.screens.ui.theme.HapinessScreen
import com.example.projectfirst.screens.ui.theme.KindnessScreen
import com.example.projectfirst.screens.ui.theme.OptimismScreen
import com.example.projectfirst.screens.ui.theme.ProjectFirstTheme
import com.example.projectfirst.screens.ui.theme.RespectScreen

class ProjectFirstKeKod : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ProjectFirstTheme {
                val navController = rememberNavController()
                val items = listOf(
                    Screens.Ego,
                    Screens.Happy,
                    Screens.Kindness,
                    Screens.Respect,
                    Screens.Giving,
                    Screens.Optimism
                )
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    bottomBar = {
                        BottomNavigationBar(navController = navController, items = items)
                    }
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        NavigationGraph(
                            navController = navController,
                            modifier = Modifier.padding(innerPadding)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
    ) {
        Row(
            modifier = Modifier
                .height(300.dp)
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
            ) {
                SwitchData.loadSwitches().forEach {
                    Switches(it)
                }
            }
            Column(
                modifier = Modifier
                    .width(200.dp)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Top,
            ) {
                SwitchData.loadSwitches2().forEach {
                    Switches(it)
                }
            }

        }
    }
}

@Composable
private fun Switches(
    switchElement: SwitchElements,
    modifier: Modifier = Modifier,

    ) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 32.dp, start = 32.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = switchElement.name, modifier.padding(8.dp)
        )
        Switch(checked = switchElement.isChecked,
            onCheckedChange = {
                /*TODO*/
            })
    }
}

@Composable
fun BottomNavigationBar(navController: NavController, items: List<Screens>) {


    BottomAppBar(
        containerColor = Color.White,
        contentColor = Color.Black,
        tonalElevation = 5.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = null) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = {

                    if (currentRoute != screen.route) {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }

            )
        }
    }
}

@Composable
fun NavigationGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController, startDestination = Screens.Ego.route) {
        composable(Screens.Ego.route) {
            Greeting()
        }
        composable(Screens.Happy.route) {
            HapinessScreen()
        }
        composable(Screens.Kindness.route) {
            KindnessScreen()
        }
        composable(Screens.Giving.route) {
            GivingScreen()
        }
        composable(Screens.Respect.route) {
            RespectScreen()
        }
        composable(Screens.Optimism.route) {
            OptimismScreen()
        }
    }
}


@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun GreetingPreview() {
    ProjectFirstTheme {
        Greeting()
    }
}