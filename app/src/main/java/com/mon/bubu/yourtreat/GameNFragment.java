package com.mon.bubu.yourtreat;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.mon.bubu.yourtreat.common.Utils;


/**
 * Created by Chuck on 2015. 4. 28..
 */
public class GameNFragment extends Fragment implements View.OnClickListener{
    private static GameNFragment gameNInstance = null;
    public GameNFragment() {}
    public static GameNFragment getInstance() {
        if(gameNInstance == null){
            gameNInstance = new GameNFragment();
        }
        return gameNInstance;
    }

    LinearLayout linearLayoutFragmentGameN;

    EditText edit_game_n_amount, edit_game_n_number;
    Button btn_game_n_info, btn_game_n_home, btn_game_n_start;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_game_n, container, false);

        linearLayoutFragmentGameN = (LinearLayout) v.findViewById(R.id.linearLayoutFragmentGameN);
        edit_game_n_amount = (EditText) v.findViewById(R.id.edit_game_n_amount);
        edit_game_n_number = (EditText) v.findViewById(R.id.edit_game_n_number);
        btn_game_n_info = (Button) v.findViewById(R.id.btn_game_n_info);
        btn_game_n_home = (Button) v.findViewById(R.id.btn_game_n_home);
        btn_game_n_start = (Button) v.findViewById(R.id.btn_game_n_start);
        linearLayoutFragmentGameN.setOnClickListener(this);
        btn_game_n_info.setOnClickListener(this);
        btn_game_n_home.setOnClickListener(this);
        btn_game_n_start.setOnClickListener(this);

        return v;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        //((HomeActivity)this.getActivity()).setCurrentFragment(this);
    }


    @Override
    public void onClick(View v){

        switch(v.getId()){
            case R.id.linearLayoutFragmentGameN:
                Utils.hideKeyboard(this.getActivity());
                break;
            case R.id.btn_game_n_info:

                break;
            case R.id.btn_game_n_home:

                break;
            case R.id.btn_game_n_start:

                int amount = 0;
                int number = 0;
                String strAmount = edit_game_n_amount.getText().toString().trim();
                String strNumber = edit_game_n_number.getText().toString().trim();

                if(strAmount == null || strAmount.length() == 0){
                    ((HomeActivity)this.getActivity()).showAlertDialog("금액을 입력해 주세요.");
                    break;
                }
                if(strNumber == null || strNumber.length() == 0){
                    ((HomeActivity)this.getActivity()).showAlertDialog("인원수를 입력해 주세요.");
                    break;
                }
                try{
                    amount = Integer.parseInt(strAmount);
                }catch(Exception ex){
                    ((HomeActivity)this.getActivity()).showAlertDialog("금액 입력은 숫자만 가능합니다.");
                    break;
                }
                try{
                    number = Integer.parseInt(strNumber);
                }catch(Exception ex){
                    ((HomeActivity)this.getActivity()).showAlertDialog("인원수 입력은 숫자만 가능합니다.");
                    break;
                }
                if(amount < 1000){
                    ((HomeActivity)this.getActivity()).showAlertDialog("금액은 천원 이상 입력해 주세요.");
                    break;
                }
                if(amount > Integer.MAX_VALUE){
                    ((HomeActivity)this.getActivity()).showAlertDialog("금액이 지나치게 많습니다. 도박은 금물!");
                    break;
                }
                if(number < 2 || number > 8){
                    ((HomeActivity)this.getActivity()).showAlertDialog("인원수는 2명이상 8명이하로 입력해 주세요.");
                    break;
                }

                Intent intent = new Intent(this.getActivity(), GameNResultActivity.class);
                intent.putExtra("amount", amount);
                intent.putExtra("number", number);
                startActivity(intent);
                break;
            default:
                break;
        }

    }

}
