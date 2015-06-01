package com.mon.bubu.yourtreat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.base.BaseActivity;
import com.mon.bubu.yourtreat.common.Utils;

/**
 * Created by Chuck on 2015. 6. 1..
 */
public class GameNStartActivity extends BaseActivity implements View.OnClickListener{

    LinearLayout linearLayoutGameNStart;

    EditText edit_game_n_amount, edit_game_n_number;
    Button btn_game_n_info, btn_game_n_home, btn_game_n_start;
    AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_n_start);

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

        linearLayoutGameNStart = (LinearLayout) findViewById(R.id.linearLayoutGameNStart);
        edit_game_n_amount = (EditText) findViewById(R.id.edit_game_n_amount);
        edit_game_n_number = (EditText) findViewById(R.id.edit_game_n_number);
        btn_game_n_info = (Button) findViewById(R.id.btn_game_n_info);
        btn_game_n_home = (Button) findViewById(R.id.btn_game_n_home);
        btn_game_n_start = (Button) findViewById(R.id.btn_game_n_start);
        linearLayoutGameNStart.setOnClickListener(this);
        btn_game_n_info.setOnClickListener(this);
        btn_game_n_home.setOnClickListener(this);
        btn_game_n_start.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.linearLayoutFragmentGameN:
                Utils.hideKeyboard(this);
                break;
            case R.id.btn_game_n_info:

                break;
            case R.id.btn_game_n_home:
                this.finish();
                break;
            case R.id.btn_game_n_start:

                int amount = 0;
                int number = 0;
                String strAmount = edit_game_n_amount.getText().toString().trim();
                String strNumber = edit_game_n_number.getText().toString().trim();

                if(strAmount == null || strAmount.length() == 0){
                    this.showAlertDialog("금액을 입력해 주세요.");
                    break;
                }
                if(strNumber == null || strNumber.length() == 0){
                    this.showAlertDialog("인원수를 입력해 주세요.");
                    break;
                }
                try{
                    amount = Integer.parseInt(strAmount);
                }catch(Exception ex){
                    this.showAlertDialog("금액 입력은 숫자만 가능합니다.");
                    break;
                }
                try{
                    number = Integer.parseInt(strNumber);
                }catch(Exception ex){
                    this.showAlertDialog("인원수 입력은 숫자만 가능합니다.");
                    break;
                }
                if(amount < 1000){
                    this.showAlertDialog("금액은 천원 이상 입력해 주세요.");
                    break;
                }
                if(amount > Integer.MAX_VALUE){
                    this.showAlertDialog("금액이 지나치게 많습니다. 도박은 금물!");
                    break;
                }
                if(number < 2 || number > 8){
                    this.showAlertDialog("인원수는 2명이상 8명이하로 입력해 주세요.");
                    break;
                }

                Intent intent = new Intent(this, GameNResultActivity.class);
                intent.putExtra("amount", amount);
                intent.putExtra("number", number);
                startActivity(intent);
                break;
            default:
                break;
        }

    }
}
