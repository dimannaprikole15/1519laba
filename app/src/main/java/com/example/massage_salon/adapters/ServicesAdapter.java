package com.example.massage_salon.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massage_salon.R;
import com.example.massage_salon.RecordActivity;
import com.example.massage_salon.data.Orders;
import com.example.massage_salon.data.Services;
import com.example.massage_salon.data.Users;

import java.util.List;

public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {
   LayoutInflater inflater;
   Users user;
   List<Services> services;

   public ServicesAdapter(List<Services> services, Users user, Context context){
       this.services = services;
       this.user = user;
       this.inflater = LayoutInflater.from(context);
   }


    @NonNull
    @Override
    public ServicesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.services_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ServicesAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
       Services servise = services.get(position);
       holder.id.setText(String.valueOf(servise.getId()));
       holder.name.setText(servise.getName());
       holder.price.setText(String.valueOf(servise.getPrice()));
       holder.name.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Services selectedService = services.get(position);
               Intent intent = new Intent(view.getContext(), RecordActivity.class);
               intent.putExtra("name", selectedService.getName());
               intent.putExtra("id", selectedService.getId());
               intent.putExtra("userId", user.getId());
               view.getContext().startActivity(intent);
           }
       });
    }

    @Override
    public int getItemCount() {
        return  services.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView id, name, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.serviceId);
            name = itemView.findViewById(R.id.nameServise);
            price = itemView.findViewById(R.id.servicePrice);
        }
    }
}
