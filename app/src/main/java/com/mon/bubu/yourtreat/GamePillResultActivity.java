package com.mon.bubu.yourtreat;

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


public class GamePillResultActivity extends BaseActivity implements View.OnClickListener{

    AdView adView;
    TextView txt_company;

    Button btn_game_play_again, btn_game_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_pill_result);

        // Test Device : will be removed for production level
        TelephonyManager telephonyManager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = telephonyManager.getDeviceId();
        /*
         * AdMob
         */
        adView = (AdView) findViewById(R.id.adView);
        txt_company = (TextView) findViewById(R.id.txt_company);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)    // Test Device : will be removed for production level
                .addTestDevice(device_id)                       // Test Device : will be removed for production level
                .build();
        adView.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                txt_company.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                txt_company.setVisibility(View.VISIBLE);
            }
        });
        adView.loadAd(adRequest);

        btn_game_play_again = (Button) findViewById(R.id.btn_game_play_again);
        btn_game_play_again.setOnClickListener(this);
        btn_game_menu = (Button) findViewById(R.id.btn_game_menu);
        btn_game_menu.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_game_play_again:
                finish();
                overridePendingTransition(R.anim.back_in_from_left, R.anim.back_out_to_right);
                break;
            case R.id.btn_game_menu:
                Intent intent = new Intent(this, HomeActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.back_in_from_left, R.anim.back_out_to_right);
                break;
            default:
                break;
        }
    }

}
