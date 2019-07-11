package com.nitrr.ecell.esummit.ecellapp.restapi;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {

    private static Retrofit retrofit = null;
    private static String BASE_URL = "https://c04c3c6b.ngrok.io/";

    public static APIServices getRetrofitInstance(){
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(getClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
            return retrofit.create(APIServices.class);
    }

    private static OkHttpClient getClient(){
        return new OkHttpClient()
                .newBuilder()
                .callTimeout(10, TimeUnit.SECONDS)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5, TimeUnit.SECONDS)
                .build();
        }
}
