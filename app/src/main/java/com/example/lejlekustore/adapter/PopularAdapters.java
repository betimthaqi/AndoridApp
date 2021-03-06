package com.example.lejlekustore.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.lejlekustore.R;
import com.example.lejlekustore.ViewAllActivity;
import com.example.lejlekustore.models.HomeCategory;
import com.example.lejlekustore.models.PopularModel;

import java.util.ArrayList;
import java.util.List;

public class PopularAdapters extends RecyclerView.Adapter<PopularAdapters.ViewHolder> {

    private Context context;
    private List<PopularModel> popularModelList;

    public PopularAdapters(Context context, List<PopularModel> popularModelList) {
        this.context = context;
        this.popularModelList = popularModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(popularModelList.get(position).getImage()).into(holder.popImg);
        holder.name.setText(popularModelList.get(position).getPname());
        holder.description.setText(popularModelList.get(position).getDescription());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("category", popularModelList.get(position).getCategory());
                context.startActivity(intent);

            }
        });
    }

    public void filterList (List<PopularModel> filterList){
        popularModelList = filterList;
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return popularModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView popImg;
        TextView name, description, price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            popImg = itemView.findViewById(R.id.pop_img);
            description = itemView.findViewById(R.id.pop_des);
            name = itemView.findViewById(R.id.pop_name);
        }
    }
}
