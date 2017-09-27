package com.example.joseph.kendrickjsonexample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Joseph on 9/26/2017.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List <ListItem> listItems;
    private Context context;

    public MyAdapter(List<ListItem> listItems, Context context) {
        this.listItems = listItems;
        this.context=context;
    }

    //Creates new view holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);

        return new ViewHolder(v);
    }

    //Set up bind view holders to update recyclerView
    //Set up private fields to be used for recyclerView
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.tvHead.setText(listItem.getName());
        holder.tvDescription.setText(listItem.getBio());
        Picasso.with(context).load(listItem.getImageurl()).into(holder.imageView);
    }

    //Returns the total number of items in the data set by the adapter
    @Override
    public int getItemCount() {
        return listItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public TextView tvHead;
        public TextView tvDescription;
        public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);

                tvHead = (TextView) itemView.findViewById(R.id.Heading);
                tvDescription = (TextView) itemView.findViewById(R.id.Description);
                imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

}
