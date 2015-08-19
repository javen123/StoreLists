package com.appsneva.storelists;

import android.os.AsyncTask;
import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

enum DownloadStatus {IDLE, PROCESSING, NOT_INITITALIZED, FAILED_OR_EMPTY, OK}

public class GetJSON {

    private String LOG_TAG = GetJSON.class.getSimpleName();
    private String mRawURL;
    private String mData;
    private DownloadStatus mDownloadStatus;

    public GetJSON(String mRawURL) {
        this.mRawURL = mRawURL;
        this.mDownloadStatus = DownloadStatus.IDLE;
    }

    public void reset() {
        this.mDownloadStatus = DownloadStatus.IDLE;
        this.mRawURL = null;
        this.mData = null;
    }

    public void setmRawURL(String mRawURL) {
        this.mRawURL = mRawURL;
    }

    public String getmRawURL() {
        return mRawURL;
    }

    public String getmData() {
        return mData;
    }

    public DownloadStatus getmDownloadStatus() {
        return mDownloadStatus;
    }

    public void execute() {
        this.mDownloadStatus = DownloadStatus.PROCESSING;
        DownloadData downloadData = new DownloadData();
        downloadData.execute(mRawURL);
    }

    public class DownloadData extends AsyncTask<String, Void, String> {

        protected void onPostExecute(String webData){

            mData = webData;

            if(mData == null){
                if(mRawURL == null){
                    mDownloadStatus = DownloadStatus.NOT_INITITALIZED;
                }
                else{
                    mDownloadStatus = DownloadStatus.FAILED_OR_EMPTY;
                }
            }
            else{
                mDownloadStatus = DownloadStatus.IDLE.OK;
            }
        }

        protected String doInBackground(String... params){
            BufferedReader reader = null;
            HttpURLConnection urlConnection = null;

            if(params == null){
                return null;
            }

            try{
                URL url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();

                if(inputStream == null){
                    return null;
                }

                StringBuffer buffer = new StringBuffer();

                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine()) != null){
                    buffer.append(line);
                }

                return buffer.toString();
            }
            catch (IOException e){
                Log.e(LOG_TAG, "error: "+e);
                return null;
            }
            finally{
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
                if(reader != null){
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "error closing stream "+e);
                    }
                }
            }
        }
    }
}


