package com.pemob.nindyaalif

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.pemob.nindyaalif.ui.screen.BasicInfoScreen
import com.pemob.nindyaalif.ui.screen.DaftarProdukScreen
import com.pemob.nindyaalif.ui.screen.DetailProductScreen
import com.pemob.nindyaalif.ui.screen.HubungiKamiScreen
import com.pemob.nindyaalif.ui.theme.JualanTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JualanTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "daftar_produk") {
                        composable(route = "daftar_produk") {
                            DaftarProdukScreen(navController = navController)
                        }
                        composable(
                            route = "detail/{productId}",
                            arguments = listOf(navArgument(name = "productId") {
                                type = NavType.IntType
                            })
                        ) { backStackEntry ->
                            val productId = backStackEntry.arguments?.getInt("productId") ?: 0
                            DetailProductScreen(
                                productId = productId,
                                navController = navController
                            )
                        }
                        composable(route = "hubungi_kami") {
                            HubungiKamiScreen(navController = navController)
                        }
                        composable(route = "form_screen") {
                            HubungiKamiScreen(navController = navController)
                        }
                        composable(route = "basic_info") {
                            BasicInfoScreen(
                                onNavigateToContact = { navController.navigate(route = "hubungi_kami") }
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    JualanTheme {
        BasicInfoScreen(onNavigateToContact = {})
    }
}
