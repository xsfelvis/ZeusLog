package xsf.zeuslibrary.zeusMobile;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import xsf.zeuslibrary.R;
import xsf.zeuslibrary.zeusLog.ZeusLog;

/**
 * Author: xushangfei
 * Time: created at 2016/12/30.
 * Description:
 */

public class ZeusMobileView extends LinearLayout {

    private static final String DOUBLE_DIVIDER = "\n ═══════════END═════════ \n";
    private TextView tvShowInfo, tvAcvName;
    private static ZeusMobileView zeusMobileView;
    private Switch btnSwitch;
    private SideDragBar sideDragBar;
    private ScrollViewSV sv;
    private LinearLayout llContainer;
    private Handler zeusHandler;

    public static ZeusMobileView startZeus(Activity activity) {
        if (zeusMobileView == null) {
            zeusMobileView = new ZeusMobileView(activity);
        }
        getInstance().attachActivity(activity);

        return zeusMobileView;
    }

    private static ZeusMobileView getInstance() {
        if (zeusMobileView == null) {
            throw new RuntimeException("debugView");
        }
        return zeusMobileView;
    }

    public ZeusMobileView(Context context) {
        this(context, null);
    }

    public ZeusMobileView(Context context, AttributeSet attrs) {
        this(context, null, 0);
    }

    public ZeusMobileView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        findViews();
        initViews();
    }

    private void findViews() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.view_debug, this, true);
        llContainer = (LinearLayout) contentView.findViewById(R.id.llZeusContainer);
        tvShowInfo = (TextView) contentView.findViewById(R.id.tvShowInfo);
        tvAcvName = (TextView) contentView.findViewById(R.id.tvActName);
        btnSwitch = (Switch) contentView.findViewById(R.id.btnSwitch);
        btnSwitch.setChecked(true);
        sideDragBar = (SideDragBar) contentView.findViewById(R.id.sideDragBar);
        sv = (ScrollViewSV) contentView.findViewById(R.id.svContent);
    }

    private void initViews() {
        zeusHandler = new Handler();
        btnSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    textClear();
                    llContainer.setBackgroundColor(0x00000000);
                    sideDragBar.setVisibility(GONE);
                } else {
                    llContainer.setBackgroundColor(0x7f000000);
                    sideDragBar.setVisibility(VISIBLE);
                }
            }
        });
        sideDragBar.setSv(sv);
    }

    private void textClear() {
        tvShowInfo.setText(null);
    }


    /**
     * 全屏附着到Actvity
     */
    public void attachActivity(Activity activity) {

        if (!(activity instanceof Activity)) {
            throw new RuntimeException("Parameter type must be Actvity");
        }
        if (zeusMobileView == null) {
            throw new RuntimeException("You must  use startZeus to create instance first!");
        }
        if(zeusMobileView.getParent()!=null){
          ((ViewGroup)zeusMobileView.getParent()).removeView(zeusMobileView);
            tvShowInfo.setText(null);
        }

        ViewGroup rootView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        rootView.addView(zeusMobileView, lp);
        tvAcvName.setText(activity.getClass().getCanonicalName());
        //AppLog.d("xsf_getCanonicalName:",activity.getClass().getCanonicalName());
        //AppLog.d("xsf_getSimpleName:",activity.getClass().getSimpleName());
        //AppLog.d("xsf_getName:",activity.getClass().getName());
        //AppLog.d("xsf_getLocalClassName:",activity.getLocalClassName());
    }

    /*public synchronized void setJsonObject(final Object object) {
        if (!btnSwitch.isChecked() || object == null) {
            return;
        }
        final String jsonStr = new Gson().toJson(object);
        handleJsonMsg(jsonStr);
    }*/

    public synchronized void setJsonStr(final String jsonStr){
        if (!btnSwitch.isChecked() || TextUtils.isEmpty(jsonStr)) {
            return;
        }
        handleJsonMsg(jsonStr);
    }


    private void handleJsonMsg(final String jsonStr) {
        if (jsonStr == null) {
            return;
        }
        final String json = jsonStr;
        zeusHandler.post(new Runnable() {
            @Override
            public void run() {
                String message;
                try{

                    if(json.startsWith("{")){
                        JSONObject jsonObject = new JSONObject(json);
                        message = jsonObject.toString(ZeusLog.JSON_INDENT);
                    }else if(json.startsWith("[")){
                        JSONArray jsonArray = new JSONArray(json);
                        message = jsonArray.toString(ZeusLog.JSON_INDENT);
                    }else {
                        message = json;
                    }

                }catch (JSONException e){
                    message = json;
                }
                tvShowInfo.append(message);
                tvShowInfo.append(DOUBLE_DIVIDER);
            }
        });
    }

}
