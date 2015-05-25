package com.mon.bubu.yourtreat;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.mon.bubu.yourtreat.base.BaseActivity;


public class GameMacaroonResultActivity extends BaseActivity implements View.OnClickListener{

    ImageView imgGameNClose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_macaroon_result);

        imgGameNClose = (ImageView) findViewById(R.id.imgGameNClose);
        imgGameNClose.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.imgGameNClose:
                finish();
                break;
            default:
                break;
        }
    }

}
