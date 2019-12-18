package com.stiki.mangab.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Api api;
    private static HttpLoggingInterceptor httpLoggingInterceptor;
    private static OkHttpClient okHttpClient;

    public static Api getClient() {
        if (api == null) {
            Retrofit retrofit;

            httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(httpLoggingInterceptor)
                    .build();

            Gson builder = new GsonBuilder().setLenient().create();

            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create(builder))
                    .client(okHttpClient)
                    .baseUrl("http://hiyahiyahiya.xyz/mangab/api/")
                    .build();

            api = retrofit.create(Api.class);
        }
        return api;
    }
}
