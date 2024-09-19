package com.example.lab666

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.lab666.ui.theme.Lab666Theme
import com.example.lab666.ui.theme.Rosadoanais

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Lab666Theme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "My App") },
                            navigationIcon = {
                                IconButton(onClick = { /* Acción del menú */ }) {
                                    Icon(Icons.Filled.Menu, contentDescription = "Menu")
                                }
                            },
                            colors = TopAppBarDefaults.topAppBarColors(
                                containerColor = Rosadoanais
                            )
                        )
                    },
                    floatingActionButton = {
                        FloatingActionButton(
                            onClick = {
                                // Se actualizará el contador en MainScreen
                            }
                        ) {
                            Icon(Icons.Filled.Add, contentDescription = "Add")
                        }
                    },
                    bottomBar = {
                        BottomBar(navController = navController)
                    }
                ) { innerPadding ->
                    NavigationComponent(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    BottomAppBar {
        IconButton(onClick = { navController.navigate("home") }) {
            Icon(Icons.Filled.Home, contentDescription = "Home")
        }
        IconButton(onClick = { navController.navigate("buscar") }) {
            Icon(Icons.Filled.Search, contentDescription = "Buscar")
        }
        IconButton(onClick = { navController.navigate("favoritos") }) {
            Icon(Icons.Filled.Favorite, contentDescription = "Favoritos")
        }
        IconButton(onClick = { navController.navigate("ajustes") }) {
            Icon(Icons.Filled.Settings, contentDescription = "Ajustes")
        }
    }
}

@Composable
fun NavigationComponent(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "home", modifier = modifier) {
        composable("home") {
            MainScreen()
        }
        composable("buscar") {
            Buscar()
        }
        composable("favoritos") {
            Favoritos()
        }
        composable("ajustes") {
            Ajustes()
        }
    }
}

@Composable
fun MainScreen() {
    var contador by remember { mutableStateOf(0) } // Estado del contador

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(text = "Contador: $contador")

        Spacer(modifier = Modifier.height(16.dp))

        FloatingActionButton(
            onClick = { contador++ } // Incrementa el contador al hacer clic
        ) {
            Icon(Icons.Filled.Add, contentDescription = "Incrementar")
        }
    }
}

@Composable
fun Buscar() {
    Text(text = "Esta es la ventana de buscar")
}

@Composable
fun Favoritos() {
    Text(text = "Esta es la pantalla de favoritos")
}

@Composable
fun Ajustes() {
    Text(text = "Esta es la pantalla de ajustes")
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Lab666Theme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = { Text(text = "My App") },
                    navigationIcon = {
                        IconButton(onClick = { /* Acción del menú */ }) {
                            Icon(Icons.Filled.Menu, contentDescription = "Menu")
                        }
                    }
                )
            },
            bottomBar = {
                BottomBar(navController = rememberNavController())
            }
        ) { innerPadding ->
            Greeting(name = "Android", modifier = Modifier.padding(innerPadding))
        }
    }
}
