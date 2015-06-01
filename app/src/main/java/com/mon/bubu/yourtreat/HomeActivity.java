package com.mon.bubu.yourtreat;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.base.BaseActivity;


public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private TextView txt_home_company;

    Button btn_navi_game_weather, btn_navi_game_cats, btn_navi_game_n, btn_navi_game_shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txt_home_company = (TextView) findViewById(R.id.txt_home_company);

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
        mAdView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                txt_home_company.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                txt_home_company.setVisibility(View.VISIBLE);
            }
        });
        mAdView.loadAd(adRequest);

        // Menu Buttons
        btn_navi_game_weather = (Button) findViewById(R.id.btn_navi_game_weather);
        btn_navi_game_cats = (Button) findViewById(R.id.btn_navi_game_cats);
        btn_navi_game_n = (Button) findViewById(R.id.btn_navi_game_n);
        btn_navi_game_shake = (Button) findViewById(R.id.btn_navi_game_shake);

        btn_navi_game_weather.setOnClickListener(this);
        btn_navi_game_cats.setOnClickListener(this);
        btn_navi_game_n.setOnClickListener(this);
        btn_navi_game_shake.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){

        Intent intent;

        switch(v.getId()){
            case R.id.btn_navi_game_weather:

                break;
            case R.id.btn_navi_game_cats:
                intent = new Intent(this, GameMacaroonStartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_navi_game_n:
                intent = new Intent(this, GameNStartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_navi_game_shake:
                intent = new Intent(this, GameSpeedStartActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }


}
