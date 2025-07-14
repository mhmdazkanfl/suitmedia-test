package com.nakersolutionid.suitmedia.ui.screen.userselect

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.nakersolutionid.suitmedia.data.remote.response.DataItem
import com.nakersolutionid.suitmedia.di.networkModule
import com.nakersolutionid.suitmedia.di.repositoryModule
import com.nakersolutionid.suitmedia.di.viewModelModule
import com.nakersolutionid.suitmedia.ui.component.common.MyTopAppBar
import com.nakersolutionid.suitmedia.ui.component.userselect.EmptyStateScreen
import com.nakersolutionid.suitmedia.ui.component.userselect.ErrorStateScreen
import com.nakersolutionid.suitmedia.ui.component.userselect.LoadingStateScreen
import com.nakersolutionid.suitmedia.ui.component.userselect.UserItem
import com.nakersolutionid.suitmedia.ui.theme.SuitmediaTheme
import org.koin.androidx.compose.koinViewModel
import org.koin.compose.KoinApplicationPreview

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserSelectScreen(
    modifier: Modifier = Modifier,
    viewModel: UserSelectViewModel = koinViewModel(),
    onNavigationBack: () -> Unit,
    onUserClick: (DataItem) -> Unit
) {
    val pagingUsers = viewModel.pagingUsers.collectAsLazyPagingItems()
    var isRefreshing by remember { mutableStateOf(false) }
    var firstLoad by rememberSaveable { mutableStateOf(true) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            Column {
                MyTopAppBar(title = "Third Screen", onNavigationBack = onNavigationBack)
                HorizontalDivider(color = Color.LightGray.copy(alpha = 0.5f))
            }
        }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = isRefreshing,
            onRefresh = { pagingUsers.refresh() },
            modifier = Modifier
                .padding(paddingValues)
        ) {
            LazyColumn(
                modifier = modifier.fillMaxSize(),
                contentPadding = PaddingValues(16.dp)
            ) {
                when {
                    // Loading state
                    pagingUsers.loadState.refresh is LoadState.Loading && firstLoad -> {
                        item { LoadingStateScreen(modifier = Modifier.fillParentMaxSize()) }
                        firstLoad = false
                        isRefreshing = false
                    }

                    // Error state
                    pagingUsers.loadState.refresh is LoadState.Error -> {
                        val error = pagingUsers.loadState.refresh as LoadState.Error
                        isRefreshing = false
                        item {
                            ErrorStateScreen(
                                error.error.message ?: "Error occur",
                                modifier = Modifier.fillParentMaxSize()
                            )
                        }
                    }

                    // Success but empty
                    pagingUsers.loadState.refresh is LoadState.NotLoading && pagingUsers.itemCount == 0 -> {
                        isRefreshing = false
                        item { EmptyStateScreen(modifier = Modifier.fillParentMaxSize()) }
                    }

                    // Success with data
                    pagingUsers.loadState.refresh is LoadState.NotLoading && pagingUsers.itemCount > 0 -> {
                        isRefreshing = false

                        items(
                            count = pagingUsers.itemCount,
                            key = pagingUsers.itemKey { it.id },
                        ) { index ->
                            val user = pagingUsers[index]
                            if (user != null) {
                                UserItem(
                                    data = user,
                                    modifier = Modifier.animateItem(),
                                    onUserClick = onUserClick
                                )
                            }

                            if (index < pagingUsers.itemCount - 1) {
                                HorizontalDivider(
                                    Modifier.fillMaxWidth(),
                                    color = Color.LightGray.copy(alpha = 0.5f)
                                )
                            }
                        }

                        // Handle append loading
                        if (pagingUsers.loadState.append is LoadState.Loading) {
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    CircularProgressIndicator()
                                }
                            }
                        }

                        // Handle append error
                        if (pagingUsers.loadState.append is LoadState.Error) {
                            val error = pagingUsers.loadState.append as LoadState.Error
                            item {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = error.error.message ?: "Error loading more items",
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun UserSelectScreenPreview() {
    KoinApplicationPreview(application = {
        modules(
            networkModule,
            viewModelModule,
            repositoryModule
        )
    }) {
        SuitmediaTheme {
            UserSelectScreen(onNavigationBack = {}, onUserClick = {})
        }
    }
}