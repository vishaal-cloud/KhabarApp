package com.example.khabarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FullNews extends AppCompatActivity {
     String title,desc,content,imageUrl,url;
     private TextView titlenews,subtitlenews,contentnews;
     private ImageView newsimage;
     private AppCompatButton readnewsbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState)
     {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_news);

        title=getIntent().getStringExtra("title");
        desc=getIntent().getStringExtra("description");
        content=getIntent().getStringExtra("content");
        imageUrl=getIntent().getStringExtra("image");

        url=getIntent().getStringExtra("url");

        titlenews=findViewById(R.id.header);
        subtitlenews=findViewById(R.id.subheader);
        contentnews=findViewById(R.id.content);
        newsimage=findViewById(R.id.Newsimg);

        readnewsbutton=findViewById(R.id.readnews);

        titlenews.setText(title);
        subtitlenews.setText(desc);
        contentnews.setText(content);
         Picasso.get().load(imageUrl).fit().into(newsimage);
         //Working well
         readnewsbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Intent i=new Intent(Intent.ACTION_VIEW);
                 i.setData(Uri.parse(url));
                 startActivity(i);

             }
         });

    }
}