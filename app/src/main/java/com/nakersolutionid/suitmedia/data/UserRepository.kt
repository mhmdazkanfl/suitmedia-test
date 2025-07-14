package com.nakersolutionid.suitmedia.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.nakersolutionid.suitmedia.data.remote.UserPagingSource
import com.nakersolutionid.suitmedia.data.remote.network.ApiServices
import com.nakersolutionid.suitmedia.data.remote.response.DataItem
import kotlinx.coroutines.flow.Flow

class UserRepository(private val apiServices: ApiServices) {
    fun getUsers(): Flow<PagingData<DataItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 5,
                prefetchDistance = 1,
                initialLoadSize = 5
            ),
            pagingSourceFactory = {
                UserPagingSource(apiServices)
            }
        ).flow
    }
}