package com.mon.bubu.yourtreat;

import android.os.Bundle;

import com.mon.bubu.yourtreat.base.BaseActivity;


public class GameNResultActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_nresult);

        showAlertDialog("AA");
    }

}
