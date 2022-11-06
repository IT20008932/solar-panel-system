package com.uee.solarpanelsystem.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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

import com.uee.solarpanelsystem.R;
import com.uee.solarpanelsystem.packages.ModifyPackage;

import java.util.ArrayList;

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList blog_id,
            blog_name,
            description;

    public BlogAdapter(Activity activity,
                          Context context,
                          ArrayList blog_id,
                          ArrayList blog_name,
                          ArrayList description)
    {
        Log.d("workflow","BlogAdapter constructor called");
        this.activity = activity;
        this.context = context;
        this.blog_id = blog_id;
        this.blog_name = blog_name;
        this.description = description;
    }

    @NonNull
    @Override
    public BlogAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.blog_row,parent,false);

        return new BlogAdapter.MyViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Log.d("workflow","BlogAdapter onBindViewHolder method called");

        holder.pid_txt.setText(String.valueOf(blog_id.get(position)));
        holder.package_name_txt.setText(String.valueOf(blog_name.get(position)));
        holder.description_txt.setText(String.valueOf(description.get(position)));
        holder.packageImg.setVisibility(View.VISIBLE);

        holder.imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(context, ModifyPackage.class);
                intent.putExtra("pid",String.valueOf(blog_id.get(position)));
                intent.putExtra("package_name",String.valueOf(blog_name.get(position)));
                intent.putExtra("description",String.valueOf(description.get(position)));
                activity.startActivityForResult(intent,1);

                Log.d("values",String.valueOf(blog_id.get(position)));
            }
        });

    }

    @Override
    public int getItemCount() {
        return blog_id.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView pid_txt,package_name_txt,description_txt;
        LinearLayout mainLayout;
        ImageButton imgbtn;
        ImageView packageImg;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            pid_txt=itemView.findViewById(R.id.pid_txt);
            package_name_txt=itemView.findViewById(R.id.package_name_txt);
            description_txt=itemView.findViewById(R.id.description_txt);
            packageImg=itemView.findViewById(R.id.package_img);
            mainLayout = itemView.findViewById(R.id.mainLayout);

            imgbtn=itemView.findViewById(R.id.imageButton);
        }
    }
}
