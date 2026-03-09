import android.content.Context
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.com.httpsguerni.crud.view.NavigationItem
import br.com.httpsguerni.crud.view.OperationScreens
import br.com.httpsguerni.crud.view.Screen
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController


@Composable
fun NavigationBar(context: Context) {
    val navController = rememberNavController()

    val navigationItems = listOf(
        NavigationItem("Insert", Icons.Default.Add, Screen.Insert.route),
        NavigationItem("Select", Icons.Default.Search, Screen.Select.route),
        NavigationItem("Update", Icons.Default.Edit, Screen.Update.route),
        NavigationItem("Delete", Icons.Default.Delete, Screen.Delete.route)
    )

    val screens = OperationScreens(context)

    Scaffold(
        bottomBar = {
            Navigation(navController, navigationItems)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Insert.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Insert.route) { screens.InsertScreen() }
            composable(Screen.Select.route) { screens.SelectScreen(context) }
            composable(Screen.Update.route) { screens.UpdateScreen() }
            composable(Screen.Delete.route) { screens.DeleteScreen() }
        }
    }
}

@Composable
fun Navigation(navController: NavController, navigationItems: List<NavigationItem>) {
    val selectedNavigationIndex = rememberSaveable { mutableIntStateOf(0) }

    NavigationBar(containerColor = Color.White) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex.intValue == index,
                onClick = {
                    selectedNavigationIndex.intValue = index
                    navController.navigate(item.route)
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.title) },
                label = {
                    Text(
                        item.title,
                        color = if (index == selectedNavigationIndex.intValue)
                            Color.Black else Color.Gray
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

