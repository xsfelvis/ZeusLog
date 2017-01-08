package xsf.zeuslibrary.zeusLog;

import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;

/**
 * Author: xushangfei
 * Time: created at 2017/1/4.
 * Description:
 */

public final class ZeusLog {
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");
    public static final int JSON_INDENT = 4;
    public static final String NULL_TIPS = "Log null object";

    private static final String DEFAULT_MESSAGE = "彩笔学长";
    private static final String PARAM = "Param";
    private static final String NULL = "null";
    private static final String TAG_DEFAULT = "ZeusLog";
    private static final String SUFFIX = ".java";

    public static final int V = 0x1;
    public static final int D = 0x2;
    public static final int I = 0x3;
    public static final int W = 0x4;
    public static final int E = 0x5;
    public static final int A = 0x6;

    private static final int JSON = 0x7;
    private static final int XML = 0x8;
    private static final int STACK_TRACE_INDEX_5 = 5;
    private static final int STACK_TRACE_INDEX_4 = 4;

    private static String GlobalTag;
    private static boolean IsGlobalTagEmpty = true;
    private static boolean IS_SHOW_LOG = true;


    public static void v() {
        printZeusLog(V, null, DEFAULT_MESSAGE);
    }

    public static void v(Object msg) {
        printZeusLog(V, null, msg);
    }

    public static void v(String tag, Object... objects) {
        printZeusLog(V, tag, objects);
    }

    public static void d() {
        printZeusLog(D, null, DEFAULT_MESSAGE);
    }

    public static void d(Object msg) {
        printZeusLog(D, null, msg);
    }

    public static void d(String tag, Object... objects) {
        printZeusLog(D, tag, objects);
    }

    public static void i() {
        printZeusLog(I, null, DEFAULT_MESSAGE);
    }

    public static void i(Object msg) {
        printZeusLog(I, null, msg);
    }

    public static void i(String tag, Object... objects) {
        printZeusLog(I, tag, objects);
    }

    public static void w() {
        printZeusLog(W, null, DEFAULT_MESSAGE);
    }

    public static void w(Object msg) {
        printZeusLog(W, null, msg);
    }

    public static void w(String tag, Object... objects) {
        printZeusLog(W, tag, objects);
    }

    public static void e() {
        printZeusLog(E, null, DEFAULT_MESSAGE);
    }

    public static void e(Object msg) {
        printZeusLog(E, null, msg);
    }

    public static void e(String tag, Object... objects) {
        printZeusLog(E, tag, objects);
    }

    public static void a() {
        printZeusLog(A, null, DEFAULT_MESSAGE);
    }

    public static void a(Object msg) {
        printZeusLog(A, null, msg);
    }

    public static void a(String tag, Object... objects) {
        printZeusLog(A, tag, objects);
    }


    public static void init(boolean isShowLog) {
        IS_SHOW_LOG = isShowLog;
    }
    //全局tag
    public static void init(boolean isShowLog, @Nullable String tag) {
        IS_SHOW_LOG = isShowLog;
        GlobalTag = tag;
        IsGlobalTagEmpty = TextUtils.isEmpty(GlobalTag);
    }

   /* public static void printJsonObjecct(String tag, Object object){
        final String jsonStr = new Gson().toJson(object);
        printZeusLog(JSON,tag,jsonStr);
    }

    public static void printJsonObjecct(Object object){
        final String jsonStr = new Gson().toJson(object);
        printZeusLog(JSON,null,jsonStr);
    }*/

    public static void printJsonStr(String tag, String jsonFormat) {
        printZeusLog(JSON, tag, jsonFormat);
    }

    public static void printJsonStr(String jsonFormat) {
        printZeusLog(JSON, null, jsonFormat);
    }

    private static void printZeusLog(int type, String tagStr, Object... objects) {
        if (!IS_SHOW_LOG) {
            return;
        }
        String[] contents = wrapperContent(STACK_TRACE_INDEX_5, tagStr, objects);
        String tag = contents[0];
        String msg = contents[1];
        String headString = contents[2];
        switch (type) {
            case V:
            case D:
            case I:
            case W:
            case E:
            case A:
                BaseLog.printDefault(type, tag, headString + msg);
                break;
            case JSON:
                JsonLog.printJsonLog(tag, msg, headString);
                break;
        }


    }

    private static String[] wrapperContent(int stackTraceIndex, String tagStr, Object... objects) {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        StackTraceElement targetElement = stackTrace[stackTraceIndex];
        String className = targetElement.getClassName();
        String[] classNameInfo = className.split("\\.");
        if (classNameInfo.length > 0) {
            className = classNameInfo[classNameInfo.length - 1] + SUFFIX;
        }
        if (className.contains("$")) {
            className = className.split("\\$")[0] + SUFFIX;
        }
        String methodName = targetElement.getMethodName();
        int lineNumber = targetElement.getLineNumber();
        if (lineNumber < 0) {
            lineNumber = 0;
        }
        String tag = (tagStr == null ? className : tagStr);
        if (IsGlobalTagEmpty && TextUtils.isEmpty(tag)) {
            tag = TAG_DEFAULT;
        } else if (!IsGlobalTagEmpty) {
            tag = GlobalTag;
        }

        String msg = (objects == null) ? NULL_TIPS : getObjectsString(objects);
        String headString = "[(" + className + ":" + lineNumber + ")#" + methodName + "] ";
        return new String[]{tag, msg, headString};
    }


    private static String getObjectsString(Object... objects) {

        if (objects.length > 1) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\n");
            for (int i = 0; i < objects.length; i++) {
                Object object = objects[i];
                if (object == null) {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(NULL).append("\n");
                } else {
                    stringBuilder.append(PARAM).append("[").append(i).append("]").append(" = ").append(object.toString()).append("\n");
                }
            }
            return stringBuilder.toString();
        } else {
            Object object = objects[0];
            return object == null ? NULL : object.toString();
        }
    }


    public static boolean isEmpty(String line) {
        return TextUtils.isEmpty(line) || line.equals("\n") || line.equals("\t") || TextUtils.isEmpty(line.trim());
    }

    public static void printLine(String tag, boolean isTop) {
        if (isTop) {
            Log.i(tag, "╔═══════════════════════════════════════════════════════════════════");
        } else {
            Log.i(tag, "╚═══════════════════════════════════════════════════════════════════");
        }
    }
}
