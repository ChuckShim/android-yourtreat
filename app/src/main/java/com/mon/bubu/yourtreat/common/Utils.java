package com.mon.bubu.yourtreat.common;

import android.app.Activity;
import android.content.Context;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by Chuck on 2015-05-21.
 */
public class Utils {

    public static void hideKeyboard(Activity activity){
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getWindow().getDecorView().getRootView().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
    }

    public static int doArithmetic(int a, int b, String operator){
        int result = 0;
        if(Constants.getOperatorAddition().equals(operator)){
            result = a + b;
        }else if(Constants.getOperatorSubtraction().equals(operator)){
            result = a - b;
        }else if(Constants.getOperatorMultiplication().equals(operator)){
            result = a * b;
        }
        return result;
    }
}
