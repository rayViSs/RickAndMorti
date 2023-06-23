package com.example.rickmortyapi.ui.main.characters

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.rickmortyapi.models.characters.Result
import com.example.rickmortyapi.repository.CharactersRepository

class CharactersPagingSource: PagingSource<Int, com.example.rickmortyapi.models.characters.Result>() {

    private val repository = CharactersRepository()

    override fun getRefreshKey(state: PagingState<Int, Result>): Int? = 1

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {
        val page = params.key?: 1

        return kotlin.runCatching {
            repository.getData(page)
        }.fold(
            onSuccess = {
                LoadResult.Page(
                    data = it.results,
                    prevKey = null,
                    nextKey = if (it.results.isEmpty()) null else page+1
                )
            },
            onFailure = {LoadResult.Error(it)}
        )
    }
}