package com.example.pooria.myapplication.adapter;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooria.myapplication.R;
import com.example.pooria.myapplication.main.ShowProfileActivity;
import com.example.pooria.myapplication.model.GetPostsModel;
import com.example.pooria.myapplication.rest.readposts.getposts.MainActivity;
import com.example.pooria.myapplication.rest.readposts.getposts.MainScreenActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
    private int counter_like,counter_seen;
    private List<GetPostsModel> list;
    private Context context;
    public ImageView post;

    private String url = "\"http://192.168.1.10/\"";


    public CustomAdapter(Context context, List<GetPostsModel> list) {
        this.context=context;
        this.list=list;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_row_layoutactivity, parent, false);

        return new MyViewHolder(v);
    }



    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {

        Picasso.get().load("http://192.168.1.10/"+list.get(position).getImageUrl()).into(post);
        holder.like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("if", String.valueOf(list.get(position).getId()));
            }
        });


    }
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView seen;
        private ImageView like;
        private ImageView comment;
        private TextView likes;
        @RequiresApi(api = Build.VERSION_CODES.M)
        public MyViewHolder(View itemView) {
            super(itemView);
            counter_like=0;
            counter_seen=0;
            seen = (TextView) itemView.findViewById(R.id.txt_seen);
            like = (ImageView) itemView.findViewById(R.id.img_like);
            comment= (ImageView) itemView.findViewById(R.id.img_comment);
            post= (ImageView) itemView.findViewById(R.id.img_post);
            likes = itemView.findViewById(R.id.txt_like);
            like.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter_like++;
                    likes.setText(String.valueOf(counter_like));
                }
            });
            seen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    counter_seen++;
                    seen.setText(String.valueOf(counter_seen));
                }
            });

            post.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }
}
