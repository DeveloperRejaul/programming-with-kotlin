package com.test.android_todo_app.ui.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.test.android_todo_app.ui.theme.Typography
import java.time.format.TextStyle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
fun HomeScreen () {
    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row (
                        modifier = Modifier.fillMaxWidth().padding(end = 10.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Todo App")
                        Button(onClick = {}) {
                            Text(text = "Add TODO")
                        }
                    }
                }

            )

        }
    ) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding).padding(horizontal = 10.dp, vertical = 10.dp)
        ){
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceVariant,
                ),
                modifier = Modifier.fillMaxWidth().height(150.dp)
            ) {
                Column (
                    modifier = Modifier.padding(10.dp).fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Text(
                            text = "Card Title",
                            modifier = Modifier,
                            style = Typography.titleLarge
                        )
                        Text(
                            text = "Card DescriptionsCard",
                            modifier = Modifier,
                            style = Typography.titleSmall,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis
                        )

                    }
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.error, // background color
                                contentColor = MaterialTheme.colorScheme.onPrimary // text/icon color
                            )
                        ) {
                            Text(text = "Delete")
                        }
                        Button(
                            onClick = {},
                            colors = ButtonDefaults.buttonColors(
                                containerColor = MaterialTheme.colorScheme.primary, // background color
                                contentColor = MaterialTheme.colorScheme.onPrimary // text/icon color
                            )
                        ) {
                            Text(text = "Edit")
                        }
                    }
                }

            }
        }
    }

}




















