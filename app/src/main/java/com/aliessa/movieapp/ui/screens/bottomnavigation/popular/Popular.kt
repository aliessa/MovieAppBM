package com.aliessa.movieapp.ui.screens.bottomnavigation.popular

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.aliessa.movieapp.data.model.GenreId
import com.aliessa.movieapp.data.model.moviedetail.Genre
import com.aliessa.movieapp.ui.component.MovieItemList

@Composable
fun Popular(
    navController: NavController,
    genres: ArrayList<Genre>? = null,
) {
    val popularViewModel = hiltViewModel<PopularViewModel>()
    MovieItemList(
        navController = navController,
        movies = popularViewModel.popularMovies,
        genres = genres,
        selectedName = popularViewModel.selectedGenre.value
    ){
        popularViewModel.filterData.value = GenreId(it?.id.toString())
        it?.let {
            popularViewModel.selectedGenre.value = it
        }
    }
}