package com.mon.bubu.yourtreat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.base.BaseActivity;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Chuck on 2015. 6. 1..
 */
public class GamePillPlayActivity extends BaseActivity implements View.OnClickListener{

    AdView adView;
    TextView txt_company;
    Button btn_info;
    RelativeLayout relative_info;
    CheckBox checkbox_info;

    SharedPreferences mPref;

    Integer idx_imgMacaroon[] = {
            R.id.imgGameMacaroon00,R.id.imgGameMacaroon01,R.id.imgGameMacaroon02,R.id.imgGameMacaroon03,
            R.id.imgGameMacaroon04,R.id.imgGameMacaroon05,R.id.imgGameMacaroon06,R.id.imgGameMacaroon07,
            R.id.imgGameMacaroon08,R.id.imgGameMacaroon09,R.id.imgGameMacaroon10,R.id.imgGameMacaroon11,
            R.id.imgGameMacaroon12,R.id.imgGameMacaroon13,R.id.imgGameMacaroon14,R.id.imgGameMacaroon15,
            R.id.imgGameMacaroon16,R.id.imgGameMacaroon17,R.id.imgGameMacaroon18,R.id.imgGameMacaroon19,
            R.id.imgGameMacaroon20,R.id.imgGameMacaroon21,R.id.imgGameMacaroon22,R.id.imgGameMacaroon23
    };

    final int macaroonCount = idx_imgMacaroon.length;
    int wasabiPosition;

    ImageView imgMacaroon[];
    boolean isImgMacaroonSelected[];

    Vibrator vibe;
    ImageView imgGamePillAniCat, imgGamePillAniBeanDown, imgGamePillAniBeanUp;
    Animation jump01Animation;
    Animation jump01BackAnimation;
    Animation jump02Animation;
    Animation jump02BackAnimation;
    Animation jump03Animation;
    Animation jump03BackAnimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_pill_play);

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

        mPref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String isChecked = mPref.getString("dontShowInfoPill", "N");
        btn_info = (Button) findViewById(R.id.btn_info);
        btn_info.setOnClickListener(this);
        relative_info = (RelativeLayout) findViewById(R.id.relative_info);
        checkbox_info = (CheckBox) findViewById(R.id.checkbox_info);
        checkbox_info.setOnClickListener(this);
        if("Y".equals(isChecked)){
            checkbox_info.setChecked(true);
            relative_info.setVisibility(View.GONE);
        }else{
            checkbox_info.setChecked(false);
            relative_info.setVisibility(View.VISIBLE);
        }

        imgMacaroon = new ImageView[macaroonCount];
        isImgMacaroonSelected = new boolean[macaroonCount];

        for(int i=0; i<macaroonCount; i++){
            imgMacaroon[i] = (ImageView) findViewById(idx_imgMacaroon[i]);
            imgMacaroon[i].setOnClickListener(this);
        }

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        imgGamePillAniCat = (ImageView) findViewById(R.id.imgGamePillAniCat);
        imgGamePillAniBeanDown = (ImageView) findViewById(R.id.imgGamePillAniBeanDown);
        imgGamePillAniBeanUp = (ImageView) findViewById(R.id.imgGamePillAniBeanUp);

        jump01Animation = AnimationUtils.loadAnimation(this, R.anim.jump01);
        jump01BackAnimation = AnimationUtils.loadAnimation(this, R.anim.jump01_back);
        jump02Animation = AnimationUtils.loadAnimation(this, R.anim.jump02);
        jump02BackAnimation = AnimationUtils.loadAnimation(this, R.anim.jump02_back);
        jump03Animation = AnimationUtils.loadAnimation(this, R.anim.jump03);
        jump03BackAnimation = AnimationUtils.loadAnimation(this, R.anim.jump03_back);
    }

    @Override
    public void onResume(){
        super.onResume();
        Random generator = new Random();
        wasabiPosition = generator.nextInt(macaroonCount);

        for(int i=0; i<macaroonCount; i++){
            isImgMacaroonSelected[i] = false;
            imgMacaroon[i].setImageResource(R.drawable.game_pill_btn_pill_yellow_selector);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_info:
                if(relative_info.getVisibility() == View.VISIBLE){
                    relative_info.setVisibility(View.GONE);
                }else if(relative_info.getVisibility() == View.GONE){
                    relative_info.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.checkbox_info:
                SharedPreferences.Editor editor = mPref.edit();
                if(checkbox_info.isChecked()){
                    editor.putString("dontShowInfoPill", "Y");
                }else{
                    editor.putString("dontShowInfoPill", "N");
                }
                editor.commit();
                break;
            default:
                relative_info.setVisibility(View.GONE);
                int buttonID = v.getId();
                int idxArray = Arrays.asList(idx_imgMacaroon).indexOf(buttonID);

                if(idxArray > -1){
                    if(wasabiPosition == idxArray){
                        vibe.vibrate(1000);
                        imgMacaroon[idxArray].setImageResource(R.drawable.game_pill_img_pill_empty);
                        Intent intent = new Intent(this, GamePillResultActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.next_in_from_right, R.anim.next_out_to_left);
                    }else{
                        if(isImgMacaroonSelected[idxArray]){
                            AlertDialog.Builder alert = new AlertDialog.Builder(this);
                            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();     //닫기
                                }
                            });
                            alert.setMessage("비어있는 알약입니다.");
                            alert.show();
                        }else{
                            imgGamePillAniCat.startAnimation(jump01Animation);
                            imgGamePillAniCat.startAnimation(jump01BackAnimation);
                            imgGamePillAniBeanDown.startAnimation(jump02Animation);
                            imgGamePillAniBeanDown.startAnimation(jump02BackAnimation);
                            imgGamePillAniBeanUp.startAnimation(jump03Animation);
                            imgGamePillAniBeanUp.startAnimation(jump03BackAnimation);
                            vibe.vibrate(100);
                            imgMacaroon[idxArray].setImageResource(R.drawable.game_pill_img_pill_empty);
                            isImgMacaroonSelected[idxArray] = true;
                        }
                    }
                }
                break;
        }
    }

}
