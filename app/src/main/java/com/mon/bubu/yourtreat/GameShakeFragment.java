package com.mon.bubu.yourtreat;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


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

    int shakeCount;
    TextView edit_game_shake_count;

    private long lastTime;
    private float speed;
    private float lastX;
    private float lastY;
    private float lastZ;
    private float x, y, z;

    private static final int SHAKE_THRESHOLD = 5000;

    private static final int DATA_X = SensorManager.DATA_X;
    private static final int DATA_Y = SensorManager.DATA_Y;
    private static final int DATA_Z = SensorManager.DATA_Z;

    private SensorManager sensorManager;
    private Sensor accelerometerSensor;
    private Vibrator vibe;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_game_shake, container, false);

        shakeCount = 0;
        edit_game_shake_count = (TextView) v.findViewById(R.id.edit_game_shake_count);

        sensorManager = (SensorManager) getActivity().getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        vibe = (Vibrator) getActivity().getSystemService(Context.VIBRATOR_SERVICE);

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
    public void onStart()
    {
        super.onStart();
        if (accelerometerSensor != null)
            sensorManager.registerListener(this, accelerometerSensor,
                    SensorManager.SENSOR_DELAY_GAME);
    }




    @Override
    public void onStop()
    {
        super.onStop();
        if (sensorManager != null)
            sensorManager.unregisterListener(this);
    }


    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

    @Override
    public void onSensorChanged(SensorEvent event){
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            long currentTime = System.currentTimeMillis();
            long gabOfTime = (currentTime - lastTime);




            if (gabOfTime > 100)
            {
                lastTime = currentTime;
                x = event.values[SensorManager.DATA_X];
                y = event.values[SensorManager.DATA_Y];
                z = event.values[SensorManager.DATA_Z];

                speed = Math.abs(x + y + z - lastX - lastY - lastZ) / gabOfTime * 10000;




                if (speed > SHAKE_THRESHOLD)
                {
                    shakeCount++;
                    edit_game_shake_count.setText(Integer.toString(shakeCount));
                    vibe.vibrate(100);

                }




                lastX = event.values[DATA_X];
                lastY = event.values[DATA_Y];
                lastZ = event.values[DATA_Z];
            }
        }
    }


}

