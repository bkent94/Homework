package com.example.admin.daily1;

import android.net.Uri;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private List<Parcelable> objectList;

    public MyRecyclerViewAdapter(List<Parcelable> objectList){
        this.objectList=objectList;
    }

    @NonNull
    @Override
    public MyRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_layout,parent,false);




        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyRecyclerViewAdapter.ViewHolder viewHolder, int position) {

      Object object=objectList.get(position);
      String field1Str="";
      String field2Str="";
      String field3Str="";
      String field4Url="";


      switch (viewHolder.getItemViewType()){
          case 1:
              MyIntentService.Person person=(MyIntentService.Person) object;
              field1Str=person.getName();
              field4Url=person.getImage();
              break;
          case 2:
              MyIntentService.Book book = (MyIntentService.Book) object;
              field1Str=book.getISBN();
              field4Url=book.getImage();
              break;
          case 3:
              MyIntentService.Car car= (MyIntentService.Car) object;
              field1Str=car.getMake();
              field4Url=car.getImage();
              break;
          case 4:
              MyIntentService.Cat cat= (MyIntentService.Cat) object;
              field1Str=cat.getBreed();
              field4Url=cat.getImage();
              break;

      }

      viewHolder.field1.setText(field1Str);
        viewHolder.field2.setText(field2Str);
        viewHolder.field3.setText(field3Str);
        viewHolder.field4.setImageURI(Uri.parse(field4Url));





    }

    @Override
    public int getItemViewType(int position) {
        Object object=objectList.get(position);

       if(object instanceof MyIntentService.Person){
           return 1;
       }

        if(object instanceof MyIntentService.Book){
            return 2;
        }
        if(object instanceof MyIntentService.Car){
            return 3;
        }

        if(object instanceof MyIntentService.Cat){
            return 4;
        }


       return 0;
    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView field1;
        private TextView field2;
        private TextView field3;
        private ImageView field4;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            field1= itemView.findViewById(R.id.field1);
            field2=itemView.findViewById(R.id.field2);
            field3=itemView.findViewById(R.id.field3);
            field4=itemView.findViewById(R.id.field4);
        }
    }
}
