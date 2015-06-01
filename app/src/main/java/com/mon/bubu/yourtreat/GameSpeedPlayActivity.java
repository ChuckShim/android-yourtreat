package com.mon.bubu.yourtreat;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Vibrator;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mon.bubu.yourtreat.async.SecTimerTask;
import com.mon.bubu.yourtreat.base.BaseActivity;
import com.mon.bubu.yourtreat.common.Constants;
import com.mon.bubu.yourtreat.common.Utils;

import java.util.Random;
import java.util.Timer;


public class GameSpeedPlayActivity extends BaseActivity implements View.OnClickListener{

    private final int TIMER_MAX = 30;
    private final int QUIZ_NUM_OPERATORS = 2;
    private final int QUIZ_NUM_OPERAND = 3;
    private final int QUIZ_MAX_OPERAND = 9;
    private final int QUIZ_NUM_ANSWERS = 3;

    private String quizOperators[];
    private int quizOperands[];
    private int answers[];
    private int rightPos;

    private ImageView imgGameNClose;
    private ProgressBar progressGameSpeedTimer;
    private TextView txtGameSpeedTimer, txtGameSpeedQuestion;
    private Button btnGameSpeedFollowing00, btnGameSpeedFollowing01, btnGameSpeedFollowing02;
    private View layoutToast;
    private Toast toast;
    private Handler toastHandler;
    private Vibrator vibe;

    private Timer timer;
    private int timeLeft;

    private Resources res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_speed_play);

        res = getResources();
        LayoutInflater inflater = getLayoutInflater();
        layoutToast = inflater.inflate(R.layout.activity_game_speed_toast, (ViewGroup)findViewById(R.id.linearLayoutGameSpeedToast));
        toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layoutToast);
        toastHandler = new Handler();

        imgGameNClose = (ImageView) findViewById(R.id.imgGameNClose);
        imgGameNClose.setOnClickListener(this);
        progressGameSpeedTimer = (ProgressBar) findViewById(R.id.progressGameSpeedTimer);
        txtGameSpeedTimer = (TextView) findViewById(R.id.txtGameSpeedTimer);
        txtGameSpeedQuestion = (TextView) findViewById(R.id.txtGameSpeedQuestion);
        btnGameSpeedFollowing00 = (Button) findViewById(R.id.btnGameSpeedFollowing00);
        btnGameSpeedFollowing00.setOnClickListener(this);
        btnGameSpeedFollowing01 = (Button) findViewById(R.id.btnGameSpeedFollowing01);
        btnGameSpeedFollowing01.setOnClickListener(this);
        btnGameSpeedFollowing02 = (Button) findViewById(R.id.btnGameSpeedFollowing02);
        btnGameSpeedFollowing02.setOnClickListener(this);

        timeLeft = TIMER_MAX;
        txtGameSpeedTimer.setText(Integer.toString(timeLeft));
        progressGameSpeedTimer.setProgress(0);

        quizOperators = new String[QUIZ_NUM_OPERATORS];
        quizOperands = new int[QUIZ_NUM_OPERAND];
        answers = new int[QUIZ_NUM_ANSWERS];

        vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        timer = new Timer();
        timer.schedule(stt, 1000, 1000);

        resetQuiz();
    }

    @Override
    public void onClick(View v){
        switch(v.getId()){
            case R.id.imgGameNClose:
                finish();
                break;
            case R.id.btnGameSpeedFollowing00:
                if(checkAnswer(0)){
                    rightAnswer();
                    resetQuiz();
                }else{
                    wrongAnswer();
                }
                break;
            case R.id.btnGameSpeedFollowing01:
                if(checkAnswer(1)){
                    rightAnswer();
                    resetQuiz();
                }else{
                    wrongAnswer();
                }
                break;
            case R.id.btnGameSpeedFollowing02:
                if(checkAnswer(2)){
                    rightAnswer();
                    resetQuiz();
                }else{
                    wrongAnswer();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onDestroy(){
        stt.stopTimer();
        super.onDestroy();
    }


    private void resetQuiz(){
        Random generator = new Random();
        StringBuffer question = new StringBuffer();
        for(int i=0;i<quizOperators.length;i++){
            quizOperators[i] = Constants.getOperator(generator.nextInt(Constants.getOperatorsNum()));
        }
        for(int i=0;i<quizOperands.length;i++){
            quizOperands[i] = generator.nextInt(QUIZ_MAX_OPERAND) + 1;
        }
        question.append(quizOperands[0]);
        question.append(quizOperators[0]);
        question.append(quizOperands[1]);
        question.append(quizOperators[1]);
        question.append(quizOperands[2]);
        question.append("=?");

        rightPos = generator.nextInt(QUIZ_NUM_ANSWERS);
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
        txtGameSpeedQuestion.setText(question.toString());
        btnGameSpeedFollowing00.setText(Integer.toString(answers[0]));
        btnGameSpeedFollowing01.setText(Integer.toString(answers[1]));
        btnGameSpeedFollowing02.setText(Integer.toString(answers[2]));
    }

    private boolean checkAnswer(int pos){
        return pos == rightPos;
    }

    private void rightAnswer(){
        toast.show();
        toastHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                toast.cancel();
            }
        }, 100);
    }

    private void wrongAnswer(){
        stt.stopTimer();
        vibe.vibrate(1000);
        Intent intent = new Intent(this, GameSpeedResultActivity.class);
        startActivity(intent);
        this.finish();
    }

    SecTimerTask stt = new SecTimerTask(new SecTimerTask.TimeCallBack() {
        @Override
        public void callback() {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    timeLeft--;
                    if(timeLeft == 10){
                        progressGameSpeedTimer.setProgressDrawable(res.getDrawable(R.drawable.common_circle_progress_foreground_alarm));
                    }
                    txtGameSpeedTimer.setText(Integer.toString(timeLeft));
                    progressGameSpeedTimer.setProgress(30-timeLeft);
                    if(timeLeft < 1){
                        wrongAnswer();
                    }
                }
            });
        }
    });

}
