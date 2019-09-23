package com.example.staggeredgridlayoutsample.staggergridsdk;

import com.example.staggeredgridlayoutsample.response.DashboardResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface APIInterface {

    @GET("/bins/dzxgl")
    Call<DashboardResponse> getDashboardItems();
}
