package com.mon.bubu.yourtreat;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.mon.bubu.yourtreat.base.BaseActivity;


public class GameSpeedResultActivity extends BaseActivity implements View.OnClickListener{

    private Button btnGameSpeedResultReplay, btnGameSpeedResultHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_speed_result);

        btnGameSpeedResultReplay = (Button) findViewById(R.id.btnGameSpeedResultReplay);
        btnGameSpeedResultReplay.setOnClickListener(this);
        btnGameSpeedResultHome = (Button) findViewById(R.id.btnGameSpeedResultHome);
        btnGameSpeedResultHome.setOnClickListener(this);

    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.btnGameSpeedResultReplay:
                this.finish();
                break;
            case R.id.btnGameSpeedResultHome:
                this.finish();
                break;
            default:
                break;
        }
    }

}
