package com.nakersolutionid.suitmedia.data.remote

import androidx.paging.LoadState
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.nakersolutionid.suitmedia.BuildConfig
import com.nakersolutionid.suitmedia.data.remote.network.ApiServices
import com.nakersolutionid.suitmedia.data.remote.response.DataItem
import retrofit2.HttpException
import java.io.IOException

class UserPagingSource(
    val apiServices: ApiServices
) : PagingSource<Int, DataItem>() {
    override suspend fun load(
        params: LoadParams<Int>
    ): LoadResult<Int, DataItem> {
        try {
            // Start refresh at page 1 if undefined.
            val nextPageNumber = params.key ?: 1
            val response = apiServices.getUsers(BuildConfig.API_KEY, nextPageNumber)
            val totalPage = response.totalPages
            val page = response.page
            val nextKey = if (totalPage - page == 0) {
                null
            } else {
                page + 1
            }

            return LoadResult.Page(
                data = response.data,
                prevKey = null, // Only paging forward.
                nextKey = nextKey
            )
        } catch (e: IOException) {
            // IOException for network failures.
            return LoadResult.Error(e)
        } catch (e: HttpException) {
            // HttpException for any non-2xx HTTP status codes.
            return LoadResult.Error(e)
        } catch (e: Exception) {
            // Handle errors in this block and return LoadResult.Error for
            // expected errors (such as a network failure).
            return LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, DataItem>): Int? {
        // Try to find the page key of the closest page to anchorPosition from
        // either the prevKey or the nextKey; you need to handle nullability
        // here.
        //  * prevKey == null -> anchorPage is the first page.
        //  * nextKey == null -> anchorPage is the last page.
        //  * both prevKey and nextKey are null -> anchorPage is the
        //    initial page, so return null.
        // return state.anchorPosition?.let { anchorPosition ->
        //     val anchorPage = state.closestPageToPosition(anchorPosition)
        //     anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        // }
        return null
    }
}