package com.example.pooria.myapplication.utils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooria.myapplication.R;

public class row_layoutactivity extends AppCompatActivity {

    private ImageView img_post,img_comment,img_like;
    private EditText data_comment;
    private TextView txt_like,txt_seen,txt_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_row_layoutactivity);
        bind();
    }
    void bind(){
        img_post = findViewById(R.id.img_post);
        img_like = findViewById(R.id.img_like);
        txt_seen = findViewById(R.id.txt_seen);
        data_comment = findViewById(R.id.edt_comment);
        txt_like = findViewById(R.id.txt_like);
        img_comment = findViewById(R.id.img_comment);
        txt_user = findViewById(R.id.txt_user);
    }
}
