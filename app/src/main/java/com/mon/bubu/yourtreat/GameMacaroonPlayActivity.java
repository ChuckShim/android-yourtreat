package com.mon.bubu.yourtreat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
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

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Chuck on 2015. 6. 1..
 */
public class GameMacaroonPlayActivity extends BaseActivity implements View.OnClickListener{

    Integer idx_imgMacaroon[] = {
            R.id.imgGameMacaroon00,R.id.imgGameMacaroon01,R.id.imgGameMacaroon02,R.id.imgGameMacaroon03,
            R.id.imgGameMacaroon04,R.id.imgGameMacaroon05,R.id.imgGameMacaroon06,R.id.imgGameMacaroon07,
            R.id.imgGameMacaroon08,R.id.imgGameMacaroon09,R.id.imgGameMacaroon10,R.id.imgGameMacaroon11,
            R.id.imgGameMacaroon12,R.id.imgGameMacaroon13,R.id.imgGameMacaroon14,R.id.imgGameMacaroon15,
            R.id.imgGameMacaroon16,R.id.imgGameMacaroon17,R.id.imgGameMacaroon18,R.id.imgGameMacaroon19
    };

    final int macaroonCount = idx_imgMacaroon.length;
    int wasabiPosition;

    ImageView imgMacaroon[];
    boolean isImgMacaroonSelected[];

    Vibrator vibe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_macaroon_play);

        imgMacaroon = new ImageView[macaroonCount];
        isImgMacaroonSelected = new boolean[macaroonCount];

        for(int i=0; i<macaroonCount; i++){
            imgMacaroon[i] = (ImageView) findViewById(idx_imgMacaroon[i]);
            imgMacaroon[i].setOnClickListener(this);
        }

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        Random generator = new Random();
        wasabiPosition = generator.nextInt(macaroonCount);

        for(int i=0; i<macaroonCount; i++){
            imgMacaroon[i].setImageResource(R.drawable.game_macaroon_green_before);
            isImgMacaroonSelected[i] = false;
        }


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btn_game_n_info:

                break;
            case R.id.btn_game_n_home:
                this.finish();
                break;
            default:
                int buttonID = v.getId();
                int idxArray = Arrays.asList(idx_imgMacaroon).indexOf(buttonID);

                if(idxArray > -1){
                    if(wasabiPosition == idxArray){
                        vibe.vibrate(1000);
                        imgMacaroon[idxArray].setImageResource(R.drawable.game_macaroon_green_after);
                        Intent intent = new Intent(this, GameMacaroonResultActivity.class);
                        startActivity(intent);
                    }else{
                        if(isImgMacaroonSelected[idxArray]){
                            AlertDialog.Builder alert = new AlertDialog.Builder(this);
                            alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();     //닫기
                                }
                            });
                            alert.setMessage("이미 먹은 마카롱입니다.");
                            alert.show();
                        }else{
                            vibe.vibrate(100);
                            imgMacaroon[idxArray].setImageResource(R.drawable.game_macaroon_green_after);
                            isImgMacaroonSelected[idxArray] = true;
                        }
                    }
                }
                break;
        }
    }

}
