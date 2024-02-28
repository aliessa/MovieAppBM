package com.aliessa.movieapp.ui.screens.bottomnavigation.popular


import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.aliessa.movieapp.data.model.GenreId
import com.aliessa.movieapp.data.model.moviedetail.Genre
import com.aliessa.movieapp.data.repository.MovieRepository
import com.aliessa.movieapp.utils.AppConstant
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PopularViewModel @Inject constructor(val repo: MovieRepository) : ViewModel() {
    var selectedGenre: MutableState<Genre> =
        mutableStateOf(Genre(null, AppConstant.DEFAULT_GENRE_ITEM))
    val filterData = MutableStateFlow<GenreId?>(null)

    @OptIn(ExperimentalCoroutinesApi::class)
    val popularMovies = filterData.flatMapLatest {
        repo.popularPagingDataSource(it?.genreId)
    }.cachedIn(viewModelScope)
}