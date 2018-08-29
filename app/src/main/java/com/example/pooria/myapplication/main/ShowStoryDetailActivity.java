package com.example.pooria.myapplication.main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pooria.myapplication.R;
import com.squareup.picasso.Picasso;

import static com.example.pooria.myapplication.rest.readposts.getposts.ShowPostsActivity.EXTRA_URL;

public class ShowStoryDetailActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_story_detail);
        Intent intent = getIntent();
        String imageUrl = intent.getStringExtra(EXTRA_URL);
        ImageView imageView = findViewById(R.id.img_pic);
        Picasso.get().load("http://192.168.1.10/"+imageUrl).fit().centerCrop().into(imageView);
    }
}
