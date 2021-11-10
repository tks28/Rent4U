package com.example.eventaid;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RentedAdapter extends RecyclerView.Adapter<RentedAdapter.ViewHolder> {
    //RentedAdapter class
    private Context context;
    private ArrayList<RentedModels> mRentedModels;

    //Constructor
    public RentedAdapter(Context context, ArrayList<RentedModels> rentedModels) {
        this.context = context;
        this.mRentedModels = rentedModels;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.rented_items, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        RentedModels rented = mRentedModels.get(position);
        holder.setRentedDetails(rented);

        //go rented details when image clicked
        holder.rentedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RentedDetails.class);
                intent.putExtra("img", rented.getRentedImg());
                intent.putExtra("rBrand", rented.getMtvBrand());
                intent.putExtra("model", rented.getMtvCarName());
                intent.putExtra("renterName", rented.getMtvRenterName());
                intent.putExtra("renterPhone", rented.getMtvRenterPhoneNumber());
                intent.putExtra("rentDate", rented.getRentDate());
                intent.putExtra("rentDays", rented.getRentDays());
                intent.putExtra("carPlate", rented.getMtvCarPlate());
                intent.putExtra("transmission", rented.getMtvCarTransmission());
                intent.putExtra("driven", rented.getMtvCarDriven());
                intent.putExtra("body", rented.getMtvCarBody());
                intent.putExtra("price", rented.getMtvPricePerDay());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        //go rented details when "See Details" clicked
        holder.mDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, RentedDetails.class);
                intent.putExtra("img", rented.getRentedImg());
                intent.putExtra("rBrand", rented.getMtvBrand());
                intent.putExtra("model", rented.getMtvCarName());
                intent.putExtra("renterName", rented.getMtvRenterName());
                intent.putExtra("renterPhone", rented.getMtvRenterPhoneNumber());
                intent.putExtra("rentDate", rented.getRentDate());
                intent.putExtra("rentDays", rented.getRentDays());
                intent.putExtra("carPlate", rented.getMtvCarPlate());
                intent.putExtra("transmission", rented.getMtvCarTransmission());
                intent.putExtra("driven", rented.getMtvCarDriven());
                intent.putExtra("body", rented.getMtvCarBody());
                intent.putExtra("price", rented.getMtvPricePerDay());

                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRentedModels.size();
    }

    //recycler view
    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView rentedImage;
        private TextView mCarName, mRenterName, mCarPlate, mPhoneNumber, mPrice, mDetails;

        ViewHolder(View itemView){
            super(itemView);
            rentedImage = itemView.findViewById(R.id.rented);
            mCarName = itemView.findViewById(R.id.tvCarName);
            mRenterName = itemView.findViewById(R.id.tvRenterName);
            mCarPlate = itemView.findViewById(R.id.tvCarPlate);
            mPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
            mPrice = itemView.findViewById(R.id.tvPrice);
            mDetails = itemView.findViewById(R.id.tvSeeDetails);
        }

        void setRentedDetails(RentedModels model){
            Picasso.get().load(model.getRentedImg()).fit().centerCrop().into(rentedImage);
            mCarName.setText(model.getMtvCarName());
            mRenterName.setText(model.getMtvRenterName());
            mCarPlate.setText(model.getMtvCarPlate());
            mPhoneNumber.setText(model.getMtvRenterPhoneNumber());
            mPrice.setText(model.getMtvPricePerDay());
        }
    }
}
