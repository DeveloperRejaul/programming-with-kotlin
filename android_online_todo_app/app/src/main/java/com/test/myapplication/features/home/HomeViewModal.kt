package com.test.myapplication.features.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.test.myapplication.core.api.NetworkResponse
import com.test.myapplication.core.api.RetrofitInstance
import com.test.myapplication.core.navigation.Routes
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeViewModal: ViewModel() {
    private val homeApi = RetrofitInstance.homeApi;

    // all state
    private val _todos = MutableStateFlow<NetworkResponse<List<HomeModal>>>(NetworkResponse.Initial)
    val todos: StateFlow<NetworkResponse<List<HomeModal>>> = _todos

    //  More Fetching state
    private val _moreFetching = MutableStateFlow<NetworkResponse<Boolean>>(NetworkResponse.Initial)
    val moreFetching: StateFlow<NetworkResponse<Boolean>> = _moreFetching

    // Refreshing state
    private val _isRefreshing = MutableStateFlow<NetworkResponse<Boolean>>(NetworkResponse.Initial)
    val isRefreshing: StateFlow<NetworkResponse<Boolean>> = _isRefreshing


    // for create and update loading
    private val _isLoading = MutableStateFlow<NetworkResponse<Boolean>>(NetworkResponse.Initial)
    val isLoading: StateFlow<NetworkResponse<Boolean>> = _isLoading


    // Navigation event channel
    private val _navEvent = Channel<Int>(Channel.BUFFERED)
    val navEvent = _navEvent.receiveAsFlow()


    /**
     * 🚀 Fetches a paginated list of Todos from the Home API.
     *
     * This function:
     * - Sets the initial loading state.
     * - Launches a coroutine in the ViewModel scope.
     * - Calls the API to get a page of Todos.
     * - Updates the `_todos` StateFlow with `Success` if the call succeeds,
     *   or `Error` if it fails.
     *
     * It uses a simple, single-load pattern without appending — ideal for initial load.
     *
     * 📌 Log tag: "API_CHECK" — check logs for response details.
     *
     * Note: Wraps the network call in a try-catch to handle exceptions gracefully.
     */
    fun getTodos (refreshing: Boolean = false) {
        _todos.value = if(refreshing) NetworkResponse.Initial else NetworkResponse.Loading;
        _isRefreshing.value = if(refreshing) NetworkResponse.Loading else NetworkResponse.Initial;
        viewModelScope.launch {
            try {
                val res = homeApi.getTodos(page = 1, limit = 10)
                if(res.isSuccessful) {
                    res.body().let {
                        _todos.value = NetworkResponse.Success(it as List<HomeModal>)
                        if(refreshing) _isRefreshing.value = NetworkResponse.Success(true)
                    }
                }else {
                    _todos.value = NetworkResponse.Error("Failed To load data")
                    if(refreshing) _isRefreshing.value = NetworkResponse.Error("Failed To Refresh data")
                }
            }catch (e: Exception) {
                Log.i("APP_ERROR_ACTION", e.toString())
                _todos.value = NetworkResponse.Error("Failed To load data")
                if(refreshing) _isRefreshing.value = NetworkResponse.Error("Failed To Refresh data")
            }
        }
    }

    /**
     * 🚀 Fetches a page of Todos from the Home API and appends it to the existing list.
     *
     * This function:
     * - Updates the `_moreFetching` StateFlow to `Loading` while fetching.
     * - Calls the API with the given `page` and `limit` parameters.
     * - If successful, appends the new page of Todos to the current list using `_todos.update`.
     * - Emits `PaginateResponse.Success` to indicate more data was fetched successfully.
     * - Emits `PaginateResponse.Error` with an appropriate message if the response is empty
     *   or if an exception occurs.
     *
     * ✅ Safe: Uses `.update {}` to merge old and new lists.
     * ✅ Handles null response bodies with `?.let`.
     * ✅ Uses `viewModelScope` for proper coroutine cancellation.
     *
     * Note: This function supports infinite scrolling or load-more scenarios.
     */
    fun getTodosByPage (page:Int, limit: Int) {
        _moreFetching.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val res = homeApi.getTodos(page, limit)
                if (res.isSuccessful) {
                    res.body()?.let {
                        _moreFetching.value = NetworkResponse.Success(true)

                        _todos.update { currentResponse ->
                            val currentList = if (currentResponse is NetworkResponse.Success) {
                                currentResponse.data
                            } else {
                                emptyList()
                            }

                            val updatedList = currentList + it
                            NetworkResponse.Success(updatedList)
                        }

                    } ?: run {
                        _moreFetching.value = NetworkResponse.Error("Empty response")
                    }
                }
            }catch (e: Exception) {
                Log.i("APP_ERROR_ACTION", e.toString())
                _moreFetching.value = NetworkResponse.Error("Failed To load more data")
            }
        }
    }


    /**
     * Removes a todo item with the specified [id] from the server and updates the local state.
     *
     * This function launches a coroutine in [viewModelScope] to call the [homeApi.deleteTodo] API.
     * If the deletion is successful, it updates the [_todos] StateFlow by removing the todo item
     * with the given [id] from the current list.
     *
     * Logs any exception that occurs during the API call.
     *
     * @param id The unique identifier of the todo item to be deleted.
     */
    fun removeTodo (id: Int) {
        viewModelScope.launch {
            try {
                val res = homeApi.deleteTodo(postId = id)
                if (res.isSuccessful) {
                    _todos.update { currentTodos ->
                        if(currentTodos is NetworkResponse.Success) {
                            val updateData =  currentTodos.data.filterNot { it.id == id }
                            NetworkResponse.Success(updateData)
                        }else {
                            currentTodos
                        }
                    }
                }
            }catch (e: Exception) {
                Log.i("APP_ERROR_ACTION", e.toString())
            }
        }
    }



    /**
     * Updates a specific todo item on the server and updates the local state if successful.
     *
     * - Sends a PUT request using the `homeApi.update()` function.
     * - If successful, updates the `_todos` state by replacing the matching item.
     * - Emits a navigation event via `_navEvent` to indicate completion.
     * - Handles loading and error states using `_isLoading`.
     *
     * @param todo The [HomeModal] object representing the updated todo item.
     */
    fun update(todo: HomeModal) {
        _isLoading.value = NetworkResponse.Loading;
        viewModelScope.launch {
            try {
                val res = homeApi.update(todo.id,todo)
                if(res.isSuccessful) {
                    _todos.update { currentTodo ->
                        if(currentTodo is NetworkResponse.Success) {
                            val  updateData = currentTodo.data.map { if(it.id == todo.id) todo else it }
                            NetworkResponse.Success(updateData)
                        }else {
                            currentTodo
                        }
                    }
                    _isLoading.value = NetworkResponse.Success(true);
                    _navEvent.send(1)
                }
            }catch (e: Exception) {
                _isLoading.value = NetworkResponse.Error("Update Field");
                Log.i("APP_ERROR_ACTION", e.toString())
            }
        }
    }


    /**
     * Creates a new todo item by sending a POST request to the backend.
     *
     * - Sends the provided [CreatePostModal] data to the API using `homeApi.create()`.
     * - If the request is successful, appends the newly created todo item to the local `_todos` list.
     * - Updates the loading state and sends a navigation event signal upon success.
     * - Handles any exceptions and sets an error state if the request fails.
     *
     * @param todo The [CreatePostModal] object containing the data for the new todo item.
     */
    fun create (todo: CreatePostModal) {
        _isLoading.value = NetworkResponse.Loading;
        viewModelScope.launch {
            try {
                val res = homeApi.create(todo)
                if(res.isSuccessful) {
                    _todos.update { currentTodo ->
                        if(currentTodo is NetworkResponse.Success) {
                            val  updateData = currentTodo.data + res.body()!!
                            NetworkResponse.Success(updateData)
                        }else {
                            currentTodo
                        }
                    }
                    _isLoading.value = NetworkResponse.Success(true);
                    _navEvent.send(1)
                }
            }catch (e: Exception) {
                _isLoading.value = NetworkResponse.Error("Update Field");
                Log.i("APP_ERROR_ACTION", e.toString())
            }
        }
    }
}