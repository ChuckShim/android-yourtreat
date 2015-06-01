package com.mon.bubu.yourtreat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.base.BaseActivity;
import com.mon.bubu.yourtreat.common.Utils;
import com.mon.bubu.yourtreat.common.ViewPagerAdapter;

/**
 * Created by Chuck on 2015. 6. 1..
 */
public class GameMacaroonStartActivity extends BaseActivity implements View.OnClickListener{

    // View Pager
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    int[] idx_tutorialImages = {R.drawable.game_speed_result_message, R.drawable.game_macaroon_result};
    int tutorialCount;
    LinearLayout layoutTutorialNavi;
    static ImageView naviImages[];
    int[] idx_naviImages = {R.drawable.pager_navi_off, R.drawable.pager_navi_on};

    Button btn_game_n_start;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_macaroon_start);

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

        // View Pager
        viewPager = (ViewPager) findViewById(R.id.introPager);
        pagerAdapter = new ViewPagerAdapter(this, idx_tutorialImages);
        viewPager.setAdapter(pagerAdapter);

        layoutTutorialNavi = (LinearLayout) findViewById(R.id.introCount);
        tutorialCount = viewPager.getAdapter().getCount();
        naviImages = new ImageView[tutorialCount];
        for(int i=0; i<tutorialCount; i++){
            naviImages[i] = new ImageView(this);
            naviImages[i].setImageResource(idx_naviImages[0]);
            layoutTutorialNavi.addView(naviImages[i]);
        }
        naviImages[0].setImageResource(idx_naviImages[1]);

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){
            }

            @Override
            public void onPageSelected(int position){
                for(int i=0; i<tutorialCount; i++){
                    naviImages[i].setImageResource(idx_naviImages[0]);
                }
                naviImages[position].setImageResource(idx_naviImages[1]);
            }

            @Override
            public void onPageScrollStateChanged(int state){
            }
        });

        btn_game_n_start = (Button) findViewById(R.id.btn_game_n_start);
        btn_game_n_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_game_n_start:
                Intent intent = new Intent(this, GameMacaroonPlayActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
