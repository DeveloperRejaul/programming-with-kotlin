package com.test.http_retrofit
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
//import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.http_retrofit.api.NetworkResponse


@Composable
fun WeatherPage(weatherViewModal: WeatherViewModal = viewModel()) {
   var city by remember {
       mutableStateOf(value = "")
   }

    // this is from live data library we can also use it for more smart state management
    // val weatherResult = weatherViewModal.weatherResult.observeAsState()

    // this is build in state management
    val weatherResult = weatherViewModal.result


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp, top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            OutlinedTextField(
                modifier = Modifier.weight(1f),
                value = city,
                onValueChange = {
                    city = it
                },
                label = {
                    Text(text = "Search For any location")
                }
            )
            IconButton(onClick = {
                weatherViewModal.getData(city)
            }) {
                Icon(imageVector = Icons.Default.Search,
                    contentDescription = "Search For any location"
                )
            }
        }


        when(val result =  weatherResult) {
            is NetworkResponse.Error -> Text(result.message);
            NetworkResponse.Loading -> CircularProgressIndicator();
            is NetworkResponse.Success -> Text(result.data.toString())
            NetworkResponse.Initial -> {}
        }
    }
}










