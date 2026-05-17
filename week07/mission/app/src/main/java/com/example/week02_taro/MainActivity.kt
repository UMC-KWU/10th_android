package com.example.week02_taro

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PersonOutline
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme(
                colorScheme = lightColorScheme(
                    background = Color.White,
                    surface = Color.White
                )
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    ShoppingApp()
                }
            }
        }
    }
}

private sealed class BottomTab(
    val route: String,
    val label: String,
    val icon: ImageVector
) {
    data object Home : BottomTab(
        route = "home",
        label = "홈",
        icon = Icons.Outlined.Home
    )

    data object Shop : BottomTab(
        route = "shop",
        label = "구매하기",
        icon = Icons.Outlined.Search
    )

    data object Wishlist : BottomTab(
        route = "wishlist",
        label = "위시리스트",
        icon = Icons.Outlined.FavoriteBorder
    )

    data object Cart : BottomTab(
        route = "cart",
        label = "장바구니",
        icon = Icons.Outlined.ShoppingBag
    )

    data object Profile : BottomTab(
        route = "profile",
        label = "프로필",
        icon = Icons.Outlined.PersonOutline
    )
}

private val bottomTabs = listOf(
    BottomTab.Home,
    BottomTab.Shop,
    BottomTab.Wishlist,
    BottomTab.Cart,
    BottomTab.Profile
)

@Composable
private fun ShoppingApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: BottomTab.Home.route

    Scaffold(
        containerColor = Color.White,
        bottomBar = {
            ShoppingBottomBar(
                currentRoute = currentRoute,
                onTabClick = { tab ->
                    navController.navigateBottomTab(tab)
                }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomTab.Home.route,
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            composable(BottomTab.Home.route) {
                HomeScreen()
            }

            composable(BottomTab.Shop.route) {
                ShopScreen()
            }

            composable(BottomTab.Wishlist.route) {
                WishlistScreen()
            }

            composable(BottomTab.Cart.route) {
                CartScreen(
                    onOrderClick = {
                        navController.navigateBottomTab(BottomTab.Shop)
                    }
                )
            }

            composable(BottomTab.Profile.route) {
                ProfileScreen()
            }
        }
    }
}

private fun NavHostController.navigateBottomTab(tab: BottomTab) {
    navigate(tab.route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}

@Composable
private fun ShoppingBottomBar(
    currentRoute: String,
    onTabClick: (BottomTab) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        HorizontalDivider(
            thickness = 1.dp,
            color = Color(0xFFF4F4F4)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            bottomTabs.forEach { tab ->
                BottomBarItem(
                    tab = tab,
                    selected = currentRoute == tab.route,
                    onClick = { onTabClick(tab) }
                )
            }
        }
    }
}

@Composable
private fun BottomBarItem(
    tab: BottomTab,
    selected: Boolean,
    onClick: () -> Unit
) {
    val itemColor = if (selected) Color.Black else Color(0xFF8E8E8E)

    Column(
        modifier = Modifier
            .width(68.dp)
            .clickable(onClick = onClick)
            .padding(vertical = 6.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = tab.icon,
            contentDescription = tab.label,
            tint = itemColor,
            modifier = Modifier.size(25.dp)
        )

        Spacer(modifier = Modifier.height(2.dp))

        Text(
            text = tab.label,
            color = itemColor,
            fontSize = 10.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Composable
private fun HomeScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(86.dp))

        Text(
            text = "Discover",
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(start = 41.dp)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "9월 4일 목요일",
            color = Color(0xFF767676),
            fontSize = 16.sp,
            modifier = Modifier.padding(start = 41.dp)
        )

        Spacer(modifier = Modifier.height(50.dp))

        Image(
            painter = painterResource(id = R.drawable.img_home_banner),
            contentDescription = "홈 배너 이미지",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .padding(horizontal = 17.dp)
                .fillMaxWidth()
                .height(506.dp)
        )
    }
}

@Composable
private fun ShopScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 84.dp, start = 9.dp),
            verticalAlignment = Alignment.Top
        ) {
            ShopCategoryTab(
                text = "전체",
                selected = true
            )

            Spacer(modifier = Modifier.width(24.dp))

            ShopCategoryTab(
                text = "Tops & T-Shirts",
                selected = false
            )

            Spacer(modifier = Modifier.width(24.dp))

            ShopCategoryTab(
                text = "Shoes",
                selected = false
            )
        }
    }
}

@Composable
private fun ShopCategoryTab(
    text: String,
    selected: Boolean
) {
    val textColor = if (selected) Color.Black else Color(0xFF8E8E8E)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = text,
            color = textColor,
            fontSize = 16.sp,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(13.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(2.dp)
                .background(if (selected) Color.Black else Color.Transparent)
        )
    }
}

@Composable
private fun WishlistScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Text(
            text = "위시리스트",
            color = Color.Black,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(start = 24.dp, top = 94.dp)
        )
    }
}

@Composable
private fun CartScreen(
    onOrderClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(horizontal = 24.dp)
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (-42).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(60.dp)
                    .border(
                        width = 2.dp,
                        color = Color.Black,
                        shape = CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Outlined.ShoppingBag,
                    contentDescription = "빈 장바구니",
                    tint = Color.Black,
                    modifier = Modifier.size(30.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "장바구니가 비어 있습니다.\n제품을 추가하면 여기에 표시됩니다.",
                color = Color.Black,
                fontSize = 14.sp,
                lineHeight = 20.sp
            )
        }

        Button(
            onClick = onOrderClick,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 22.dp)
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(28.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
                contentColor = Color.White
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = "주문하기",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
private fun ProfileScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    )
}