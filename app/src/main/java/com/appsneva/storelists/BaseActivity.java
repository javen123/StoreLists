package com.appsneva.storelists;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by javen on 8/18/15.
 */
public class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    protected Toolbar activateToolbar() {
        if(mToolbar == null){
            mToolbar = (Toolbar)findViewById(R.id.app_bar);
            if(mToolbar != null){
                setSupportActionBar(mToolbar);
                mToolbar.setTitleTextColor(R.color.accent);

            }
        }
        return mToolbar;
    }

    protected Toolbar activateToolbarWithHomeEnabled(){
        activateToolbar();

        if(mToolbar != null){


            getSupportActionBar().setDisplayHomeAsUpEnabled(true);//returns back arrow for home in appbar

        }
        return mToolbar;
    }

}
