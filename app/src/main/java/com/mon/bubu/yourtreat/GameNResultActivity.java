package com.mon.bubu.yourtreat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mon.bubu.yourtreat.base.BaseActivity;

import java.util.Arrays;
import java.util.Random;


public class GameNResultActivity extends BaseActivity implements View.OnClickListener{

    Integer idx_imgMoney[] = {
            R.id.imgGameNMoney00, R.id.imgGameNMoney01, R.id.imgGameNMoney02, R.id.imgGameNMoney03,
            R.id.imgGameNMoney04, R.id.imgGameNMoney05, R.id.imgGameNMoney06, R.id.imgGameNMoney07
    };

    Integer idx_txtMoney[] = {
            R.id.txtGameNMoney00, R.id.txtGameNMoney01, R.id.txtGameNMoney02, R.id.txtGameNMoney03,
            R.id.txtGameNMoney04, R.id.txtGameNMoney05, R.id.txtGameNMoney06, R.id.txtGameNMoney07
    };

    private final int BASE_MONEY = 5;
    private final int BASE_TEN = 10;

    ImageView imgMoney[], imgGameNClose;
    TextView txtMoney[];
    int nMoney[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_n_result);

        imgGameNClose = (ImageView) findViewById(R.id.imgGameNClose);
        imgGameNClose.setOnClickListener(this);

        Intent intent = getIntent();
        int amount = intent.getExtras().getInt("amount");
        int number = intent.getExtras().getInt("number");

        if(amount < 1000 || amount > Integer.MAX_VALUE){
            finish();
        }
        if(number < 2 || number > 8){
            finish();
        }

        int amountDigits = 1;
        long tempAmount = amount;
        while(true){
            if(tempAmount >= BASE_TEN){
                tempAmount /= BASE_TEN;
                amountDigits++;
            }else{
                break;
            }
        }
        int amountUnit = BASE_MONEY;
        for(int i=0; i < amountDigits-3;i++){
            amountUnit *= BASE_TEN;
        }
        int maxPoint = amount/amountUnit;


        Random generator = new Random();
        imgMoney = new ImageView[number];
        txtMoney = new TextView[number];
        nMoney = new int[number];
        for(int i=0; i<number; i++){
            imgMoney[i] = (ImageView) findViewById(idx_imgMoney[i]);
            imgMoney[i].setImageResource(R.drawable.game_n_card_active);
            imgMoney[i].setOnClickListener(this);

            txtMoney[i] = (TextView) findViewById(idx_txtMoney[i]);

            if(i < number - 1 ){
                int point = generator.nextInt(maxPoint-(number-i));
                nMoney[i] = amountUnit * point;
                amount -= nMoney[i];
                maxPoint -= point;
            }else{
                int tempPosition = generator.nextInt(number);
                nMoney[i] = nMoney[tempPosition];
                nMoney[tempPosition] = amount;
            }

        }

    }

    @Override
    public void onBackPressed(){

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.imgGameNClose:
                finish();
                break;
            case R.id.imgGameNMoney00:
                displayResult(Arrays.asList(idx_imgMoney).indexOf(R.id.imgGameNMoney00));
                break;
            case R.id.imgGameNMoney01:
                displayResult(Arrays.asList(idx_imgMoney).indexOf(R.id.imgGameNMoney01));
                break;
            case R.id.imgGameNMoney02:
                displayResult(Arrays.asList(idx_imgMoney).indexOf(R.id.imgGameNMoney02));
                break;
            case R.id.imgGameNMoney03:
                displayResult(Arrays.asList(idx_imgMoney).indexOf(R.id.imgGameNMoney03));
                break;
            case R.id.imgGameNMoney04:
                displayResult(Arrays.asList(idx_imgMoney).indexOf(R.id.imgGameNMoney04));
                break;
            case R.id.imgGameNMoney05:
                displayResult(Arrays.asList(idx_imgMoney).indexOf(R.id.imgGameNMoney05));
                break;
            case R.id.imgGameNMoney06:
                displayResult(Arrays.asList(idx_imgMoney).indexOf(R.id.imgGameNMoney06));
                break;
            case R.id.imgGameNMoney07:
                displayResult(Arrays.asList(idx_imgMoney).indexOf(R.id.imgGameNMoney07));
                break;
            default:
                break;
        }

    }

    private void displayResult(int index){
        imgMoney[index].setImageResource(R.drawable.game_n_card_result);
        txtMoney[index].setText(Integer.toString(nMoney[index]));
    }


}
