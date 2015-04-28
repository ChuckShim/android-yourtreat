package com.mon.bubu.yourtreat;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Chuck on 2015. 4. 28..
 */
public class GameCatsFragment extends Fragment{

    public GameCatsFragment() {}

    public static GameCatsFragment newInstance() {
        GameCatsFragment fragment = new GameCatsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_game_cats, container, false);

        return v;
    }

}
