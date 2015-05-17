package com.mon.bubu.yourtreat;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Chuck on 2015. 4. 28..
 */
public class GameNFragment extends Fragment implements View.OnClickListener{

    EditText edit_game_n_amount, edit_game_n_number;
    Button btn_game_n_info, btn_game_n_home, btn_game_n_start;

    public GameNFragment() {}

    public static GameNFragment newInstance() {
        return new GameNFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_game_n, container, false);

        edit_game_n_amount = (EditText) v.findViewById(R.id.edit_game_n_number);
        edit_game_n_number = (EditText) v.findViewById(R.id.edit_game_n_number);
        btn_game_n_info = (Button) v.findViewById(R.id.btn_game_n_info);
        btn_game_n_home = (Button) v.findViewById(R.id.btn_game_n_home);
        btn_game_n_start = (Button) v.findViewById(R.id.btn_game_n_start);
        btn_game_n_info.setOnClickListener(this);
        btn_game_n_home.setOnClickListener(this);
        btn_game_n_start.setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
    }

    @Override
    public void onClick(View v){

        switch(v.getId()){
            case R.id.btn_game_n_info:

                break;
            case R.id.btn_game_n_home:
                getFragmentManager().beginTransaction()
                        .remove(this)
                        .commit();
                break;
            case R.id.btn_game_n_start:

                int amount = 0;
                int number = 0;
                String strAmount = edit_game_n_amount.getText().toString().trim();
                String strNumber = edit_game_n_number.getText().toString().trim();

                if(strAmount == null || strAmount.length() == 0){

                }
                if(strNumber == null || strNumber.length() == 0){

                }
                try{
                    amount = Integer.parseInt(strAmount);
                }catch(Exception ex){

                }
                try{
                    number = Integer.parseInt(strNumber);
                }catch(Exception ex){

                }

                Intent intent = new Intent(this.getActivity(), GameNResultActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

}
