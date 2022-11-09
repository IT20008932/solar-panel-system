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

public class BlogAdapter extends RecyclerView.Adapter<BlogAdapter.myviewholder> {

    private ArrayList<Blog> blog;
    private Context context;
    private DBHandler dbHandler;

    public BlogAdapter(ArrayList<Blog> blog){
        this.blog = blog;
    }

    @NonNull
    @Override
    public BlogAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);

        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BlogAdapter.myviewholder holder, int position) {

        holder.title.setText(blog.get(position).getTitle());

        int id =position;

        dbHandler = new DBHandler(context);

        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), editBlog.class);
                i.putExtra("blog", blog.get(id).getBlog());
                i.putExtra("title", blog.get(id).getTitle());
                i.putExtra("id", Integer.toString(blog.get(id).getId()));
                view.getContext().startActivity(i);
            }
        });


        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder=new AlertDialog.Builder(context);
                builder.setTitle("Delete Blog");
                builder.setMessage("Delete...?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        boolean res = dbHandler.deleteBlog(blog.get(id).getId());
                        if (res){
                            blog.remove(id);
                            notifyItemRemoved(id);
                            notifyDataSetChanged();
                            Toast.makeText(context,"Successful",Toast.LENGTH_LONG).show();
                        }else {
                            Toast.makeText(context,"Unsuccessful",Toast.LENGTH_LONG).show();
                        }
                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

                builder.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return this.blog.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        ImageView edit,delete;
        TextView title;
        public myviewholder(View itemView)
        {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.titletext);

            edit=(ImageView)itemView.findViewById(R.id.editicon);
            delete=(ImageView)itemView.findViewById(R.id.deleteicon);
        }
    }
}
