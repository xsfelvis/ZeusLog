package xsf.zeuslog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import xsf.zeuslibrary.zeusLog.ZeusLog;
import xsf.zeuslibrary.zeusMobile.ZeusMobileView;

public class MainActivity extends AppCompatActivity {
    private static String JSON;
    private static String JSON_LONG;
    private static String STRING_LONG;
    private static final String TAG = "TestTag";
    private static final String LOG_MSG = "ZeusLog Test String!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ZeusLog.init(BuildConfig.DEBUG);
        initData();
    }

    private void initData() {
        JSON_LONG = getResources().getString(R.string.json_long);
        JSON = getResources().getString(R.string.json);
        STRING_LONG = getString(R.string.string_long);
    }

    public void logOnPhone(View view){
       ZeusMobileView.startZeus(MainActivity.this).setJsonStr(JSON_LONG);
    }

    public void logWithJson(View view) {
        ZeusLog.printJsonStr("彩笔学长 ZeusLog");
        ZeusLog.printJsonStr(null);
        ZeusLog.printJsonStr(JSON);
    }

    public void logWithLongJson(View view) {
        ZeusLog.printJsonStr(JSON_LONG);
    }

    public void logWithJsonTag(View view) {
        ZeusLog.printJsonStr(TAG, JSON);
    }

    public void logWithLong(View view) {
        ZeusLog.d(TAG, STRING_LONG);
    }

    public void logWithMsg(View view) {
        ZeusLog.v(LOG_MSG);
        ZeusLog.d(LOG_MSG);
        ZeusLog.i(LOG_MSG);
        ZeusLog.w(LOG_MSG);
        ZeusLog.e(LOG_MSG);
        ZeusLog.a(LOG_MSG);
    }

    public void logWithTag(View view) {
        ZeusLog.v(TAG, LOG_MSG);
        ZeusLog.d(TAG, LOG_MSG);
        ZeusLog.i(TAG, LOG_MSG);
        ZeusLog.w(TAG, LOG_MSG);
        ZeusLog.e(TAG, LOG_MSG);
        ZeusLog.a(TAG, LOG_MSG);
    }

    public void logWithNull(View view) {
        ZeusLog.v(null);
        ZeusLog.d(null);
        ZeusLog.i(null);
        ZeusLog.w(null);
        ZeusLog.e(null);
        ZeusLog.a(null);
    }

    public void log(View view) {
        ZeusLog.v();
        ZeusLog.d();
        ZeusLog.i();
        ZeusLog.w();
        ZeusLog.e();
        ZeusLog.a();
    }

    public void logWithParams(View view) {
        ZeusLog.v(TAG, LOG_MSG, "params1", "params2", this);
        ZeusLog.d(TAG, LOG_MSG, "params1", "params2", this);
        ZeusLog.i(TAG, LOG_MSG, "params1", "params2", this);
        ZeusLog.w(TAG, LOG_MSG, "params1", "params2", this);
        ZeusLog.e(TAG, LOG_MSG, "params1", "params2", this);
        ZeusLog.a(TAG, LOG_MSG, "params1", "params2", this);
    }

}
