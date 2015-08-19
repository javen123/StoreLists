package com.appsneva.storelists;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity {

    private String LOG_TAG = MapsActivity.class.getSimpleName();
    private GoogleMap mMap; // Might be null if Google Play services APK is not available.
    private Store mCurStore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();

        //get intent from mainActivity
        Intent intent = this.getIntent();
        Bundle args = intent.getBundleExtra("INFO");
        convertIntentBundleToUse(args);
        setStoreLocation();
    }

    private void setStoreLocation() {


    }

    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p>
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
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap() {

        long mLat = Long.parseLong(mCurStore.getmLatitude());
        long mLong = Long.parseLong(mCurStore.getmLongitude());

        mMap.addMarker(new MarkerOptions().position(new LatLng(mLat,mLong)).title(mCurStore.getmName()));
    }

    //HELPERS

    private void convertIntentBundleToUse (Bundle args){
        Store curStore = (Store)args.getSerializable("CURSTORE");

        //set current store to activity scope

        mCurStore = curStore;
        Log.v(LOG_TAG, "Curstore is: " + mCurStore.toString());
    }
}
