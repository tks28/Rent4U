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

public class InventoryCarAdapter extends RecyclerView.Adapter<InventoryCarAdapter.ViewHolder> {
    //InventoryCarAdapter class
    private Context context;
    private ArrayList<InventoryCarModels> mInventoryCarModels;

    //Constructor
    public InventoryCarAdapter(Context context, ArrayList<InventoryCarModels> inventoryCarModels) {
        this.context = context;
        this.mInventoryCarModels = inventoryCarModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.inventory_car_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        InventoryCarModels inventoryCar = mInventoryCarModels.get(position);
        holder.setInventoryCarDetails(inventoryCar);

        //go inventory car details when image clicked
        holder.carImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InventoryCarDetails.class);
                intent.putExtra("carImg", inventoryCar.getCarImg());
                intent.putExtra("carBrand", inventoryCar.getMtvInventoryCarBrand());
                intent.putExtra("carModel", inventoryCar.getMtvInventoryModel());
                intent.putExtra("carYear", inventoryCar.getMtvInventoryYear());
                intent.putExtra("carName", inventoryCar.getMtvInventoryCarName());
                intent.putExtra("carModel", inventoryCar.getMtvInventoryModel());
                intent.putExtra("carYear", inventoryCar.getMtvInventoryYear());
                intent.putExtra("carPlate", inventoryCar.getMtvInventoryCarPlate());
                intent.putExtra("carTransmission", inventoryCar.getMtvInventoryCarTransmission());
                intent.putExtra("carDriven", inventoryCar.getMtvInventoryCarDriven());
                intent.putExtra("carBody", inventoryCar.getMtvInventoryCarBody());
                intent.putExtra("carPrice", inventoryCar.getMtvInventoryPricePerDay());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        //go inventory car details when "See Details" clicked
        holder.mInventoryDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, InventoryCarDetails.class);
                intent.putExtra("carImg", inventoryCar.getCarImg());
                intent.putExtra("carBrand", inventoryCar.getMtvInventoryCarBrand());
                intent.putExtra("carModel", inventoryCar.getMtvInventoryModel());
                intent.putExtra("carYear", inventoryCar.getMtvInventoryYear());
                intent.putExtra("carName", inventoryCar.getMtvInventoryCarName());
                intent.putExtra("carModel", inventoryCar.getMtvInventoryModel());
                intent.putExtra("carYear", inventoryCar.getMtvInventoryYear());
                intent.putExtra("carPlate", inventoryCar.getMtvInventoryCarPlate());
                intent.putExtra("carTransmission", inventoryCar.getMtvInventoryCarTransmission());
                intent.putExtra("carDriven", inventoryCar.getMtvInventoryCarDriven());
                intent.putExtra("carBody", inventoryCar.getMtvInventoryCarBody());
                intent.putExtra("carPrice", inventoryCar.getMtvInventoryPricePerDay());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInventoryCarModels.size();
    }

    //recycler view
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView carImage;
        private TextView mInventoryCarName, mInventoryCarPlate, mInventoryPrice, mInventoryDetails;

        ViewHolder(View itemView){
            super(itemView);
            carImage = itemView.findViewById(R.id.inventoryCar);
            mInventoryCarName = itemView.findViewById(R.id.tvInventoryCarName);
            mInventoryCarPlate = itemView.findViewById(R.id.tvInventoryCarPlate);
            mInventoryPrice = itemView.findViewById(R.id.tvInventoryPrice);
            mInventoryDetails = itemView.findViewById(R.id.tvInventorySeeDetails);
        }

        void setInventoryCarDetails(InventoryCarModels model){
            Picasso.get().load(model.getCarImg()).fit().centerCrop().into(carImage);
            mInventoryCarName.setText(model.getMtvInventoryCarName());
            mInventoryCarPlate.setText(model.getMtvInventoryCarPlate());
            mInventoryPrice.setText(model.getMtvInventoryPricePerDay());
        }

    }
}
