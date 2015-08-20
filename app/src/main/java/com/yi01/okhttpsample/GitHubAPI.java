package com.yi01.okhttpsample;

import com.yi01.okhttpsample.github.Authorization;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;

public interface GitHubAPI {

    @GET("/authorizations")
    @Headers("Authorization: Bearer c093299aa438e1f78fbee27222214dae0f1191bc")
    Call<List<Authorization>> getAuthorization();

}
