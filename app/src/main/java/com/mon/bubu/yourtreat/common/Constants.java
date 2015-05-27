package com.mon.bubu.yourtreat.common;

/**
 * Created by Chuck on 2015. 5. 27..
 */
public class Constants {

    private static final String _OPERATOR_ADDITION_ = "ADDITION";
    private static final String _OPERATOR_SUBTRACTION_ = "SUBTRACTION";
    private static final String _OPERATOR_MULTIPLICATION_ = "MULTIPLICATION";

    private static final String _OPERATORS_[] = {_OPERATOR_ADDITION_, _OPERATOR_SUBTRACTION_, _OPERATOR_MULTIPLICATION_};

    public static String getOperatorAddition(){
        return _OPERATOR_ADDITION_;
    }
    public static String getOperatorSubtraction(){
        return _OPERATOR_SUBTRACTION_;
    }
    public static String getOperatorMultiplication(){
        return _OPERATOR_MULTIPLICATION_;
    }

    public static int getOperatorsNum(){
        return _OPERATORS_.length;
    }

    public static String getOperator(int pos){
        return _OPERATORS_[pos];
    }

}
