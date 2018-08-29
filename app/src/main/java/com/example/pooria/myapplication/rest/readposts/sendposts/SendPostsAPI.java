package com.example.pooria.myapplication.rest.readposts.sendposts;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SendPostsAPI {

    private static final String BaseUrl = "http://192.168.1.10/imageupload/";
    private static Retrofit retrofit;
    public static Retrofit getApClient(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        OkHttpClient client = new OkHttpClient();
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BaseUrl).
                    addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
