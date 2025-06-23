package com.test.mvvmapp.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.test.mvvmapp.model.UiState
import com.test.mvvmapp.viewModel.PostViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.test.mvvmapp.model.Post
import com.test.mvvmapp.ui.compose.Header
import com.test.mvvmapp.ui.navigation.NavRoutes

@Composable
fun HomeScreen(navController: NavHostController, viewModel: PostViewModel = viewModel()) {
   val state = viewModel.uiState

   Box(modifier = Modifier.fillMaxSize()) {
      when (state) {
         is UiState.Loading -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               CircularProgressIndicator()
            }
         }

         is UiState.Success -> {
            val posts = state.post

            Column(modifier = Modifier.padding(horizontal = 10.dp)) {
               Header("Posts")

               LazyColumn(
                  contentPadding = PaddingValues(bottom = 100.dp),
                  verticalArrangement = Arrangement.spacedBy(8.dp),
                  modifier = Modifier.fillMaxSize()
               ) {
                  items(posts) { post ->
                     ListItem(post, onClick = {
                        navController.navigate(NavRoutes.HOME_DETAIL)
                     })
                     HorizontalDivider(
                        thickness = 1.dp,
                        color = MaterialTheme.colorScheme.outlineVariant
                     )
                  }
               }
            }
         }

         is UiState.Error -> {
            Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
               Text("Error: ${state.message}", color = Color.Red)
            }
         }
      }
   }

}

@Composable
fun ListItem (post: Post, onClick: (Post) -> Unit = {}) {
   Column(
      modifier = Modifier
         .fillMaxWidth()
         .clickable{ onClick(post) }
         .padding(vertical = 10.dp)
   ) {
      Text(
         text = post.title,
         style = MaterialTheme.typography.titleMedium,
         maxLines = 1,
         overflow = TextOverflow.Ellipsis
      )
      Spacer(modifier = Modifier.height(4.dp))
      Text(
         text = post.body,
         style = MaterialTheme.typography.bodyMedium,
         maxLines = 2,
         overflow = TextOverflow.Ellipsis
      )
   }
}