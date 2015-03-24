package com.richardscollin.shoppingwithfriends;

import android.location.Location;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class SalesMap extends FragmentActivity {

    private GoogleMap mMap; // Might be null if Google Play services APK is not available.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sales_map);
        setUpMapIfNeeded();
        putStuffOnMap();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
        putStuffOnMap();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            mMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                setUpMap();
            }
        }
    }

    /**
     * Go through each of the sales on this user's friends list
     * and put a marker on the map. Then, change the map viewing area to include the locations.
     */
    private void putStuffOnMap() {
        mMap.clear();
        ArrayList<Person.Sale> sales = new ArrayList<>();
        for (User i : Model.getCurrentPerson().getFriends()) {
            for (Person.Sale j : i.getSales()) {
                if (null != j.getGpsLocation()) {
                    sales.add(j);
                } else {
                    Toast.makeText(this, "null location", Toast.LENGTH_SHORT).show();
                }
            }
        }
        if (sales.isEmpty()) {
            Toast.makeText(this, "There is nothing to view", Toast.LENGTH_SHORT).show();
            finish();
        }

        LatLngBounds.Builder bounds = LatLngBounds.builder();
        for (Person.Sale i : sales) {
            Location loc = i.getGpsLocation();
            mMap.addMarker(new MarkerOptions().position(new LatLng(loc.getLatitude(), loc.getLongitude()))
                    .title(i.getItemName())
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_launcher)));
            bounds = bounds.include(new LatLng(loc.getLatitude(), loc.getLongitude()));

        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds.build(), 50, 50, 20));
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {
        putStuffOnMap();
    }
}
