package com.example.staggeredgridlayoutsample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.staggeredgridlayoutsample.model.Item;
import com.example.staggeredgridlayoutsample.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DashboardRecyclerAdapter extends RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardAdapterViewHolder> {

    private final Context context;
    private final List<Item> items;

    public DashboardRecyclerAdapter(Context context, List<Item> items) {
        this.context = context;
        this.items = items;
    }

    @NonNull
    @Override
    public DashboardAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.recycle_child_layout, viewGroup, false);
        return new DashboardAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DashboardAdapterViewHolder dashboardAdapterViewHolder, int i) {
        String images = items.get(i).getImage();
        Picasso.get().load(images).into(dashboardAdapterViewHolder.itemIcon); // use picasso image library
    }


    @Override
    public int getItemCount() {
        return items.size();
    }


    class DashboardAdapterViewHolder extends RecyclerView.ViewHolder {
        ImageView itemIcon;

        DashboardAdapterViewHolder(View itemView) {
            super(itemView);
            itemIcon = itemView.findViewById(R.id.itemIcon);
        }

    }

}
