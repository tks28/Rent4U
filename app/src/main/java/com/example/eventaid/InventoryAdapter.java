package com.example.eventaid;

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

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.ViewHolder> {
    //InventoryAdapter class
    private Context context;
    private ArrayList<InventoryModels> mInventoryModels;

    //Constructor
    public InventoryAdapter(Context context, ArrayList<InventoryModels> inventoryModels) {
        this.context = context;
        this.mInventoryModels = inventoryModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inventory_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryModels inventory = mInventoryModels.get(position);
        holder.setInventoryDetails(inventory);

        //go inventory car when image clicked
        holder.logoImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InventoryCar.class);
                intent.putExtra("bName", holder.mBrand.getText().toString());
                context.startActivity(intent);
            }
        });

        //go inventory car when brand text view clicked
        holder.mBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InventoryCar.class);
                intent.putExtra("bName", holder.mBrand.getText().toString());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInventoryModels.size();
    }

    //recycler view
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView logoImage;
        private TextView mBrand;

        ViewHolder(View itemView){
            super(itemView);
            logoImage = itemView.findViewById(R.id.logo);
            mBrand = itemView.findViewById(R.id.tvBrandName);
        }

        void setInventoryDetails(InventoryModels model){
            Picasso.get().load(model.getLogoImg()).fit().centerCrop().into(logoImage);
            mBrand.setText(model.getMtvBrand());
        }
    }
}