package xsf.zeuslibrary.zeusLog;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Author: xushangfei
 * Time: created at 2017/1/4.
 * Description:Json
 */

public class JsonLog {
    public static void printJsonLog(String tag, String msg, String headString){
        String message;
        try{
            if(msg.startsWith("{")){
                JSONObject jsonObject = new JSONObject(msg);
                message = jsonObject.toString(ZeusLog.JSON_INDENT);
            }else if(msg.startsWith("[")){
                JSONArray jsonArray = new JSONArray(msg);
                message = jsonArray.toString(ZeusLog.JSON_INDENT);
            }else {
                message = msg;
            }

        }catch (JSONException e){
            message = msg;
        }

        ZeusLog.printLine(tag,true);
        message = headString+ZeusLog.LINE_SEPARATOR+message;
        String[] lines = message.split(ZeusLog.LINE_SEPARATOR);
        for (String line:lines){
            Log.i(tag, "║ " + line);
        }
        ZeusLog.printLine(tag,false);

    }
}
