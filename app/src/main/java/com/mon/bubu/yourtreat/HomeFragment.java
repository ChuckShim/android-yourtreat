package com.mon.bubu.yourtreat;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by Chuck on 2015. 4. 28..
 */
public class HomeFragment extends Fragment implements View.OnClickListener{

    Button btn_navi_game_weather, btn_navi_game_cats, btn_navi_game_n;

    GameCatsFragment gameCatsFragment;
    GameNFragment gameNFragment;

    public HomeFragment() {}

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_home, container, false);

        // Menu Buttons
        btn_navi_game_weather = (Button) v.findViewById(R.id.btn_navi_game_weather);
        btn_navi_game_cats = (Button) v.findViewById(R.id.btn_navi_game_cats);
        btn_navi_game_n = (Button) v.findViewById(R.id.btn_navi_game_n);

        btn_navi_game_weather.setOnClickListener(this);
        btn_navi_game_cats.setOnClickListener(this);
        btn_navi_game_n.setOnClickListener(this);

        // Menu Fragment Instances
        gameCatsFragment = GameCatsFragment.newInstance();
        gameNFragment = GameNFragment.newInstance();

        return v;
    }

    @Override
    public void onClick(View v){

        switch(v.getId()){
            case R.id.btn_navi_game_weather:

                break;
            case R.id.btn_navi_game_cats:
                getFragmentManager().beginTransaction()
                        .add(R.id.container, gameCatsFragment)
                        .addToBackStack("BACKSTACK_GAME_CATS")
                        .commit();
                break;
            case R.id.btn_navi_game_n:
                getFragmentManager().beginTransaction()
                        .add(R.id.container, gameNFragment)
                        .addToBackStack("BACKSTACK_GAME_N")
                        .commit();
                break;
            default:
                break;
        }

    }

}
