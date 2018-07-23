package com.example.admin.daily4.adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.admin.daily4.R;
import com.example.admin.daily4.model.Recipe;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    public static final String TAG=RecipeListAdapter.class.getSimpleName();

    List<Recipe> recipes;
    List<Bitmap> images;
    public RecipeListAdapter(List<Recipe> recipes,List<Bitmap> images){
        Log.d(TAG, "RecipeListAdapter: ");
        this.recipes=recipes;
        this.images=images;
    }

    @NonNull
    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d(TAG, "onCreateViewHolder: ");
      ImageView imageView= (ImageView) LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list,parent,false);

ViewHolder viewHolder =new ViewHolder(imageView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeListAdapter.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: RecipeImage:" + recipes.get(position).getImage());

        holder.imageView.setImageBitmap(images.get(position));


    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public int getItemCount() {
        Log.d(TAG, "getItemCount: ");
        return images.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
ImageView imageView;

        public ViewHolder(ImageView imageView){
            super(imageView);
            this.imageView=imageView;


        }
    }
}
