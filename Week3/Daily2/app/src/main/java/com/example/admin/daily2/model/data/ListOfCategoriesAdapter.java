package com.example.admin.daily2.model.data;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.admin.daily2.R;

import java.util.List;

public class ListOfCategoriesAdapter extends ArrayAdapter<Category> {
    public ListOfCategoriesAdapter(@NonNull Context context, int resource, @NonNull List<Category> objects) {
        super(context, resource, objects);


    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView==null){
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_categories,null);
        }

        TextView tvCategory = convertView.findViewById(R.id.tvCategory);
        Category category=getItem(position);
        tvCategory.setText(category.getName());

        return convertView;
    }
}
