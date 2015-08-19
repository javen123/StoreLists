package com.appsneva.storelists;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by javen on 8/17/15.
 */
public class StoreRecyclerViewAdapter extends RecyclerView.Adapter<StoreRecyclerViewAdapter.StoreViewHolder> {

    private static String LOG_TAG = "RecyclerView";

    private static List<Store> mStores;


    Context mContext;

    public static class StoreViewHolder extends RecyclerView.ViewHolder {


        ImageView thumbnail;
        CardView cv;
        TextView title;

        public StoreViewHolder(View view) {
            super(view);

            cv = (CardView)view.findViewById(R.id.my_card_view);
//            this.thumbnail =(ImageView)view.findViewById(R.id.list_image);
            title = (TextView)view.findViewById(R.id.list_title);

            // set info icon click listener

            ImageButton infoBtn = (ImageButton)view.findViewById(R.id.icon_info);
            infoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context vContext = v.getContext();
                    Intent intent = new Intent(vContext, StoreDetails.class);
                    Store singleStore = mStores.get(getAdapterPosition());
                    Bundle args = new Bundle();
                    args.putSerializable("CURSTORE", singleStore);
                    intent.putExtra("INFO", args);
                    vContext.startActivity(intent);
                }
            });

            ImageButton mapBtn = (ImageButton)view.findViewById(R.id.icon_map);
            mapBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context vContext = v.getContext();
                    Intent intent = new Intent(vContext, MapsActivity.class);
                    Store singleStore = mStores.get(getAdapterPosition());
                    Bundle args = new Bundle();
                    args.putSerializable("CURSTORE", singleStore);
                    intent.putExtra("INFO", args);
                    vContext.startActivity(intent);
                }
            });
        }
    }



    public StoreRecyclerViewAdapter(List<Store> stores, Context context) {
        this.mContext = context;
        this.mStores = stores;
    }



    @Override
    public StoreViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        Log.v(LOG_TAG,"recycler on create called");
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.store_list_item,viewGroup,false);
        StoreViewHolder holder = new StoreViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(StoreViewHolder holder, int i) {
        holder.title.setText(mStores.get(i).getmName());
//       Picasso.with(mContext)
//                .load(mStores.get(i).getmLogoUrl())
//                .resize(150, (int) (150/2.2))
//                .placeholder(R.drawable.img_placeholder)
//                .error(R.drawable.img_placeholder)
//                .into(holder.thumbnail);
//
//        Log.v(LOG_TAG, "Picasso in action");

    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return mStores.size();
    }
}
