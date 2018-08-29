package com.example.pooria.myapplication.rest.readposts.getposts;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.pooria.myapplication.R;
import com.example.pooria.myapplication.adapter.CustomAdapter;
import com.example.pooria.myapplication.adapter.StoryAdapter;
import com.example.pooria.myapplication.main.ShowStoryDetailActivity;
import com.example.pooria.myapplication.model.GetPostsModel;
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

public class ShowPostsActivity extends android.support.v4.app.Fragment implements StoryAdapter.OnItemclikListener {

    public static final String EXTRA_URL = "imageUrl";
    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECTED_PIC = 1;
    private RecyclerView recyclerView,recyclerView2;
    private CustomAdapter adapter;
    private StoryAdapter storyAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<GetPostsModel> items = new ArrayList<>();
    private Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_show_posts, container, false);
        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);
        recyclerView2 = (RecyclerView) rootView.findViewById(R.id.recyclerView2);

        return rootView;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
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
                final List<GetPostsModel> body = response.body();
                for (int i=0;i<body.size();i++) {
                    Log.d("log"," id :" + body.get(i).getId());
                    Log.d("log"," title :" + body.get(i).getTitle());
                    Log.d("log"," image_url :" + body.get(i).getImageUrl());
                    Log.d("log", " seen :"+body.get(i).getSeen());
                    adapter = new CustomAdapter(context, items);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
                    GetPostsModel model = new GetPostsModel();
                    model.setTitle(body.get(i).getTitle());
                    model.setImageUrl(body.get(i).getImageUrl());
                    model.setSeen(body.get(i).getSeen());
                    items.add(model);
                    storyAdapter = new StoryAdapter(items, context);
                    recyclerView2.setHasFixedSize(true);
                    recyclerView2.setLayoutManager(new LinearLayoutManager(context, LinearLayout.HORIZONTAL, false));

                }
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                storyAdapter.notifyDataSetChanged();
                recyclerView2.setAdapter(storyAdapter);
                storyAdapter.setOnItemClickListener(ShowPostsActivity.this);
            }
            @Override
            public void onFailure(Call<List<GetPostsModel>> call, Throwable t) {
                Log.d("log",t.toString());

            }
        });
    }

    @Override
    public void onItemClik(int position) {
        Intent intent = new Intent(getContext(), ShowStoryDetailActivity.class);
        GetPostsModel model = items.get(position);
        intent.putExtra(EXTRA_URL, model.getImageUrl());
        startActivity(intent);
    }
   /* @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SELECTED_PIC:
                if (resultCode == RESULT_OK) {
                    Uri uri = data.getData();
                    String[] projection = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(projection[0]);
                    String filepath = cursor.getString(columnIndex);
                    cursor.close();

                    Bitmap bitmap = BitmapFactory.decodeFile(filepath);
                    Drawable drawable = new BitmapDrawable(bitmap);
                    // date in drawable .// show in recycler fuuture
                }
                break;
            default:
                break;
        }
    }*/
}
