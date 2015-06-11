package com.mon.bubu.yourtreat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.telephony.TelephonyManager;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.base.BaseActivity;
import com.mon.bubu.yourtreat.common.Constants;
import com.mon.bubu.yourtreat.common.Utils;
import com.mon.bubu.yourtreat.common.ViewPagerAdapter;

/**
 * Created by Chuck on 2015. 6. 1..
 */
public class GameWeatherStartActivity extends BaseActivity implements View.OnClickListener{

    // View Pager
    ViewPager viewPager;
    PagerAdapter pagerAdapter;
    int[] idx_tutorialImages = {R.drawable.game_speed_result_message};
    int tutorialCount;
    LinearLayout layoutTutorialNavi;
    static ImageView naviImages[];
    int[] idx_naviImages = {R.drawable.common_pager_navi_gray, R.drawable.common_pager_navi_white};

    Button btn_game_start;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_weather_start);

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
        int naviImagesDp = 20;
        float scale = getBaseContext().getResources().getDisplayMetrics().density;
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, naviImagesDp, getApplicationContext().getResources().getDisplayMetrics()
        );
        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(px, px);
        imageParams.leftMargin = px/4;
        imageParams.rightMargin = px/4;
        for(int i=0; i<tutorialCount; i++){
            naviImages[i] = new ImageView(this);
            naviImages[i].setLayoutParams(imageParams);
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

        btn_game_start = (Button) findViewById(R.id.btn_game_start);
        btn_game_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_game_start:
                if(Utils.isNetworkAvailable(getApplicationContext())){
                    Intent intent = new Intent(this, GameWeatherUsersActivity.class);
                    startActivity(intent);
                }else{
                    showAlertDialog(Constants.getMessageNoNetwork());
                }
                break;
            default:
                break;
        }

    }
}
