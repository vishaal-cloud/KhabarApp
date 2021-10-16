   package com.example.khabarapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private final ArrayList<Articles> articlesArrayList;
    private final Context context;

    public NewsAdapter(ArrayList<Articles> articlesArrayList, Context context) {
        this.articlesArrayList = articlesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_vertical,parent,false);
        return new NewsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
     Articles articles=articlesArrayList.get(position);
     holder.title.setText(articles.getTitle());
     holder.subtitle.setText(articles.getDescription());
     Picasso.get().load(articles.getUrlToImage()).fit().into(holder.newsimg);
     holder.itemView.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i= new Intent(context,FullNews.class);
        i.putExtra("title",articles.getTitle());
       i.putExtra("content",articles.getContent());
       i.putExtra("description",articles.getDescription());
        i.putExtra("image",articles.getUrlToImage());
        i.putExtra("url",articles.getUrl());
        context.startActivity(i);

    }
});

    }

    @Override
    public int getItemCount() {

        return articlesArrayList.size();
    }

    public    class ViewHolder extends RecyclerView.ViewHolder {
    private TextView title;
    private  TextView subtitle;
    private  ImageView newsimg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.header);
            subtitle=itemView.findViewById(R.id.subheader);
            newsimg=itemView.findViewById(R.id.Newsimg);

        }
    }
}
