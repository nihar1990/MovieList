package com.nihar.movielist;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieRepository {
    private ApiService apiService;

    public MovieRepository(ApiService apiService) {
        this.apiService = apiService;
    }

    //Fetch Data
    public MutableLiveData<List<Result>> getTop10Movies(String apiKey) {
        MutableLiveData<List<Result>> moviesData = new MutableLiveData<>();

        apiService.getTopRatedMovies(apiKey, 1, "en-US", "US").enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.body().getResults() != null
                        && response.body().getResults().size() >= 10) {
                    List<Result> top10Results = response.body().getResults().subList(0, 10);
                    moviesData.setValue(top10Results);
                } else {
                    moviesData.setValue(new ArrayList<>());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                moviesData.setValue(new ArrayList<>());
            }
        });

        return moviesData;
    }


}
