package com.mon.bubu.yourtreat;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.base.BaseActivity;


public class HomeActivity extends BaseActivity{

    private Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Test Device : will be removed for production level
        TelephonyManager telephonyManager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = telephonyManager.getDeviceId();
        /*
         * AdMob
         */
        AdView mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)    // Test Device : will be removed for production level
                .addTestDevice(device_id)                       // Test Device : will be removed for production level
                .build();
        mAdView.loadAd(adRequest);

        getFragmentManager().beginTransaction()
                            .add(R.id.container, HomeFragment.getInstance())
                            .commit();
    }

    @Override
    public void onBackPressed(){
        if(getCurrentFragment() instanceof GameCatsFragment){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, HomeFragment.getInstance())
                    .commit();
        }else if(getCurrentFragment() instanceof GameNFragment){
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, HomeFragment.getInstance())
                    .commit();
        }else{
            super.onBackPressed();
        }
    }

    protected void setCurrentFragment(Fragment fragment){
        this.currentFragment = fragment;
    }

    protected Fragment getCurrentFragment(){
        return this.currentFragment;
    }

}
