package com.mon.bubu.yourtreat;

import android.app.Fragment;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mon.bubu.yourtreat.common.Constants;
import com.mon.bubu.yourtreat.common.Utils;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


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

    private final int QUIZ_NUM_OPERATORS = 2;
    private final int QUIZ_NUM_OPERAND = 3;
    private final int QUIZ_MAX_OPERAND = 9;

    private String quizOperators[];
    private int quizOperands[];

    private int answers[];
    private int rightPos;

    private ProgressBar progressGameArithmeticTimer;
    private TextView txtGameArithmeticTimer;

    private TimerTask timerTask;
    private Timer timer;
    private int timeLeft;

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

        quizOperators = new String[QUIZ_NUM_OPERATORS];
        quizOperands = new int[QUIZ_NUM_OPERAND];
        answers = new int[QUIZ_NUM_OPERAND];

        progressGameArithmeticTimer = (ProgressBar) v.findViewById(R.id.progressGameArithmeticTimer);
        txtGameArithmeticTimer = (TextView) v.findViewById(R.id.txtGameArithmeticTimer);

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        ((HomeActivity)this.getActivity()).setCurrentFragment(this);


        timeLeft = 30;
        System.out.println(timeLeft);
        txtGameArithmeticTimer.setText(Integer.toString(timeLeft));
        progressGameArithmeticTimer.setProgress(30-timeLeft);

        Runnable task = new Runnable() {
            @Override
            public void run() {
                while(timeLeft > 0){
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException ex){

                    }
                    timeLeft--;
                    System.out.println(timeLeft);
                    handler.sendEmptyMessage(1);
                }

            }
        };

/*
        timerTask = new TimerTask() {
            @Override
            public void run() {

                if(timeLeft < 1){
                    timer.cancel();
                }
            }
        };
        timer = new Timer();
        timer.schedule(timerTask, 1000);*/

        resetQuiz();

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

    private void resetQuiz(){
        Random generator = new Random();
        for(int i=0;i<quizOperators.length;i++){
            quizOperators[i] = Constants.getOperator(generator.nextInt(Constants.getOperatorsNum()));
        }
        for(int i=0;i<quizOperands.length;i++){
            quizOperands[i] = generator.nextInt(QUIZ_MAX_OPERAND) + 1;
        }

        rightPos = generator.nextInt(QUIZ_NUM_OPERAND);
        if(Constants.getOperatorMultiplication().equals(quizOperators[1])){
            answers[rightPos] = Utils.doArithmetic(quizOperands[0], Utils.doArithmetic(quizOperands[1], quizOperands[2], quizOperators[1]), quizOperators[0]);
        }else{
            answers[rightPos] = Utils.doArithmetic(Utils.doArithmetic(quizOperands[0], quizOperands[1], quizOperators[0]), quizOperands[2], quizOperators[1]);
        }

        int variation = quizOperands[generator.nextInt(2)];
        switch (rightPos){
            case 0:
                answers[rightPos+1] = answers[rightPos] + variation;
                answers[rightPos+2] = answers[rightPos+1] + variation;
                break;
            case 1:
                answers[rightPos-1] = answers[rightPos] - variation;
                answers[rightPos+1] = answers[rightPos] + variation;
                break;
            case 2:
                answers[rightPos-1] = answers[rightPos] - variation;
                answers[rightPos-2] = answers[rightPos-1] - variation;
                break;
            default:
                break;
        }
    }

    final Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg){
            if(msg.what == 0){
                txtGameArithmeticTimer.setText(Integer.toString(timeLeft));
                progressGameArithmeticTimer.setProgress(30-timeLeft);
            }

        }
    };


}

