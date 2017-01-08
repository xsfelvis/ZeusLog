package xsf.zeuslibrary.zeusMobile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * Author: xushangfei
 * Time: created at 2017/1/4.
 * Description:
 */

public class ScrollViewSV extends ScrollView {
    public ScrollViewSV(Context context) {
        super(context);
    }

    public ScrollViewSV(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewSV(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

}
