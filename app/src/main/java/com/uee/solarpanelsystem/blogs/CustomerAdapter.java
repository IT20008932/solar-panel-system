package com.uee.solarpanelsystem;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.uee.solarpanelsystem.blogs.Blog;
import com.uee.solarpanelsystem.database.DBHandler;

import java.util.ArrayList;

public class CustomerAdapter extends RecyclerView.Adapter<CustomerAdapter.myviewholder> {

    private ArrayList<Blog> blog;
    private Context context;

    public CustomerAdapter(ArrayList<Blog> blog){
        this.blog = blog;
    }

    @NonNull
    @Override
    public CustomerAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cus,parent,false);

        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomerAdapter.myviewholder holder, int position) {

        holder.title.setText(blog.get(position).getTitle());

        int id =position;

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), ViewBlog.class);
                i.putExtra("blog", blog.get(id).getBlog());
                i.putExtra("title", blog.get(id).getTitle());
                view.getContext().startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.blog.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        Button view;
        TextView title;

        public myviewholder(View itemView)
        {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.titletext);

            view=(Button) itemView.findViewById(R.id.viewBtn);
        }
    }
}
