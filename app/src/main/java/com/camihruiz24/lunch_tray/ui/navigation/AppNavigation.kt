package com.camihruiz24.lunch_tray.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.camihruiz24.lunch_tray.R
import com.camihruiz24.lunch_tray.data.DataSource
import com.camihruiz24.lunch_tray.model.OrderUiState
import com.camihruiz24.lunch_tray.ui.AccompanimentMenuScreen
import com.camihruiz24.lunch_tray.ui.CheckoutScreen
import com.camihruiz24.lunch_tray.ui.EntreeMenuScreen
import com.camihruiz24.lunch_tray.ui.OrderViewModel
import com.camihruiz24.lunch_tray.ui.SideDishMenuScreen
import com.camihruiz24.lunch_tray.ui.StartOrderScreen

enum class LunchTrayScreens(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    EntreeMenu(title = R.string.choose_entree),
    SideDishMenu(title = R.string.choose_side_dish),
    AccompanimentMenu(title = R.string.choose_accompaniment),
    Checkout(title = R.string.order_checkout),
}

@Composable
fun AppNavigation(
    navController: NavHostController,
    viewModel: OrderViewModel,
    uiState: OrderUiState,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = LunchTrayScreens.Start.name,
    ) {
        val onCancelClicked = {
            navController.popBackStack(
                LunchTrayScreens.Start.name,
                false
            )
            viewModel.resetOrder()
        }
        composable(LunchTrayScreens.Start.name) {
            StartOrderScreen(
                onStartOrderButtonClicked = { navController.navigate(LunchTrayScreens.EntreeMenu.name) },
                modifier
            )
        }
        composable(LunchTrayScreens.EntreeMenu.name) {
            EntreeMenuScreen(
                options = DataSource.entreeMenuItems,
                onCancelButtonClicked = { onCancelClicked() },
                onNextButtonClicked = { navController.navigate(LunchTrayScreens.SideDishMenu.name) },
                onSelectionChanged = { viewModel.updateEntree(it) },
                modifier
            )
        }
        composable(LunchTrayScreens.SideDishMenu.name) {
            SideDishMenuScreen(
                options = DataSource.sideDishMenuItems,
                onCancelButtonClicked = { onCancelClicked() },
                onNextButtonClicked = { navController.navigate(LunchTrayScreens.AccompanimentMenu.name) },
                onSelectionChanged = { viewModel.updateSideDish(it) },
                modifier
            )
        }
        composable(LunchTrayScreens.AccompanimentMenu.name) {
            AccompanimentMenuScreen(
                options = DataSource.accompanimentMenuItems,
                onCancelButtonClicked = { onCancelClicked() },
                onNextButtonClicked = { navController.navigate(LunchTrayScreens.Checkout.name) },
                onSelectionChanged = { viewModel.updateAccompaniment(it) },
                modifier
            )
        }
        composable(LunchTrayScreens.Checkout.name) {
            CheckoutScreen(
                orderUiState = uiState,
                onNextButtonClicked = { onCancelClicked() },
                onCancelButtonClicked = { onCancelClicked() },
                modifier = modifier.padding(dimensionResource(id = R.dimen.padding_medium))
            )
        }
    }
}
