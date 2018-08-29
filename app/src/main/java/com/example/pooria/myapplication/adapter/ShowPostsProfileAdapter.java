package com.example.pooria.myapplication.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pooria.myapplication.R;
import com.example.pooria.myapplication.model.GetPostsModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShowPostsProfileAdapter extends RecyclerView.Adapter<ShowPostsProfileAdapter.MyViewHolder> {
    private List<GetPostsModel> list;
    private Context context;
    public ImageView show;

    public ShowPostsProfileAdapter(Context context, List<GetPostsModel> list) {
        this.context=context;
        this.list=list;
    }


    @Override
    public ShowPostsProfileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_row_layoutprofileactivity, parent, false);
        return new MyViewHolder(v);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull ShowPostsProfileAdapter.MyViewHolder holder, int position) {
        Picasso.get().load("http://192.168.1.10/"+list.get(position).getImageUrl()).into(show);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        @RequiresApi(api = Build.VERSION_CODES.M)
        public MyViewHolder(View itemView) {
            super(itemView);
            show =  itemView.findViewById(R.id.img_);
            show.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context, "Saklam", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

}
