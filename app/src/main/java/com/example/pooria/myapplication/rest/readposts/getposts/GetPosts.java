package com.example.pooria.myapplication.rest.readposts.getposts;

import com.example.pooria.myapplication.model.GetPostsModel;
import com.example.pooria.myapplication.model.SendPostsModel;
import com.example.pooria.myapplication.model.SignUpModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GetPosts {
    //get post in main page
    @GET("/ShowPosts.php")
    Call<List<GetPostsModel>> GettingPosts();

    @FormUrlEncoded
    @POST("/upload.php")
    Call<SendPostsModel> uploadImage(@Field("title") String title, @Field("image") String image);

    @FormUrlEncoded
    @POST("/signup.php")
    Call<SignUpModel> signup(@Field("email") String email, @Field("password") String password);

}
