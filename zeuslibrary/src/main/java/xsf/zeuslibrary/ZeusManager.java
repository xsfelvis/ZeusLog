package xsf.zeuslibrary;

import android.content.Context;

import xsf.zeuslibrary.zeusLog.ZeusLog;


/**
 * Author: xushangfei
 * Time: created at 2017/1/5.
 * Description:
 */

public class ZeusManager {
    public static void startZeus(Context context){
        //ZeusMobileView.startZeus(context);
        ZeusLog.init(BuildConfig.DEBUG);
    }
}
