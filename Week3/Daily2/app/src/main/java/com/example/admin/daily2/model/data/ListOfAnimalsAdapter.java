package com.example.admin.daily2.model.data;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.daily2.ListOfCategoriesActivity;
import com.example.admin.daily2.R;

import java.util.List;

public class ListOfAnimalsAdapter extends RecyclerView.Adapter<ListOfAnimalsAdapter.ViewHolder> {

    List<Animal> animalList;

    public ListOfAnimalsAdapter(List<Animal> animalList){
        this.animalList=animalList;
    }

    @NonNull
    @Override
    public ListOfAnimalsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_of_animals,parent,false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ListOfAnimalsAdapter.ViewHolder viewHolder, int position) {
        Animal animal=animalList.get(position);
        viewHolder.tvAnimalName.setText(animal.getName());
        viewHolder.tvAnimalCategory.setText(animal.getCategory());
        viewHolder.tvAnimalSound.setText(animal.getSound());
        viewHolder.tvAnimalDetails.setText(animal.getDetails());
        viewHolder.tvAnimalWeight.setText(animal.getWeight()+" grams");



    }

    @Override
    public int getItemCount() {
        return animalList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        private final TextView tvAnimalName;
        private final TextView tvAnimalCategory;
        private final TextView tvAnimalSound;
        private final TextView tvAnimalDetails;
        private final TextView tvAnimalWeight;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvAnimalName=itemView.findViewById(R.id.tvAnimalName);
            tvAnimalCategory=itemView.findViewById(R.id.tvAnimalCategory);
            tvAnimalSound=itemView.findViewById(R.id.tvAnimalSound);
            tvAnimalDetails=itemView.findViewById(R.id.tvAnimalDetails);
            tvAnimalWeight=itemView.findViewById(R.id.tvAnimalWeight);

        }
    }
}
