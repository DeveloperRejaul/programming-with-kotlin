package com.test.myapplication.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.test.myapplication.core.api.NetworkResponse
import com.test.myapplication.core.component.Container
import com.test.myapplication.core.component.ListView
import com.test.myapplication.core.component.ScreenCenterLoading
import com.test.myapplication.core.component.TodoItem
import com.test.myapplication.core.navigation.Routes

var page = 1;
@Composable
fun HomeScreen(navController: NavHostController, homeViewModal: HomeViewModal) {
    val todos = homeViewModal.todos.collectAsState()
    val moreFetching = homeViewModal.moreFetching.collectAsState()
    val isRefreshing = homeViewModal.isRefreshing.collectAsState()

    // load fast file 10 item
    LaunchedEffect(Unit) {
        homeViewModal.getTodos()
    }


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Routes.Task)
            }) {
                Icon(Icons.Filled.Add, "Small floating action button.")
            }
        }
    ) { innerPadding ->
        Container(modifier = Modifier.padding(innerPadding), headerLabel = "TODO App", showBackIcon = false){
            when(todos.value) {
                is NetworkResponse.Error -> {
                    Text((todos.value as NetworkResponse.Error).message)
                }
                is NetworkResponse.Loading -> {
                    ScreenCenterLoading()
                }
                is NetworkResponse.Success<List<HomeModal>> -> {
                    val todoList = (todos.value as NetworkResponse.Success<List<HomeModal>>).data
                    ListView(
                        onRefresh = {
                            page = 1;
                            homeViewModal.getTodos(refreshing = true)
                        },
                        isRefreshing = isRefreshing.value === NetworkResponse.Loading,
                        items = todoList,
                        itemKey = {item -> item.id},
                        loadMore = {
                            page += 1;
                            homeViewModal.getTodosByPage(page,10)
                        },
                        itemContent = { todo ->
                            TodoItem(
                                todo,
                                onEdit = {
                                    homeViewModal.update(HomeModal(id = todo.id, title = "hello Word", body = "Hello Word", userId = 12))
                                    // navController.navigate(Routes.Task)
                                },
                                onDelete = {
                                    homeViewModal.removeTodo(todo.id)
                                }
                            )
                        },
                        isLoadingMore = moreFetching.value === NetworkResponse.Loading,
                        loadingItem = {
                            Box(
                                modifier = Modifier.fillMaxWidth(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(
                                    modifier = Modifier.width(20.dp),
                                    color = MaterialTheme.colorScheme.secondary,
                                    trackColor = MaterialTheme.colorScheme.surfaceVariant,
                                )
                            }
                        }
                    )
                }
                else -> Unit
            }
        }
    }
}