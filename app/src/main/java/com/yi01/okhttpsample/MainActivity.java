package com.yi01.okhttpsample;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.yi01.okhttpsample.github.Authorization;

import java.io.IOException;
import java.util.List;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Request request = new Request.Builder()
                .get()
                .header("X-HOGE-API-TOKEN", "tokentokentoken")
                .url("http://kodama2222.dip.jp/userdir/")
                .build();

        OkHttpClient client = OkHttpHelper.getClient(getApplicationContext());
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Log.e("OkHttpSample", "err", e);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                Log.d("OkHttpSample", response.body().string());
            }
        });

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .create();

        GitHubAPI api = new Retrofit.Builder()
                .baseUrl("https://api.github.com")
                .converterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(GitHubAPI.class);

        api.getAuthorization().enqueue(new retrofit.Callback<List<Authorization>>() {
            @Override
            public void onResponse(retrofit.Response<List<Authorization>> response) {
                if(response.isSuccess()) {
                    for (Authorization a : response.body()) {
                        Log.d("hogehogehoge", "name, clientID = " + a.app.name + "," + a.app.clientId);
                    }
                }
                else try {
                    Log.d("hogehogehoge", response.errorBody().string());
                } catch (IOException e) {
                    Log.e("hogehogehoge", "error", e);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                Log.e("hogehogehoge", "error", t);
            }
        });
    }
}
