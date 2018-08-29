package com.example.pooria.myapplication.main;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.pooria.myapplication.R;
import com.example.pooria.myapplication.adapter.CustomAdapter;
import com.example.pooria.myapplication.adapter.ShowPostsProfileSecondAdapter;
import com.example.pooria.myapplication.adapter.StoryAdapter;
import com.example.pooria.myapplication.model.GetPostsModel;
import com.example.pooria.myapplication.rest.readposts.getposts.GetPosts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ShowProfileSecondActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<GetPostsModel> items = new ArrayList<>();
    private ShowPostsProfileSecondAdapter adapter;
    private LinearLayoutManager lm;
    private Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_profile_second);
        recyclerView = findViewById(R.id.recycle);
        recyclerView.setHasFixedSize(true);

        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.10/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GetPosts p = retrofit.create(GetPosts.class);
        Call<List<GetPostsModel>> call = p.GettingPosts();
        call.enqueue(new Callback<List<GetPostsModel>>() {
            @Override
            public void onResponse(Call<List<GetPostsModel>> call, Response<List<GetPostsModel>> response) {
                final List<GetPostsModel> body = response.body();
                for (int i=0;i<body.size();i++) {
                    Log.d("logg"," id :" + body.get(i).getId());
                    Log.d("logg"," title :" + body.get(i).getTitle());
                    Log.d("logg"," image_url :" + body.get(i).getImageUrl());
                    Log.d("logg", " seen :"+body.get(i).getSeen());
                    recyclerView.setHasFixedSize(true);
                    adapter= new ShowPostsProfileSecondAdapter(context, items);
                    lm= new LinearLayoutManager(ShowProfileSecondActivity.this, LinearLayoutManager.VERTICAL, false);
                    GetPostsModel model = new GetPostsModel();
                    model.setTitle(body.get(i).getTitle());
                    model.setImageUrl(body.get(i).getImageUrl());
                    model.setSeen(body.get(i).getSeen());
                    items.add(model);
                }
                adapter.notifyDataSetChanged();
                recyclerView.setLayoutManager(lm);
                recyclerView.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<GetPostsModel>> call, Throwable t) {
                Log.d("log",t.toString());

            }
        });
    }
}
