package com.mon.bubu.yourtreat;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Chuck on 2015. 4. 28..
 */
public class GameCatsFragment extends Fragment implements View.OnClickListener{

    Integer idx_btnCats[] = {
            R.id.btnCats00, R.id.btnCats01, R.id.btnCats02, R.id.btnCats03, R.id.btnCats04,
            R.id.btnCats05, R.id.btnCats06, R.id.btnCats07, R.id.btnCats08, R.id.btnCats09,
            R.id.btnCats10, R.id.btnCats11, R.id.btnCats12, R.id.btnCats13, R.id.btnCats14,
            R.id.btnCats15, R.id.btnCats16, R.id.btnCats17, R.id.btnCats18, R.id.btnCats19
    };

    final int catsCount = idx_btnCats.length;
    int tigerPosition;

    Button btnCats[];
    boolean isBtnCatsSelected[];

    public GameCatsFragment() {}

    public static GameCatsFragment newInstance() {
        return new GameCatsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_game_cats, container, false);

        btnCats = new Button[catsCount];
        isBtnCatsSelected = new boolean[catsCount];

        for(int i=0; i<catsCount; i++){
            btnCats[i] = (Button) v.findViewById(idx_btnCats[i]);
            btnCats[i].setOnClickListener(this);
        }

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        Random generator = new Random();
        tigerPosition = generator.nextInt(catsCount);

        for(int i=0; i<catsCount; i++){
            btnCats[i].setBackgroundResource(R.drawable.btn_circle_gray);
            isBtnCatsSelected[i] = false;
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<catsCount; i++) {
                    btnCats[i].setBackgroundResource(R.drawable.btn_circle_transparent);
                }
            }
        }, 500);

    }

    @Override
    public void onClick(View v){

        int buttonID = v.getId();
        int idxArray = Arrays.asList(idx_btnCats).indexOf(buttonID);

        if(idxArray > -1){
            if(tigerPosition == idxArray){
                btnCats[idxArray].setBackgroundResource(R.drawable.btn_circle_red);

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();     //닫기
                        onResume();
                    }
                });
                alert.setMessage("커피 쏴!!! 두번 쏴!!!");
                alert.show();
            }else{
                if(isBtnCatsSelected[idxArray]){
                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    alert.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();     //닫기
                        }
                    });
                    alert.setMessage("이미 누른 버튼입니다.");
                    alert.show();
                }else{
                    btnCats[idxArray].setBackgroundResource(R.drawable.btn_circle_gray);
                    isBtnCatsSelected[idxArray] = true;
                }
            }
        }

    }

}
