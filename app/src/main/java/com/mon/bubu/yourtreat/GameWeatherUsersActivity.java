package com.mon.bubu.yourtreat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.base.BaseActivity;


/**
 * Created by Chuck on 2015. 6. 1..
 */
public class GameWeatherUsersActivity extends BaseActivity implements View.OnClickListener{

    int users;
    final int _MiN_USERS_ = 2;
    final int _MAX_USERS_ = 8;

    TextView txt_game_weather_users;
    Button btn_game_weather_minus, btn_game_weather_plus, btn_game_next;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_weather_users);

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

        txt_game_weather_users = (TextView) findViewById(R.id.txt_game_weather_users);
        btn_game_weather_minus = (Button) findViewById(R.id.btn_game_weather_minus);
        btn_game_weather_minus.setOnClickListener(this);
        btn_game_weather_plus = (Button) findViewById(R.id.btn_game_weather_plus);
        btn_game_weather_plus.setOnClickListener(this);
        btn_game_next = (Button) findViewById(R.id.btn_game_next);
        btn_game_next.setOnClickListener(this);

        users = 2;
        txt_game_weather_users.setText(Integer.toString(users));
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btn_game_next:
                Intent intent = new Intent(this, GameWeatherLoadCityActivity.class);
                intent.putExtra("users", users);
                startActivity(intent);
                break;
            case R.id.btn_game_weather_minus:
                if(users > _MiN_USERS_){
                    users--;
                    txt_game_weather_users.setText(Integer.toString(users));
                }else{
                    showAlertDialog("2명 이상 플레이 하여야 합니다.");
                }
                break;
            case R.id.btn_game_weather_plus:
                if(users < _MAX_USERS_){
                    users++;
                    txt_game_weather_users.setText(Integer.toString(users));
                }else{
                    showAlertDialog("8명 이상 플레이 할수 없습니다.");
                }
                break;
            default:
                break;
        }

    }
}
