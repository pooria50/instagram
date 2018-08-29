package com.example.pooria.myapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pooria.myapplication.R;
import com.example.pooria.myapplication.main.ShowProfileSecondActivity;
import com.example.pooria.myapplication.model.GetPostsModel;
import com.example.pooria.myapplication.rest.readposts.getposts.GetPosts;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ShowPostsProfileSecondAdapter extends  RecyclerView.Adapter<ShowPostsProfileSecondAdapter.MyHolder> {
    private Context context;
    private List<GetPostsModel> models ;
    private ImageView img;

    public ShowPostsProfileSecondAdapter(Context context, List<GetPostsModel> list) {
        this.context = context;
        this.models = list;
    }
    @NonNull
    @Override

    public ShowPostsProfileSecondAdapter.MyHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_row_layoutsecondactivity, viewGroup, false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ShowPostsProfileSecondAdapter.MyHolder myHolder, int i) {
        //myHolder.post_id.setText(models.get(i).getId().toString());
        myHolder.post_title.setText(models.get(i).getTitle().toString());
        Picasso.get().load("http://192.168.1.10/"+models.get(i).getImageUrl()).into(img);
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        private TextView post_id,post_title;
        public MyHolder(@NonNull View itemView) {
            super(itemView);
            post_id = itemView.findViewById(R.id.post_id);
            post_title = itemView.findViewById(R.id.post_title);
            img = itemView.findViewById(R.id.img);
        }
    }

}
