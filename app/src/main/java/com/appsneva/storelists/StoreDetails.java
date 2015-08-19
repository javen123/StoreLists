package com.appsneva.storelists;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class StoreDetails extends BaseActivity {


    private static String LOG_TAG = "StoreDetails";

    //GUI Vars and Views
    private Store mCurStore;
    private ImageView mLogo;
    private TextView mStoreId;
    private TextView mLatitude;
    private TextView mLongitude;
    private TextView mPhoneNumber;
    private TextView mState;
    private TextView mCity;
    private TextView mZip;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_details);

        //set up tool bar
        activateToolbarWithHomeEnabled();


        //get intent from mainActivity
        Intent intent = this.getIntent();
        Bundle args = intent.getBundleExtra("INFO");

        convertIntentBundleToUse(args);

        //set up GUI

        mLogo = (ImageView)findViewById(R.id.detail_logo);

        Picasso.with(this.getApplicationContext())
                .load(mCurStore.getmLogoUrl())
                .resize(200, (int) (200/2.2))
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .into(mLogo);

        mStoreId = (TextView)findViewById(R.id.detail_storeId);
        mStoreId.setText(mCurStore.getmStoreId());

        mLatitude = (TextView)findViewById(R.id.detail_latitude);
        mLatitude.setText("Lat: "+mCurStore.getmLatitude());

        mLongitude = (TextView)findViewById(R.id.detail_longitude);
        mLongitude.setText("Long: " +mCurStore.getmLongitude());

        mPhoneNumber = (TextView)findViewById(R.id.detail_phone);
        mPhoneNumber.setText(mCurStore.getmPhone());

        mState = (TextView) findViewById(R.id.detail_state);
        mState.setText(mCurStore.getmState());

        mCity = (TextView) findViewById(R.id.detail_city);
        mCity.setText(mCurStore.getmCity());

        mZip = (TextView) findViewById(R.id.detail_zip);
        mZip.setText(mCurStore.getmZipcode());

    }



    //HELPERS

    private void convertIntentBundleToUse (Bundle args){
        Store curStore = (Store)args.getSerializable("CURSTORE");

        //set current store to activity scope

        mCurStore = curStore;
        Log.v(LOG_TAG, "Curstore is: "+ mCurStore.toString());
    }
}
