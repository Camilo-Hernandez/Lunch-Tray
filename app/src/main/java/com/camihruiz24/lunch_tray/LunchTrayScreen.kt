/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.camihruiz24.lunch_tray

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.camihruiz24.lunch_tray.ui.OrderViewModel
import com.camihruiz24.lunch_tray.ui.navigation.AppNavigation
import com.camihruiz24.lunch_tray.ui.navigation.LunchTrayScreens
import com.camihruiz24.lunch_tray.ui.navigation.LunchTrayTopAppBar


@Composable
fun LunchTrayApp(
    viewModel: OrderViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {

    val backStackEntry by navController.currentBackStackEntryAsState()

    val currentScreen: LunchTrayScreens = LunchTrayScreens.valueOf(
        backStackEntry?.destination?.route ?: LunchTrayScreens.Start.name
    )

    Scaffold(
        topBar = {
            LunchTrayTopAppBar(
                currentScreen = currentScreen,
                canNavigateUp = navController.previousBackStackEntry != null, // currentScreen.name != LunchTrayScreens.Start.name,
                navigateUp = { navController.navigateUp() })
        }
    ) { innerPadding ->
        val uiState by viewModel.uiState.collectAsState()
        AppNavigation(navController, viewModel, uiState, Modifier.fillMaxSize().padding(innerPadding),)
    }
}

