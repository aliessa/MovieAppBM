package com.aliessa.movieapp.ui.screens.bottomnavigation.nowplaying

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliessa.movieapp.data.model.GenreId
import com.aliessa.movieapp.data.model.moviedetail.Genre
import com.aliessa.movieapp.ui.component.MovieItemList

@Composable
fun NowPlaying(
    navController: NavController,
    genres: ArrayList<Genre>? = null,
) {
    val nowPlayViewModel = hiltViewModel<NowPlayingViewModel>()
    MovieItemList(
        navController = navController,
        movies = nowPlayViewModel.nowPlayingMovies,
        genres = genres,
        selectedName = nowPlayViewModel.selectedGenre.value
    ){
        nowPlayViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let {
            nowPlayViewModel.selectedGenre.value = it
        }
    }
}