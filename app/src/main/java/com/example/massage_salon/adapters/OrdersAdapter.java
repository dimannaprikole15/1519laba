package com.example.massage_salon.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.massage_salon.R;
import com.example.massage_salon.data.Orders;
import com.example.massage_salon.database.DataBaseManager;

import java.util.List;

public class OrdersAdapter extends RecyclerView.Adapter<OrdersAdapter.ViewHolder> {
    List<Orders> ordersList;
    Context context;
    LayoutInflater layoutInflater;
    DataBaseManager dbManager;

    public OrdersAdapter(List<Orders> ordersList, Context context){
        this.ordersList = ordersList;
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public OrdersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.orders_list, parent, false);
        return  new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdersAdapter.ViewHolder holder, int position) {
        dbManager = new DataBaseManager(holder.itemView.getContext());
        dbManager.openDb();
        Orders order = ordersList.get(position);
        holder.name.setText(dbManager.getService(order.getServiceId()).getName());
        holder.date.setText(order.getDateRecord());
        dbManager.closeDb();
    }

    @Override
    public int getItemCount() {
        return ordersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.serviceIDmain);
            date = itemView.findViewById(R.id.dateSerfviceShow);
        }
    }
}
