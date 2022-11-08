package com.uee.solarpanelsystem.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.uee.solarpanelsystem.R;
import com.uee.solarpanelsystem.packages.ModifyPackage;
import com.uee.solarpanelsystem.packages.ViewPackage;

import java.util.ArrayList;

public class PackageAdapter extends RecyclerView.Adapter<PackageAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList package_id,
            package_name,
            description,
            price,
            sp_qty,
            rating,
            batteries,
            backup,
            connection,
            ch_current,
            ch_time;

    public PackageAdapter(Activity activity,
                          Context context,
                          ArrayList package_id,
                          ArrayList package_name,
                          ArrayList description,
                          ArrayList price,
                          ArrayList sp_qty,
                          ArrayList rating,
                          ArrayList batteries,
                          ArrayList backup,
                          ArrayList connection,
                          ArrayList ch_current,
                          ArrayList ch_time)

    {
        Log.d("workflow","PackageAdapter constructor called");
        this.activity = activity;
        this.context = context;
        this.package_id = package_id;
        this.package_name = package_name;
        this.description = description;
        this.price = price;
        this.sp_qty = sp_qty;
        this.rating = rating;
        this.batteries = batteries;
        this.backup = backup;
        this.connection = connection;
        this.ch_current = ch_current;
        this.ch_time = ch_time;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.package_row,parent,false);

        return new MyViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull PackageAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("workflow","PackageAdapter onBindViewHolder method called");

        holder.pid_txt.setText(String.valueOf(package_id.get(position)));
        holder.package_name_txt.setText(String.valueOf(package_name.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
        holder.price_txt.setText(String.valueOf(description.get(position)));
        holder.sp_qty_txt.setText(String.valueOf(sp_qty.get(position)));
        holder.rating_txt.setText(String.valueOf(rating.get(position)));
        holder.batteries_txt.setText(String.valueOf(batteries.get(position)));
        holder.backup_txt.setText(String.valueOf(backup.get(position)));
        holder.connection_txt.setText(String.valueOf(connection.get(position)));
        holder.ch_current_txt.setText(String.valueOf(ch_current.get(position)));
        holder.ch_time_txt.setText(String.valueOf(ch_time.get(position)));
        holder.packageImg.setVisibility(View.VISIBLE);

        holder.viewbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ViewPackage.class);
                intent.putExtra("package_id",String.valueOf(package_id.get(position)));
                intent.putExtra("package_name",String.valueOf(package_name.get(position)));
                intent.putExtra("description",String.valueOf(description.get(position)));
                intent.putExtra("price", String.valueOf(price.get(position)));
                intent.putExtra("sp_qty",String.valueOf(sp_qty.get(position)));
                intent.putExtra("rating",String.valueOf(rating.get(position)));
                intent.putExtra("batteries",String.valueOf(batteries.get(position)));
                intent.putExtra("backup",String.valueOf(backup.get(position)));
                intent.putExtra("connection",String.valueOf(connection.get(position)));
                intent.putExtra("ch_current",String.valueOf(ch_current.get(position)));
                intent.putExtra("ch_time",String.valueOf(ch_time.get(position)));
                activity.startActivityForResult(intent,1);

                Log.d("values",String.valueOf(package_id.get(position)));
            }
        });

        holder.editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ModifyPackage.class);
                intent.putExtra("pid",String.valueOf(package_id.get(position)));
                intent.putExtra("package_name",String.valueOf(package_name.get(position)));
                intent.putExtra("description",String.valueOf(description.get(position)));
                intent.putExtra("price", String.valueOf(price.get(position)));
                intent.putExtra("sp_qty",String.valueOf(sp_qty.get(position)));
                intent.putExtra("rating",String.valueOf(rating.get(position)));
                intent.putExtra("batteries",String.valueOf(batteries.get(position)));
                intent.putExtra("backup",String.valueOf(backup.get(position)));
                intent.putExtra("connection",String.valueOf(connection.get(position)));
                intent.putExtra("ch_current",String.valueOf(ch_current.get(position)));
                intent.putExtra("ch_time",String.valueOf(ch_time.get(position)));
                activity.startActivityForResult(intent,1);

                Log.d("values",String.valueOf(package_id.get(position)));
            }
        });


    }

    @Override
    public int getItemCount() {
        return package_id.size();      }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView pid_txt,package_name_txt,description_txt, price_txt, sp_qty_txt, rating_txt, batteries_txt, backup_txt, connection_txt, ch_current_txt, ch_time_txt;
        LinearLayout mainLayout;
        ImageButton viewbtn;
        MaterialButton editbtn;
        ImageView packageImg;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pid_txt=itemView.findViewById(R.id.pid_txt);
            package_name_txt=itemView.findViewById(R.id.package_name_txt);
            description_txt=itemView.findViewById(R.id.description_txt);
            price_txt=itemView.findViewById(R.id.price_txt);
            sp_qty_txt=itemView.findViewById(R.id.sp_qty_txt);
            rating_txt=itemView.findViewById(R.id.rating_txt);
            batteries_txt=itemView.findViewById(R.id.batteries_txt);
            backup_txt=itemView.findViewById(R.id.backup_txt);
            connection_txt=itemView.findViewById(R.id.connection_txt);
            ch_current_txt=itemView.findViewById(R.id.ch_current_txt);
            ch_time_txt=itemView.findViewById(R.id.ch_time_txt);

            packageImg=itemView.findViewById(R.id.package_img);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            viewbtn=itemView.findViewById(R.id.btnView);
            editbtn=itemView.findViewById(R.id.btnEdit);
        }
    }

}
