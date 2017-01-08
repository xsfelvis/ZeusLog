package xsf.zeuslibrary.zeusMobile;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class SideDragBar extends View {
    private int lastX = 0;
    private int lastY = 0;

    private ScrollViewSV sv;


    public SideDragBar(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SideDragBar(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SideDragBar(Context context) {
        super(context);
    }

    public void setSv(ScrollViewSV sv) {
        this.sv = sv;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        final int action = event.getAction();
        //记录当前点击位置
        int x = (int) event.getX();
        int y = (int) event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
            case MotionEvent.ACTION_MOVE:
                int offsetX = x-lastX;
                int offsetY = y-lastY;
                sv.smoothScrollBy(0,offsetY);
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.TRANSPARENT);
    }

}
