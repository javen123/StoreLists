package com.appsneva.storelists;

import java.io.Serializable;

/**
 * Created by javen on 8/17/15.
 */
public class Store implements Serializable {


    String mName;
    String mAddress;
    String mCity;
    String mState;
    String mZipcode;
    String mLatitude;
    String mLongitude;
    String mPhone;
    String mStoreId;
    String mLogoUrl;

    public Store(String mName, String mAddress, String mCity, String mState,String mZipcode, String mLatitude, String mLongitude, String mPhone, String mStoreId, String mLogoUrl) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mCity = mCity;
        this.mState = mState;
        this.mLatitude = mLatitude;
        this.mLongitude = mLongitude;
        this.mPhone = mPhone;
        this.mStoreId = mStoreId;
        this.mLogoUrl = mLogoUrl;
        this.mZipcode = mZipcode;
    }

    public String getmName() {
        return mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public String getmCity() {
        return mCity;
    }

    public String getmState() {
        return mState;
    }
    public String getmZipcode() {
        return mZipcode;
    }

    public String getmLatitude() {
        return mLatitude;
    }

    public String getmLongitude() {
        return mLongitude;
    }

    public String getmPhone() {
        return mPhone;
    }

    public String getmStoreId() {
        return mStoreId;
    }

    public String getmLogoUrl() {
        return mLogoUrl;
    }

    @Override
    public String toString() {
        return "Store{" +
                "mAddress='" + mAddress + '\'' +
                ", mCity='" + mCity + '\'' +
                ", mState='" + mState + '\'' +
                ", mLatitude=" + mLatitude +
                ", mLongitude=" + mLongitude +
                ", mPhone='" + mPhone + '\'' +
                ", mStoreId=" + mStoreId +
                ", mLogoUrl='" + mLogoUrl + '\'' +
                '}';
    }
}
