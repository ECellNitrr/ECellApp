package com.nitrr.ecell.esummit.ecellapp.restapi;


import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.nitrr.ecell.esummit.ecellapp.BuildConfig;
import com.nitrr.ecell.esummit.ecellapp.misc.SharedPref;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {

//<<<<<<< HEAD
//    private static AppClient mInstance;
//    private static SharedPref sharedPrefs;
//
//    private AppClient() {
//    }
//
//    public static synchronized AppClient getInstance() {
//        if (mInstance == null) mInstance = new AppClient();
//        return mInstance;
//    }
//
//    public <S> S createService(Class<S> serviceClass) {
//        OkHttpClient.Builder httpClient = getOKHttpClient();
//        OkHttpClient client = httpClient.build();
//
//        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl(BuildConfig.BASE_URL).client(client)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        return retrofit.create(serviceClass);
//    }
//
//    public <S> S createServiceWithAuth(Class<S> serviceClass, final AppCompatActivity activity) {
////        sharedPrefs = new SharedPref(activity);
//        Interceptor interceptorReq = new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request request = chain.request().newBuilder()
//                        .addHeader("authId", sharedPrefs.getAccessToken()).build();
//
//                Log.e("Header====",   sharedPrefs.getAccessToken()  );
//                return chain.proceed(request);
//            }
//        };
//
//        OkHttpClient.Builder httpClient = getOKHttpClient();
//        httpClient.addInterceptor(interceptorReq);
//        OkHttpClient okHttpClient = httpClient.build();
//
//        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl(BuildConfig.BASE_URL)
//                .client(okHttpClient)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        return retrofit.create(serviceClass);
//    }
//
//    private OkHttpClient.Builder getOKHttpClient() {
//        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
//
//        if (BuildConfig.DEBUG) {
//            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
//            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//            httpClient.addInterceptor(httpLoggingInterceptor);
//}
//        return httpClient;
    private static Retrofit retrofit=null;
    private static String BASE_URL="http://jsonplaceholder.typicode.com";

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
            return retrofit;
    }
}
