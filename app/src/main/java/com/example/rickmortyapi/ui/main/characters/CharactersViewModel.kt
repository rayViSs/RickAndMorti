package com.example.rickmortyapi.ui.main.characters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.rickmortyapi.models.characters.Result
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CharactersViewModel() : ViewModel() {

    val pagedCharacters: Flow<PagingData<Result>> = Pager(
        config = PagingConfig(pageSize = 5),
        pagingSourceFactory = { CharactersPagingSource() }
    ).flow.cachedIn(viewModelScope)


    private val _characterDetailsFlow = Channel<com.example.rickmortyapi.models.characters.Result>()
    val characterDetailsFlow = _characterDetailsFlow.receiveAsFlow()

    fun onItemClick(item: com.example.rickmortyapi.models.characters.Result) {
        viewModelScope.launch {
            _characterDetailsFlow.send(item)
        }
    }
}