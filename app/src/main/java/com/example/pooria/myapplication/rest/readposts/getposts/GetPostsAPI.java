package com.example.pooria.myapplication.rest.readposts.getposts;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetPostsAPI {
    public static final String BASE_URL = "http://192.168.1.10/";
    private static Retrofit retrofit;
    public static Retrofit getClient(){
        /*Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient();*/
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
