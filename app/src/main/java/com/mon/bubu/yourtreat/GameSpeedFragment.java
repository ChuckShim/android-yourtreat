package com.mon.bubu.yourtreat;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Chuck on 2015. 4. 28..
 */
public class GameSpeedFragment extends Fragment implements View.OnClickListener {
    private static GameSpeedFragment gameShakeInstance = null;

    public GameSpeedFragment() {
    }

    public static GameSpeedFragment getInstance() {
        if (gameShakeInstance == null) {
            gameShakeInstance = new GameSpeedFragment();
        }
        return gameShakeInstance;
    }

    Button btnGameSpeedStart, btn_game_n_info, btn_game_n_home;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_game_speed, container, false);

        btnGameSpeedStart = (Button) v.findViewById(R.id.btnGameSpeedStart);
        btnGameSpeedStart.setOnClickListener(this);

        btn_game_n_info = (Button) v.findViewById(R.id.btn_game_n_info);
        btn_game_n_home = (Button) v.findViewById(R.id.btn_game_n_home);
        btn_game_n_info.setOnClickListener(this);
        btn_game_n_home.setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
     //   ((HomeActivity) this.getActivity()).setCurrentFragment(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_game_n_info:

                break;
            case R.id.btn_game_n_home:
                break;
            case R.id.btnGameSpeedStart:
                Intent intent = new Intent(this.getActivity(), GameSpeedPlayActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }
}