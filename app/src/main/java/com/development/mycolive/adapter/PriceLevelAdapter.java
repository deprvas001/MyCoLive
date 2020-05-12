package com.development.mycolive.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.model.propertyDetailModel.FacilityData;
import com.development.mycolive.model.searchDetailPage.PriceLevel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PriceLevelAdapter extends RecyclerView.Adapter<PriceLevelAdapter.MyViewHolder> {

    private List<PriceLevel> detailList;
    private Context context;
    private ViewGroup group;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView label,price;
        public LinearLayout postLayout;

        public MyViewHolder(View view) {
            super(view);
            label = (TextView)view.findViewById(R.id.label);
            price = (TextView)view.findViewById(R.id.value);

        }
    }

    public PriceLevelAdapter(Context context,List<PriceLevel> detailList) {
        this.context = context;
        this.detailList = detailList;
    }

    @Override
    public PriceLevelAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.price_level_item_row, parent, false);
        group = parent;

        return new PriceLevelAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(PriceLevelAdapter.MyViewHolder holder, int position) {
       PriceLevel priceLevel = detailList.get(position);

        holder.label.setText(priceLevel.getLabel()+":");
        holder.price.setText("€ " +priceLevel.getPrice()+ "/Month");
    }

    @Override
    public int getItemCount() {
        return detailList.size();
    }
}
