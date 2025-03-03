package com.sahil.cartoonapi

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sahil.cartoonapi.presentation.characters.detail.CharacterDetailScreen
import com.sahil.cartoonapi.presentation.characters.list.CharactersListScreen
import com.sahil.cartoonapi.ui.theme.CartoonApiTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CartoonApiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RickAndMortyApp(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun RickAndMortyApp( modifier : Modifier) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "characters_list"
    ) {
        composable("characters_list") {
            CharactersListScreen(
                onCharacterClick = { characterId ->
                    navController.navigate("character_detail/$characterId")
                }
            )
        }

        composable(
            route = "character_detail/{characterId}",
            arguments = listOf(
                navArgument("characterId") { type = NavType.IntType }
            )
        ) {
            CharacterDetailScreen(
                onBackClick = {
                    navController.popBackStack()
                }
            )
        }
    }
}