package com.example.lejlekustore;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterItem;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;
import java.util.List;


public class MapFragment extends Fragment {

    private ClusterManager<MyItem>clusterManager;
    private List<MyItem>items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_map, container, false);



        SupportMapFragment supportMapFragment = (SupportMapFragment)getChildFragmentManager().
                findFragmentById(R.id.google_map);


        //Async map
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(@NonNull GoogleMap googleMap) {
                //LatLng mylocation = new LatLng(42.325816, 20.694676); //mylocation
                GoogleMapOptions mapoptions = new GoogleMapOptions();
                mapoptions.mapType(GoogleMap.MAP_TYPE_TERRAIN);
                mapoptions.zoomControlsEnabled(true);
                mapoptions.compassEnabled(true);


                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(42.325816, 20.694676), 5));
                clusterManager = new ClusterManager<MyItem>(getContext(), googleMap);
                googleMap.setOnCameraIdleListener(clusterManager);
                googleMap.setOnMarkerClickListener(clusterManager);
                addItems();

            }
        });



        return root;
    }

    private void addItems() {

        // Set some lat/lng coordinates to start with.
        double lat = 42.325816;
        double lng = 20.694676;

        // Add ten cluster items in close proximity, for purposes of this example.
        for (int i = 0; i < 8; i++) {
            double offset = i / 60d;
            lat = lat + offset;
            lng = lng + offset;
            MyItem offsetItem = new MyItem(lat, lng, "Store " + i + " location" , "This is the location of one of our stores");
            clusterManager.addItem(offsetItem);
        }
    }
}


