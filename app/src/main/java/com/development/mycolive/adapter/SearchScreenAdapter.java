package com.development.mycolive.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import com.development.mycolive.R;
import com.development.mycolive.constant.ApiConstant;
import com.development.mycolive.model.favouriteRoomate.FavouriteRequest;
import com.development.mycolive.model.favouriteRoomate.RoomateFavApiResponse;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.model.home.HomeSlider;
import com.development.mycolive.views.activity.favouriteRoomate.FavRoomateViewModel;
import com.development.mycolive.views.activity.favouriteRoomate.FavouriteRoomateDetail;
import com.development.mycolive.views.activity.propertyDetail.PropertyDetail;
import com.development.mycolive.views.activity.roomInformation.RoomInformation;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchScreenAdapter extends RecyclerView.Adapter<SearchScreenAdapter.MyViewHolder> {
    private List<HomeFeatureProperty> searchList;
    private Context context;
    ProgressDialog progressDialog;
    String token="";

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, adddress, noOfBathroom, noOfRoom, price, label;
        public ImageView imageView, fav_icon;
        public LinearLayout propertyLayout;


        public MyViewHolder(View view) {
            super(view);
            imageView = (ImageView) view.findViewById(R.id.image);
            title = (TextView) view.findViewById(R.id.type);
            adddress = (TextView) view.findViewById(R.id.address);
            noOfBathroom = (TextView) view.findViewById(R.id.no_of_bathroom);
            noOfRoom = (TextView) view.findViewById(R.id.no_of_room);
            price = (TextView) view.findViewById(R.id.price);
            fav_icon = (ImageView) view.findViewById(R.id.fav_icon);
            label = (TextView) view.findViewById(R.id.label);
            propertyLayout = (LinearLayout) view.findViewById(R.id.property_layout);
        }
    }

    public SearchScreenAdapter(Context context, List<HomeFeatureProperty> searchList,String token) {
        this.context = context;
        this.searchList = searchList;
        this.token = token;
    }

    @Override
    public SearchScreenAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.search_result_layout, parent, false);

        return new SearchScreenAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchScreenAdapter.MyViewHolder holder, int position) {
        HomeFeatureProperty search_room = searchList.get(position);
        List<HomeSlider> homeSlider = search_room.getImage_slider();
       /* holder.title.setText(search_room.getPropertyName());
        holder.adddress.setText(search_room.getAddress());
        holder.noOfBathroom.setText(search_room.getNoOfBathroom());
        holder.noOfRoom.setText(search_room.getNumberOfRoom());*/
        holder.title.setText(search_room.getName());
        holder.adddress.setText(search_room.getAddress());
        holder.noOfBathroom.setText(search_room.getNo_of_bathroom());
        holder.noOfRoom.setText(search_room.getNumber_of_room());
        holder.price.setText("€ " + search_room.getPrice());

        if (search_room.getBooking_shared_btn() != null) {
            if (!search_room.getBooking_shared_btn().isEmpty()) {
                holder.label.setText(search_room.getBooking_shared_btn());
            }
        }

        holder.fav_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               addFav(search_room.getId(), holder);
            }
        });


        if (search_room.getFavourites().equals("0")) {
            holder.fav_icon.setColorFilter(ContextCompat.getColor(context,
                    R.color.text_color_hint), android.graphics.PorterDuff.Mode.MULTIPLY);
        } else {
            holder.fav_icon.setColorFilter(ContextCompat.getColor(context,
                    R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
        }

        holder.price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, PropertyDetail.class);
                intent.putExtra("Property_Id", search_room.getId());
                context.startActivity(intent);
                //  context.startActivity(new Intent(context, RoomDetail.class));
            }
        });

      /*  Picasso.get()
                .load(image)
                .placeholder(R.drawable.loading)
                .error(R.drawable.no_image_found)
                .into(imageView);*/


        Picasso.get()
                .load(homeSlider.get(0).getImage())
                .placeholder(R.drawable.no_image_found)
                /* .placeholder(R.drawable.image1)
                 .error(R.drawable.err)*/
                .into(holder.imageView);
        /* holder.title.setText(find_room.getType());*/
    }

    @Override
    public int getItemCount() {
        return searchList.size();
    }

    private void addFav(String id, MyViewHolder holder) {
        showProgressDialog(context .getResources().getString(R.string.loading));

        String type = "PROPERTY";

        FavouriteRequest request = new FavouriteRequest();
        request.setType(type);
        request.setId(id);

        Map<String, String> headers = new HashMap<>();
        headers.put(ApiConstant.CONTENT_TYPE, ApiConstant.CONTENT_TYPE_VALUE);
        headers.put(ApiConstant.SOURCES, ApiConstant.SOURCES_VALUE);
        headers.put(ApiConstant.USER_TYPE, ApiConstant.USER_TYPE_DRIVER);
        headers.put(ApiConstant.USER_DEVICE_TYPE, ApiConstant.USER_DEVICE_TYPE_VALUE);
        headers.put(ApiConstant.USER_DEVICE_TOKEN, ApiConstant.USER_DEVICE_TOKEN_VALUE);
        headers.put(ApiConstant.AUTHENTICAT_TOKEN, token);

        FavRoomateViewModel viewModel = ViewModelProviders.of((FragmentActivity) context).get(FavRoomateViewModel.class);
        viewModel.setFav(context, headers, request).observe((LifecycleOwner) context, new Observer<RoomateFavApiResponse>() {
            @Override
            public void onChanged(RoomateFavApiResponse apiResponse) {
                hideProgressDialog();
                if (apiResponse.response != null) {

                    if (apiResponse.getResponse().getStatus() == 1) {
                        Toast.makeText(context, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();

                        if (apiResponse.getResponse().getIs_fav() == 1) {
                            holder.fav_icon.setColorFilter(ContextCompat.getColor(context,
                                    R.color.colorPrimaryDark), android.graphics.PorterDuff.Mode.MULTIPLY);
                        } else {
                            holder.fav_icon.setColorFilter(ContextCompat.getColor(context,
                                    R.color.text_color_hint), android.graphics.PorterDuff.Mode.MULTIPLY);
                        }

                    } else {
                        Toast.makeText(context, apiResponse.getResponse().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(context, apiResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void showProgressDialog(String message) {
        if (progressDialog == null)
            progressDialog = new ProgressDialog(context);

        if (!progressDialog.isShowing()) {
            progressDialog.setMessage(message);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
        }
    }

    public void hideProgressDialog() {
        if (progressDialog != null && progressDialog.isShowing())
            progressDialog.dismiss();
    }

}
