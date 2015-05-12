package com.mon.bubu.yourtreat;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;


public class HomeActivity extends Activity{

    HomeFragment homeFragment;

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

        // Home Fragment Instance
        homeFragment = HomeFragment.newInstance();
        getFragmentManager().beginTransaction()
                            .add(R.id.container, homeFragment)
                            .commit();
    }

}
