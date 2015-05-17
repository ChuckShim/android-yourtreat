package com.mon.bubu.yourtreat.base;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.telephony.TelephonyManager;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mon.bubu.yourtreat.HomeFragment;
import com.mon.bubu.yourtreat.R;

/**
 * Created by Chuck on 2015-05-18.
 */
public class BaseActivity extends Activity {

    private AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    public void showAlertDialog(String message) {
        try {
            if (this.alertDialog == null) {
                this.alertDialog = new AlertDialog.Builder(this);
            }
            alertDialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            alertDialog.setMessage(message);
            alertDialog.create();
            alertDialog.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
