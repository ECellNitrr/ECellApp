package com.nitrr.ecell.esummit.ecellapp.restapi;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppClient {

    private static Retrofit retrofit=null;
    private static String BASE_URL="http://jsonplaceholder.typicode.com";

    public static Retrofit getRetrofitInstance(){
        if(retrofit==null){
            retrofit= new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }


}
