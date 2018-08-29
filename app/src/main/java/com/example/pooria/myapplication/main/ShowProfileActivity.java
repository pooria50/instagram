package com.example.pooria.myapplication.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.pooria.myapplication.R;
import com.example.pooria.myapplication.adapter.CustomAdapter;
import com.example.pooria.myapplication.adapter.ShowPostsProfileAdapter;
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

public class ShowProfileActivity extends android.support.v4.app.Fragment {
    private RecyclerView recyclerViews;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<GetPostsModel> items = new ArrayList<>();
    private Context context;
    private ShowPostsProfileAdapter adapter;
    private Button button;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_show_profile, container, false);
        recyclerViews = (RecyclerView) rootView.findViewById(R.id.recyclerViews);
        button=rootView.findViewById(R.id.btn_show);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ShowProfileSecondActivity.class);
                startActivity(intent);
            }
        });
        return rootView;
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                List<GetPostsModel> body = response.body();
                for (int i=0;i<body.size();i++) {
                    Log.d("log"," id :" + body.get(i).getId());
                    Log.d("log"," title :" + body.get(i).getTitle());
                    Log.d("log"," image_url :" + body.get(i).getImageUrl());
                    Log.d("log", " seen :"+body.get(i).getSeen());
                    adapter = new ShowPostsProfileAdapter(context,items);
                    recyclerViews.setHasFixedSize(true);
                    recyclerViews.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
                    GetPostsModel model = new GetPostsModel();
                    model.setTitle(body.get(i).getTitle());
                    model.setImageUrl(body.get(i).getImageUrl());
                    model.setSeen(body.get(i).getSeen());
                    items.add(model);

                }
                adapter.notifyDataSetChanged();
                recyclerViews.setAdapter(adapter);

            }
            @Override
            public void onFailure(Call<List<GetPostsModel>> call, Throwable t) {
                Log.d("log",t.toString());

            }
        });
    }

}
