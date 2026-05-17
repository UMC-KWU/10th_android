package com.neouul.umc10android.week07.core.routing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.neouul.umc10android.week07.presentation.screen.cart.CartScreen
import com.neouul.umc10android.week07.presentation.screen.home.HomeScreen
import com.neouul.umc10android.week07.presentation.screen.main.MainScreen
import com.neouul.umc10android.week07.presentation.screen.profiie.ProfileScreen
import com.neouul.umc10android.week07.presentation.screen.shop.ShopScreen
import com.neouul.umc10android.week07.presentation.screen.wish.WishScreen

@Composable
fun NavigationRoot(
    navController: NavHostController = rememberNavController(),
) {
    // 현재 경로 확인을 위한 상태
//    val navBackStackEntry by navController.currentBackStackEntryAsState()
//    val currentDestination = navBackStackEntry?.destination

    NavHost(
        navController = navController,
        startDestination = Route.Main,
    ) {
        composable<Route.Splash> {
            // SplashScreen()
        }
        composable<Route.SignIn> {
            // SignInScreen()
        }
        composable<Route.SignUp> {
            // SignUpScreen()
        }
        composable<Route.Main> {
            // MainScreen 내부 탭 전용의 새로운 NavController
            val mainNavController = rememberNavController()
            val mainNavBackStackEntry by mainNavController.currentBackStackEntryAsState()
            val mainCurrentDestination = mainNavBackStackEntry?.destination

            // 공통 네비게이션 로직
            val onTabClick: (Route) -> Unit = { route ->
                mainNavController.navigate(route) {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = true
                    }
                    launchSingleTop = true
                    restoreState = true
                }
            }

            MainScreen(
                currentDestination = mainCurrentDestination,
                onTabClick = onTabClick,
                body = { safeModifier ->
                    // 하단바 패딩이 적용된 modifier를 NavHost에 설정
                    NavHost(
                        navController = mainNavController,
                        startDestination = Route.MainGraph,
                        modifier = safeModifier
                    ) {
                        navigation<Route.MainGraph>(startDestination = Route.Home) {
                            composable<Route.Home> {
                                HomeScreen()
                            }
                            composable<Route.Shop> {
                                ShopScreen()
                            }
                            composable<Route.Wish> {
                                WishScreen()
                            }
                            composable<Route.Cart> {
                                CartScreen()
                            }
                            composable<Route.Profile> {
                                ProfileScreen()
                            }
                        }
                    }
                }
            )
        }

        composable<Route.ProductDetail> { backStackEntry ->
            // val productDetail: Route.ProductDetail = backStackEntry.toRoute()
            // ProductDetailScreen(productId = productDetail.productId)
        }
    }
}