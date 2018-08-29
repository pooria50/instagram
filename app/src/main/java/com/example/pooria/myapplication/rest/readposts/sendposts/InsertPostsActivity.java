package com.example.pooria.myapplication.rest.readposts.sendposts;

import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.pooria.myapplication.R;
import com.example.pooria.myapplication.model.SendPostsModel;
import com.example.pooria.myapplication.rest.readposts.getposts.GetPosts;
import com.example.pooria.myapplication.rest.readposts.sendposts.SendPhoto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class InsertPostsActivity extends android.support.v4.app.Fragment {

    private Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_insert_posts, container, false);
        button = rootView.findViewById(R.id.btn_save);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SendPhoto.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

 /*   private void selectImage(){

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMG_REQUEST);
    }

    private String imageToString(){
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        byte[] imgByte = outputStream.toByteArray();
        return Base64.encodeToString(imgByte, Base64.DEFAULT);
    }

    private void uploadImage(){
        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10/imageupload/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        String Image = imageToString();
        String Title = editText_title.getText().toString();
        GetPosts posts = retrofit.create(GetPosts.class);
        Call<SendPostsModel> call = posts.uploadImage(Title, Image);
        call.enqueue(new Callback<SendPostsModel>() {
            @Override
            public void onResponse(Call<SendPostsModel> call, Response<SendPostsModel> response) {
                SendPostsModel sendPostsModel = response.body();
                Toast.makeText(getContext(), "Server Response"+ sendPostsModel.getResponse().toString(), Toast.LENGTH_SHORT).show();
                //Toast.makeText(SendPhoto.this, "Ookkk", Toast.LENGTH_SHORT).show();
                Log.d("test", "Ok");
            }

            @Override
            public void onFailure(Call<SendPostsModel> call, Throwable t) {
                Log.d("test", t.toString());
            }
        });
    }*/

   /* @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                imageView.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }*/

}
