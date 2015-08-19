package com.appsneva.storelists;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by javen on 8/17/15.
 */
public class ParseJSON extends GetJSON {



    public List<Store> mStoresDownloaded;
    private String LOG_TAG = ParseJSON.class.getSimpleName();


    public ParseJSON(String mRawURL) {
        super(mRawURL);

        mStoresDownloaded = new ArrayList<Store>();
    }

    public List<Store> getmStoresDownloaded() {
        return mStoresDownloaded;
    }

    public void execute() {

        MyParser parsing = new MyParser();
        Log.v(LOG_TAG, "Paring is parsing");
        parsing.execute(getmRawURL());
    }

    public void processResult() {

        if(getmDownloadStatus() != DownloadStatus.OK){
            Log.e(LOG_TAG, "Error downloading data");
            return;
        }
        try {
            JSONObject jsonData = new JSONObject((getmData()));
            JSONArray stores = jsonData.getJSONArray("stores");
            for(int i =0; i<stores.length(); i++){

                JSONObject storeData = stores.getJSONObject(i);
                String address = storeData.getString("address");
                String city = storeData.getString("city");
                String state = storeData.getString("state");
                String zip = storeData.getString("zipcode");
                String name = storeData.getString("name");
                String latitude = storeData.getString("latitude");
                String longitude = storeData.getString("longitude");
                String phone = storeData.getString("phone");
                String storeId = storeData.getString("storeID");
                String photoURL = storeData.getString("storeLogoURL");

                Store storeObject = new Store(name, address,city,state,zip,latitude,longitude,phone,storeId,photoURL);
                mStoresDownloaded.add(storeObject);
            }

            for(Store singleStore : mStoresDownloaded){
                Log.v(LOG_TAG, "Single store: "+ singleStore.toString());
            }
        }
        catch (JSONException jsone){
            jsone.printStackTrace();
            Log.e(LOG_TAG, "Error processing JSON"+jsone);

        }
    }


    public class MyParser extends DownloadData {

        protected void onPostExecute(String webData){
            super.onPostExecute(webData);
            processResult();
        }

        protected String doInBackground(String... params){

            return super.doInBackground(params);
        }
    }
}
