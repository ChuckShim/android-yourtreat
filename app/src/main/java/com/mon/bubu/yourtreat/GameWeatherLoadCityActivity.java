package com.mon.bubu.yourtreat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.widget.ImageView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.async.SecTimerTask;
import com.mon.bubu.yourtreat.base.BaseActivity;

import java.util.Timer;

/**
 * Created by Chuck on 2015. 6. 1..
 */
public class GameWeatherLoadCityActivity extends BaseActivity {

    int users;

    AdView adView;

    int[] idx_loadingImages = {R.drawable.common_pager_navi_white, R.drawable.common_pager_navi_gray, R.drawable.common_pager_navi_dark};
    ImageView loadingImages[];

    int timeCount;
    Timer timeTable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_weather_load_city);

        // Test Device : will be removed for production level
        TelephonyManager telephonyManager = (TelephonyManager) getBaseContext().getSystemService(Context.TELEPHONY_SERVICE);
        String device_id = telephonyManager.getDeviceId();
        /*
         * AdMob
         */
        adView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)    // Test Device : will be removed for production level
                .addTestDevice(device_id)                       // Test Device : will be removed for production level
                .build();
        adView.loadAd(adRequest);

        Intent intent = getIntent();
        users = intent.getExtras().getInt("users");

        timeCount = 0;
        loadingImages = new ImageView[3];
        loadingImages[0] = (ImageView) findViewById(R.id.loading00);
        loadingImages[1] = (ImageView) findViewById(R.id.loading01);
        loadingImages[2] = (ImageView) findViewById(R.id.loading02);

        timeTable = new Timer();
        timeTable.schedule(stt, 100, 500);

    }

    SecTimerTask stt = new SecTimerTask(new SecTimerTask.TimeCallBack() {
        @Override
        public void callback() {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    timeCount++;
                    if(timeCount > 30){
                        goNext();
                    }
                    System.out.println(timeCount);
                    int modulus = timeCount%3;
                    if(modulus == 0){
                        loadingImages[0].setImageResource(idx_loadingImages[0]);
                        loadingImages[1].setImageResource(idx_loadingImages[1]);
                        loadingImages[2].setImageResource(idx_loadingImages[2]);
                    }else if(modulus == 1){
                        loadingImages[0].setImageResource(idx_loadingImages[2]);
                        loadingImages[1].setImageResource(idx_loadingImages[0]);
                        loadingImages[2].setImageResource(idx_loadingImages[1]);
                    }else if(modulus == 2){
                        loadingImages[0].setImageResource(idx_loadingImages[1]);
                        loadingImages[1].setImageResource(idx_loadingImages[2]);
                        loadingImages[2].setImageResource(idx_loadingImages[0]);
                    }
                    loadingImages[0].refreshDrawableState();
                    loadingImages[1].refreshDrawableState();
                    loadingImages[2].refreshDrawableState();
                }
            });
        }
    });

    @Override
    public void onBackPressed() {
       // 로딩화면 중 백키 허용 안함.
    }

    private void goNext(){
        stt.stopTimer();
        Intent intent = new Intent(this, GamePillPlayActivity.class);
        startActivity(intent);
        this.finish();
    }

}
