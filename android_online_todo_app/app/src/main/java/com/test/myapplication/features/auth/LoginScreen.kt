package com.test.myapplication.features.auth

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.setValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.test.myapplication.core.theme.Typography
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.myapplication.core.api.NetworkResponse
import com.test.myapplication.core.component.Button
import com.test.myapplication.core.component.Input
import com.test.myapplication.core.component.InputVariant
import com.test.myapplication.core.navigation.Routes


@Composable
fun LoginScreen(navController: NavHostController, viewModal: AuthViewModal = viewModel()) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val response = viewModal.result.observeAsState()

    LaunchedEffect(response.value) {
        when(response.value) {
            is NetworkResponse.Success -> {
                navController.navigate(Routes.Home){
                    popUpTo(0)
                }
            }
           else -> Unit
        }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 10.dp),
        verticalArrangement =Arrangement.Center,
        horizontalAlignment =  Alignment.CenterHorizontally
    ){
        Text("Welcome TO TODO App", style = Typography.titleLarge.copy(fontSize = 30.sp, fontWeight = FontWeight.Bold))
        Spacer(modifier = Modifier.height(height = 50.dp))
        Input(
            value = userName,
            onChange = { userName = it},
            placeholder = "Type your user name",
            label = "User name",
        )
        Spacer(modifier = Modifier.height(height = 10.dp))
        Input(
            value = password,
            onChange = { password = it},
            placeholder = "Type your password",
            label = "Password",
            variant = InputVariant.PASSWORD
        )
        Spacer(modifier = Modifier.height(height = 10.dp))
        Button(
            text = "Login",
            onClick = {
                viewModal.login(userName = userName, password = password)
            },
            isLoading = response.value === NetworkResponse.Loading
        )
        Row(
            modifier = Modifier.padding(top = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text("You don't have an account? ", style = Typography.bodyMedium)
            TextButton(
                onClick = {
                    navController.navigate(Routes.Register)
                },
                contentPadding = PaddingValues(0.dp)
            ) {
                Text("Register")
            }
        }

    }
}

