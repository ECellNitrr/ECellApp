package com.nitrr.ecell.esummit.ecellapp.restapi;


import android.app.Activity;
import android.util.Log;

import com.google.gson.GsonBuilder;
import com.nitrr.ecell.esummit.ecellapp.BuildConfig;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {
    private static AppClient mInstance;

    private AppClient() {
    }

    public static synchronized AppClient getInstance() {
        if (mInstance == null) mInstance = new AppClient();
        return mInstance;
    }

    public <S> S createService(Class<S> serviceClass) {
        OkHttpClient.Builder httpClient = getOKHttpClient();
        OkHttpClient client = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    public <S> S createServiceWithAuth(Class<S> serviceClass, Activity activity) {
        Interceptor interceptorReq = chain -> {
            Request request = chain.request().newBuilder().build();
            return chain.proceed(request);
        };

        OkHttpClient.Builder httpClient = getOKHttpClient();
        httpClient.addInterceptor(interceptorReq);
        OkHttpClient okHttpClient = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    public <S> S createService(Class<S> serviceClass, String URL){
        Interceptor interceptorReq = chain -> {
            Request request = chain.request().newBuilder().build();
            return chain.proceed(request);
        };

        OkHttpClient.Builder httpClient = getOKHttpClient();
        httpClient.addInterceptor(interceptorReq);
        OkHttpClient okHttpClient = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BQUIZ_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .build();

        return retrofit.create(serviceClass);
    }


    private OkHttpClient.Builder getOKHttpClient() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(httpLoggingInterceptor);
        }

        httpClient.connectTimeout(15, TimeUnit.SECONDS);
        httpClient.writeTimeout(15, TimeUnit.SECONDS);
        httpClient.readTimeout(15, TimeUnit.SECONDS);

        return httpClient;
    }

}