package com.mon.bubu.yourtreat;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.mon.bubu.yourtreat.common.Utils;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Chuck on 2015. 4. 28..
 */
public class GameMacaroonFragment extends Fragment implements View.OnClickListener{
    private static GameMacaroonFragment gameMacaroonInstance = null;
    public GameMacaroonFragment() {}
    public static GameMacaroonFragment getInstance() {
        if(gameMacaroonInstance == null){
            gameMacaroonInstance = new GameMacaroonFragment();
        }
        return gameMacaroonInstance;
    }

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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_game_macaroon, container, false);

        imgMacaroon = new ImageView[macaroonCount];
        isImgMacaroonSelected = new boolean[macaroonCount];

        for(int i=0; i<macaroonCount; i++){
            imgMacaroon[i] = (ImageView) v.findViewById(idx_imgMacaroon[i]);
            imgMacaroon[i].setOnClickListener(this);
        }

        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ((HomeActivity)this.getActivity()).setCurrentFragment(this);
        Random generator = new Random();
        wasabiPosition = generator.nextInt(macaroonCount);

        for(int i=0; i<macaroonCount; i++){
            imgMacaroon[i].setImageResource(R.drawable.game_macaroon_green_before);
            isImgMacaroonSelected[i] = false;
        }

    }

    @Override
    public void onClick(View v){

        switch(v.getId()) {
            case R.id.btn_game_n_info:

                break;
            case R.id.btn_game_n_home:
                getFragmentManager().beginTransaction()
                        .replace(R.id.container, HomeFragment.getInstance())
                        .commit();
                break;
            default:
                int buttonID = v.getId();
                int idxArray = Arrays.asList(idx_imgMacaroon).indexOf(buttonID);

                if(idxArray > -1){
                    if(wasabiPosition == idxArray){
                        vibe.vibrate(1000);
                        imgMacaroon[idxArray].setImageResource(R.drawable.game_macaroon_green_after);
                        Intent intent = new Intent(this.getActivity(), GameMacaroonResultActivity.class);
                        startActivity(intent);
                    }else{
                        if(isImgMacaroonSelected[idxArray]){
                            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
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
