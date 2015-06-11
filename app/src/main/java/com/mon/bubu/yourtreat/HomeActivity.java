package com.mon.bubu.yourtreat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.base.BaseActivity;
import com.mon.bubu.yourtreat.common.CityManager;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;


public class HomeActivity extends BaseActivity implements View.OnClickListener{

    private TextView txt_home_company;

    Button btn_navi_game_weather, btn_navi_game_cats, btn_navi_game_n, btn_navi_game_shake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        SharedPreferences mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String isJsonLoaded = mPref.getString("isJsonLoaded", "N");
        if("N".equals(isJsonLoaded)){
            try{
                //CityManager.getInstance(getApplicationContext()).deleteAll();

                InputStream is = getApplicationContext().getAssets().open("cities.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();

                String bufferString = new String(buffer);
                JSONObject jsonObject = new JSONObject(bufferString);
                JSONArray jsonArray = jsonObject.getJSONArray("cities");

                int jsonSize = jsonArray.length();
                JSONObject item;
                for(int i=0;i<jsonSize;i++){
                    item = jsonArray.getJSONObject(i);
                    System.out.println(item);
                    CityManager.getInstance(getApplicationContext()).save(item.getString("name"), item.getString("country"), item.getLong("latitude"), item.getLong("longitude"));
                }

                SharedPreferences.Editor editor = mPref.edit();
                editor.putString("isJsonLoaded", "Y");
                editor.commit();
                Toast.makeText(getApplicationContext(), "도시정보 데이터가 로드되었습니다.", Toast.LENGTH_SHORT).show();
            }catch (Exception ex){
                ex.printStackTrace();
                Toast.makeText(getApplicationContext(), "도시정보 데이터 로드가 실패하였습니다. 어플리케이션을 다시 실행해 주십시오.", Toast.LENGTH_SHORT).show();
            };
        }



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
                intent = new Intent(this, GameWeatherStartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.next_in_from_right, R.anim.next_out_to_left);
                break;
            case R.id.btn_navi_game_cats:
                intent = new Intent(this, GamePillPlayActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.next_in_from_right, R.anim.next_out_to_left);
                break;
            case R.id.btn_navi_game_n:
                intent = new Intent(this, GameNStartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.next_in_from_right, R.anim.next_out_to_left);
                break;
            case R.id.btn_navi_game_shake:
                intent = new Intent(this, GameSpeedStartActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.next_in_from_right, R.anim.next_out_to_left);
                break;
            default:
                break;
        }

    }


}
