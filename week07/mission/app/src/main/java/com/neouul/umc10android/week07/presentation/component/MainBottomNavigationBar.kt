package com.neouul.umc10android.week07.presentation.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.neouul.umc10android.week07.R
import com.neouul.umc10android.week07.core.routing.Route
import com.neouul.umc10android.week07.ui.AppColors

@Composable
fun MainBottomNavigationBar(
    modifier: Modifier = Modifier,
    currentRoute: Route,
    onItemClick: (Route) -> Unit,
) {
    val colors = NavigationBarItemDefaults.colors(
        selectedIconColor = AppColors.black,
        unselectedIconColor = AppColors.gray1,
        indicatorColor = Transparent, // 선택 시 생기는 배경 제거
    )

    NavigationBar(
        containerColor = AppColors.white,
        tonalElevation = 0.dp, // Indicator 배경 효과를 제거하고 싶을 때 사용
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp)
    ) {
        NavigationBarItem(
            selected = currentRoute is Route.Home,
            onClick = { onItemClick(Route.Home) },
            icon = { Icon(painterResource(R.drawable.ic_house_simple), "홈") },
            colors = colors
        )

        NavigationBarItem(
            selected = currentRoute is Route.Shop,
            onClick = { onItemClick(Route.Shop) },
            icon = { Icon(painterResource(R.drawable.ic_list_magnifying_glass), "구매하기") },
            colors = colors
        )

        NavigationBarItem(
            selected = currentRoute is Route.Wish,
            onClick = { onItemClick(Route.Wish) },
            icon = { Icon(painterResource(R.drawable.ic_heart_straight), "위시리스트") },
            colors = colors
        )

        NavigationBarItem(
            selected = currentRoute is Route.Cart,
            onClick = { onItemClick(Route.Cart) },
            icon = { Icon(painterResource(R.drawable.ic_bag_simple), "장바구니") },
            colors = colors
        )

        NavigationBarItem(
            selected = currentRoute is Route.Profile,
            onClick = { onItemClick(Route.Profile) },
            icon = { Icon(painterResource(R.drawable.ic_user), "프로필") },
            colors = colors
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainBottomNavigationBarPreview(modifier: Modifier = Modifier) {
    MainBottomNavigationBar(
        currentRoute = Route.Wish,
        onItemClick = {}
    )
}