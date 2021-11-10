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

public class DamagedCarAdapter extends RecyclerView.Adapter<DamagedCarAdapter.ViewHolder> {
    //EventAdapter class
    private Context context;
    private ArrayList<DamagedCarModels> mDamagedCarModels;

    //Constructor
    public DamagedCarAdapter(Context context, ArrayList<DamagedCarModels> damagedCarModels) {
        this.context = context;
        this.mDamagedCarModels = damagedCarModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.damaged_car_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DamagedCarModels damagedCar = mDamagedCarModels.get(position);
        holder.setDamagedCarDetails(damagedCar);

        //go to damaged car details "See details" clicked
        holder.mDCDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DamagedCarDetails.class);
                intent.putExtra("dcImg", damagedCar.getDamagedCarImg());
                intent.putExtra("dcModel", damagedCar.getMtvDCCarName());
                intent.putExtra("dcBrand", damagedCar.getMtvDCBrand());
                intent.putExtra("technicianName", damagedCar.getMtvTechnicianName());
                intent.putExtra("technicianPhone", damagedCar.getMtvTechnicianPhoneNumber());
                intent.putExtra("sentDate", damagedCar.getSentRepairDate());
                intent.putExtra("repairDays", damagedCar.getRepairDays());
                intent.putExtra("dcCarPlate", damagedCar.getMtvDCCarPlate());
                intent.putExtra("dcTransmission", damagedCar.getMtvDCCarTransmission());
                intent.putExtra("dcDriven", damagedCar.getMtvDCCarDriven());
                intent.putExtra("dcBody", damagedCar.getMtvDCCarBody());
                intent.putExtra("dcPrice", damagedCar.getMtvDCPricePerDay());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        //go to damaged car details when image clicked
        holder.damagedCarImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DamagedCarDetails.class);
                intent.putExtra("dcImg", damagedCar.getDamagedCarImg());
                intent.putExtra("dcModel", damagedCar.getMtvDCCarName());
                intent.putExtra("dcBrand", damagedCar.getMtvDCBrand());
                intent.putExtra("technicianName", damagedCar.getMtvTechnicianName());
                intent.putExtra("technicianPhone", damagedCar.getMtvTechnicianPhoneNumber());
                intent.putExtra("sentDate", damagedCar.getSentRepairDate());
                intent.putExtra("repairDays", damagedCar.getRepairDays());
                intent.putExtra("dcCarPlate", damagedCar.getMtvDCCarPlate());
                intent.putExtra("dcTransmission", damagedCar.getMtvDCCarTransmission());
                intent.putExtra("dcDriven", damagedCar.getMtvDCCarDriven());
                intent.putExtra("dcBody", damagedCar.getMtvDCCarBody());
                intent.putExtra("dcPrice", damagedCar.getMtvDCPricePerDay());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDamagedCarModels.size();
    }

    //recycler view
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView damagedCarImage;
        private TextView mDCCarName, mTechnicianName, mDCCarPlate, mDCPhoneNumber, mDCPrice, mDCDetails;

        ViewHolder(View itemView){
            super(itemView);
            damagedCarImage = itemView.findViewById(R.id.damagedCar);
            mDCCarName = itemView.findViewById(R.id.tvDCCarName);
            mTechnicianName = itemView.findViewById(R.id.tvDCTechnicianName);
            mDCCarPlate = itemView.findViewById(R.id.tvDCCarPlate);
            mDCPhoneNumber = itemView.findViewById(R.id.tvDCTechnicianPhoneNumber);
            mDCPrice = itemView.findViewById(R.id.tvDCPrice);
            mDCDetails = itemView.findViewById(R.id.tvDCSeeDetails);
        }

        void setDamagedCarDetails(DamagedCarModels model){
            Picasso.get().load(model.getDamagedCarImg()).fit().centerCrop().into(damagedCarImage);
            mDCCarName.setText(model.getMtvDCCarName());
            mTechnicianName.setText(model.getMtvTechnicianName());
            mDCCarPlate.setText(model.getMtvDCCarPlate());
            mDCPhoneNumber.setText(model.getMtvTechnicianPhoneNumber());
            mDCPrice.setText(model.getMtvDCPricePerDay());
        }

    }
}

