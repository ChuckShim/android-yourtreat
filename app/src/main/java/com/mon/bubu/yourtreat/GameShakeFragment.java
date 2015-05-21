package com.mon.bubu.yourtreat;

import android.app.Fragment;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by Chuck on 2015. 4. 28..
 */
public class GameShakeFragment extends Fragment implements View.OnClickListener, SensorEventListener{
    private static GameShakeFragment gameShakeInstance = null;
    public GameShakeFragment() {}
    public static GameShakeFragment getInstance() {
        if(gameShakeInstance == null){
            gameShakeInstance = new GameShakeFragment();
        }
        return gameShakeInstance;
    }

    EditText edit_game_shake_count;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_game_shake, container, false);


        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ((HomeActivity)this.getActivity()).setCurrentFragment(this);
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
                break;
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onSensorChanged(SensorEvent event){

    }

}
