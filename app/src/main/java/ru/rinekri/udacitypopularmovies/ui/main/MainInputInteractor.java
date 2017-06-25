package ru.rinekri.udacitypopularmovies.ui.main;

import android.support.annotation.NonNull;

import java.util.List;

import ru.rinekri.udacitypopularmovies.network.models.MovieInfo;
import ru.rinekri.udacitypopularmovies.network.services.MainServiceApi;
import ru.rinekri.udacitypopularmovies.ui.base.models.MovieSortType;
import ru.rinekri.udacitypopularmovies.ui.base.SyncInteractor;

class MainInputInteractor implements SyncInteractor<MovieSortType, MainMvp.PM> {

  private MainServiceApi mainServiceApi;

  MainInputInteractor(MainServiceApi mainServiceApi) {
    this.mainServiceApi = mainServiceApi;
  }

  @Override
  public MainMvp.PM getData(@NonNull MovieSortType type) throws Exception {
    List<MovieInfo> movies = null;

    switch (type) {
      case TopRated:
        movies = mainServiceApi.getTopRatedMovies().execute().body().results();
        break;
      case Popular:
        movies = mainServiceApi.getPopularMovies().execute().body().results();
        break;
      case Favorites: {
        //TODO: Add logic to load favorites movies from db
        break;
      }
    }
    return MainMvp.PM.create(movies);
  }
}