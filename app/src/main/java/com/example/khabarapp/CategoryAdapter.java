package com.example.khabarapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class  CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder>{
    private ArrayList<categoryModal> categoryModals;
    private Context context;
    private CategoryClickInterface categoryClickInterface;

    public CategoryAdapter(ArrayList<categoryModal> categoryModals, Context context, CategoryClickInterface categoryClickInterface) {
        this.categoryModals = categoryModals;
        this.context = context;
        this.categoryClickInterface = categoryClickInterface;
    }



    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_horizontal,parent,false);
        return new CategoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.ViewHolder holder, int position) {
        categoryModal categoryModal=categoryModals.get(position);
        holder.categorytxt.setText(categoryModal.getCategory());
        Picasso.get().load(categoryModal.getCategoryImgUrl()).into(holder.categoryImg);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                categoryClickInterface.onCategoryClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {

        return categoryModals.size();
    }
    public interface CategoryClickInterface
    {
        void onCategoryClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         private TextView categorytxt;
         private ImageView categoryImg;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            categorytxt=itemView.findViewById(R.id.horizontaltxt);
            categoryImg=itemView.findViewById(R.id.horizontalimg);

        }
    }
}
