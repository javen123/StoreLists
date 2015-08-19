package com.appsneva.storelists;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends BaseActivity {


    protected RecyclerView mStoreRecyclerView;
    private StoreRecyclerViewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(isNetworkAvailable() == false){
            alertPopUp("OOPS!", "You need network connectivity to use this wonderful app");
        }


        activateToolbar();

        mStoreRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager llm = new LinearLayoutManager(MainActivity.this);
        mStoreRecyclerView.setLayoutManager(llm);
        mStoreRecyclerView.setHasFixedSize(true);

        ProcessStores process = new ProcessStores("http://sandbox.bottlerocketapps.com/BR_Android_CodingExam_2015_Server/stores.json");
        process.execute();

    }

    @Override
    protected void onResume() {
        super.onResume();

        if(isNetworkAvailable() == false){
            alertPopUp("OOPS!", "You need network connectivity to use this wonderful app");
        }
    }

    //Helper

    public class ProcessStores extends ParseJSON {

        public ProcessStores(String mRawURL) {
            super(mRawURL);
        }

        public void execute() {
            super.execute();

            ProcessData processData = new ProcessData();
            processData.execute(getmRawURL());
        }

        public class ProcessData extends MyParser {
            protected void onPostExecute(String webData){
                super.onPostExecute(webData);

                adapter = new StoreRecyclerViewAdapter(getmStoresDownloaded(), getApplicationContext());
                mStoreRecyclerView.setAdapter(adapter);
            }
            protected String doInBackground(String... params){

                return super.doInBackground(params);
            }
        }
    }

    // connectivity helper

    // Check all connectivities whether available or not
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }

    private void alertPopUp(String title, String message){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.setNegativeButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog addAlert = builder.create();
        addAlert.show();

    }

}
