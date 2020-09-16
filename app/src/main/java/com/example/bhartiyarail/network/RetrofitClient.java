package com.example.bhartiyarail.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    public RetrofitClient() {
    }

    private static Gson gson = new GsonBuilder()
                                    .setLenient()
                                    .create();

    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
            .addInterceptor(new MockInterceptor())
            .build();

    public static ApiInterface getRetrofit(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://railproject.000webhostapp.com/rail_api/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build();

        return retrofit.create(ApiInterface.class);
    }
}
