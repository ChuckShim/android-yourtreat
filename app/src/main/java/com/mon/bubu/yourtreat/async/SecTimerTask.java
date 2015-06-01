package com.mon.bubu.yourtreat.async;

import java.util.TimerTask;

/**
 * Created by donguk on 2015-04-07.
 */
/*public class TimerTask extends AsyncTask<String, String, String> {

    @Override
    protected String doInBackground(String... params) {
        return null;
    }
}*/
public class SecTimerTask extends TimerTask {
    private TimeCallBack callBack;

    public SecTimerTask(TimeCallBack callBack) {
        this.callBack = callBack;
    }


    public void stopTimer() {
        this.cancel();
    }

    @Override
    public void run() {
        if (callBack != null)
            callBack.callback();
    }

    public static interface TimeCallBack {
        public void callback();
    }
}