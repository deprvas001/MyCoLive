package com.development.mycolive.views.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.development.mycolive.R;
import com.development.mycolive.databinding.ActivityPropertyMapBinding;
import com.development.mycolive.model.home.HomeFeatureProperty;
import com.development.mycolive.views.activity.propertyDetail.PropertyDetail;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class PropertyMap extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    ArrayList<HomeFeatureProperty> searchList;
    ActivityPropertyMapBinding mapBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapBinding = DataBindingUtil.setContentView(this,R.layout.activity_property_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        if(getIntent()!=null){
            searchList = getIntent().getParcelableArrayListExtra("search_list");
        }

        initializeView();
    }

    private void initializeView(){
        mapBinding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
       /* mapBinding.toolbar.setTitle(getString(R.string.search_result));

        setSupportActionBar(mapBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
*/

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected (item);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        for(int i = 0 ; i < searchList.size() ; i++) {

            createMarker(Double.parseDouble(searchList.get(i).getLatitude()),
                    Double.parseDouble(searchList.get(i).getLongitude()),
                    searchList.get(i).getName(),searchList.get(0));
        }

        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                HomeFeatureProperty homeFeatureProperty = (HomeFeatureProperty) marker.getTag();
              //  Toast.makeText(PropertyMap.this, homeFeatureProperty.getId(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(PropertyMap.this, PropertyDetail.class);
                intent.putExtra("Property_Id",homeFeatureProperty.getId());
                startActivity(intent);

            }
        });

    /*    mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                return false;
            }
        });*/

    }

    protected Marker createMarker(double latitude, double longitude, String title,HomeFeatureProperty featureProperty) {
        Marker marker = mMap.addMarker(new MarkerOptions()
                .position(new LatLng(latitude, longitude))
                .anchor(0.5f, 0.5f)
                .title(title)
                //.snippet(snippet)
                .icon(BitmapDescriptorFactory.defaultMarker()));
        marker.setTag(featureProperty);

        return marker;
    }


}
