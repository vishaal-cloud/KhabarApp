package com.example.khabarapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements CategoryAdapter.CategoryClickInterface {
    private RecyclerView newsRv, categoryRv;
    private ArrayList<Articles> articlesArrayList=new ArrayList<>();
    private ArrayList<categoryModal> categoryModalArrayList=new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    private NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        newsRv = findViewById(R.id.vertical);
        categoryRv = findViewById(R.id.horizontal);
        newsAdapter = new NewsAdapter(articlesArrayList,this);
        categoryAdapter = new CategoryAdapter(categoryModalArrayList, this, this::onCategoryClick);
        newsRv.setLayoutManager(new LinearLayoutManager(this));
        newsRv.setAdapter(newsAdapter);
        categoryRv.setAdapter(categoryAdapter);
        getCategories();
        getNews("All");

        newsAdapter.notifyDataSetChanged();


    }

    private void getCategories() {
        categoryModalArrayList.add(new categoryModal("All", "https://images.unsplash.com/photo-1508051123996-69f8caf4891d?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=751&q=80"));
        categoryModalArrayList.add(new categoryModal("Technology", "https://images.unsplash.com/photo-1624571409108-e9a41746af53?ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=750&q=80"));
        categoryModalArrayList.add(new categoryModal("Sports", "https://images.unsplash.com/photo-1575052814086-f385e2e2ad1b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8eW9nYXxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryModalArrayList.add(new categoryModal("General", "https://images.unsplash.com/photo-1494059980473-813e73ee784b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Z2VuZXJhbHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryModalArrayList.add(new categoryModal("Buisness", "https://images.unsplash.com/flagged/photo-1576485436509-a7d286952b65?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8YnVpc25lc3N8ZW58MHx8MHx8&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryModalArrayList.add(new categoryModal("Entertainment", "https://images.unsplash.com/photo-1470229722913-7c0e2dbbafd3?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryModalArrayList.add(new categoryModal("Health", "https://images.unsplash.com/photo-1494059980473-813e73ee784b?ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8Z2VuZXJhbHxlbnwwfHwwfHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=500&q=60"));
        categoryAdapter.notifyDataSetChanged();

    }


    private void getNews(String category) {
          articlesArrayList.clear();
        String categoryURL = "https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=5e1543cc1f7a45f9bf7110bc4efe7438";
        String url = "https://newsapi.org/v2/top-headlines?country=in&excludeDomains=Stackoverflow.com&sortBy=publishedAt&language=en&apiKey=5e1543cc1f7a45f9bf7110bc4efe7438";
        String BASE_URL = "https://newsapi.org/";
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).
                addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if (category.equals("All"))
            call = retrofitAPI.getAllNews(url);
        else
            call = retrofitAPI.getNewsByCategory(categoryURL);

        call.enqueue(new Callback<NewsModal>() {
            @Override
             public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
               NewsModal newsModal=response.body();
                ArrayList<Articles> articles= newsModal.getArticles();
                for(int i=1;i<articles.size();i++)
                  {
                         articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription() ,
                                 articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));
                  }
                 newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error in loading news", Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public void onCategoryClick(int position) {
        String category= categoryModalArrayList.get(position).getCategory();
        getNews(category);
    }

}

