package edu.ucne.blayverth_reyes_ap2_p1.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import edu.ucne.blayverth_reyes_ap2_p1.presentation.amonestacion.form.AmonestacionFormScreen
import edu.ucne.blayverth_reyes_ap2_p1.presentation.amonestacion.list.AmonestacionListScreen

@Composable
fun RegistroNavHost(
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController = navController,
        startDestination = Screen.AmonestacionList
    ){
        composable<Screen.AmonestacionList> {
            AmonestacionListScreen(
                onAddAmonestacion = {
                    navController.navigate(Screen.AmonestacionForm())
                },
                onEditAmonestacion = { id ->
                    navController.navigate(Screen.AmonestacionForm(id))
                }
            )
        }
        composable<Screen.AmonestacionForm> {
            AmonestacionFormScreen(
                onBack = {navController.navigateUp()}
            )
        }
    }
}