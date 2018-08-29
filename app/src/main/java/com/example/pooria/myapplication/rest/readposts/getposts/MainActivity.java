package com.example.pooria.myapplication.rest.readposts.getposts;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.LinearLayout;

import com.example.pooria.myapplication.R;
import com.example.pooria.myapplication.adapter.CustomAdapter;
import com.example.pooria.myapplication.model.GetPostsModel;
import com.example.pooria.myapplication.rest.readposts.sendposts.SendPhoto;
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

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_REQUEST = 1888;
    private static final int SELECTED_PIC = 1;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<GetPostsModel> items = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        OkHttpClient client = new OkHttpClient();
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.43.254/")
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
                    adapter = new CustomAdapter(MainActivity.this, items);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayout.VERTICAL, false));
                    GetPostsModel model = new GetPostsModel();
                    model.setTitle(body.get(i).getTitle());
                    model.setImageUrl(body.get(i).getImageUrl());
                    model.setSeen(body.get(i).getSeen());
                    items.add(model);
                }
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<GetPostsModel>> call, Throwable t) {
                Log.d("log",t.toString());

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.insert:
                Intent intent = new Intent(MainActivity.this, SendPhoto.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
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
    }
}
