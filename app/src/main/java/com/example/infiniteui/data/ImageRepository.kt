package com.example.infiniteui.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.infiniteui.data.paging.ImagePagingSource
import kotlinx.coroutines.flow.Flow

class ImageRepository {
    fun getImages(): Flow<PagingData<ImageItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 5,
                enablePlaceholders = false
            ),
            pagingSourceFactory = { ImagePagingSource() }
        ).flow
    }
}
