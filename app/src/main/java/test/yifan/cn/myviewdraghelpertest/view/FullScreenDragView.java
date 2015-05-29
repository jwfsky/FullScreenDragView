package test.yifan.cn.myviewdraghelpertest.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

/**
 * Author: Jwf(feijia101@gmail.com)
 * Date: 2015-04-01 13:27
 * Version: 1.0
 * Desc:
 * Revise:
 */
public class FullScreenDragView extends FrameLayout {

    private ViewDragHelper mDragHelper;

    public FullScreenDragView(Context context) {
        super(context);
        initData(context);

    }

    public FullScreenDragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initData(context);
    }

    public FullScreenDragView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
    }

    private void initData(Context context) {
        mDragHelper = ViewDragHelper.create(this, new MyDragCallBack());
    }

    private class MyDragCallBack extends ViewDragHelper.Callback {

        /**
         * 尝试捕获子视图，应返回true
         *
         * @param view 尝试捕获的view
         * @param i    恶意指定捕获对应得view
         * @return
         */
        @Override
        public boolean tryCaptureView(View view, int i) {
            return true;
        }

        /**
         * 当viewDrag 状态改变的时候调用
         *
         * @param state 改变的状态
         */
        @Override
        public void onViewDragStateChanged(int state) {
            switch (state) {
                case ViewDragHelper.STATE_DRAGGING://正在拖动
                    break;
                case ViewDragHelper.STATE_SETTLING://fling 完毕
                    break;
                case ViewDragHelper.STATE_IDLE://没有被拖动，或正在fling
                    break;

            }
            super.onViewDragStateChanged(state);
        }

        /**
         * 横向的位置移动
         *
         * @param child 被移动的子视图
         * @param left  离x轴的距离
         * @param dx    建议移动的X 的距离
         * @return
         */
        @Override
        public int clampViewPositionHorizontal(View child, int left, int dx) {
            //判断，使子视图存在于viewgroup 内部
            if (getPaddingLeft() > left) {
                return getPaddingLeft();
            }
            if ((getWidth() - child.getWidth()) < left) {
                return (getWidth() - child.getWidth());
            }
            return left;
        }

        /**
         * 纵向的位置移动
         *
         * @param child 子视图
         * @param top   离Y轴的距离
         * @param dy    建议移动的距离
         * @return
         */
        @Override
        public int clampViewPositionVertical(View child, int top, int dy) {
            //判断，使子视图存在于viewgroup 内部
            if (getPaddingTop() > top) {
                return getPaddingTop();
            }
            if ((getHeight() - child.getHeight()) < top) {
                return getHeight() - child.getHeight();
            }
            return top;
        }
    }

    /**
     * 和gestureDecter一样，viewDragHelper 也要重写一下两个方法
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {

        View view = mDragHelper.getCapturedView();
        mDragHelper.getTouchSlop();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                mDragHelper.cancel();
                break;
            case MotionEvent.ACTION_DOWN:
                break;
        }
        //System.out.println(startX+"|"+startY+"|"+endX+"|"+endY);
        //检查是否可以拦截touch 事件
        //System.out.println(mDragHelper.shouldInterceptTouchEvent(ev));
        return true;
    }

    float startX = 0, startY = 0, endX = 0, endY = 0, test = 0;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        Button btn = null;
        /**
         * 获取BUTTON，对点击或拖动进行处理
         */
        for (int i = 0; i < getChildCount(); i++) {
            View view = getChildAt(i);

            if (view instanceof Button) {
                if (isEventWithinView(ev, view)) {
                    btn = (Button) view;
                }
            }
        }

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = ev.getX();
                startY = ev.getY();
                if (btn != null) {
                    btn.dispatchTouchEvent(ev);
                }
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                endX = ev.getX();
                endY = ev.getY();
                if (Math.abs(endX - startX) < 8 && Math.abs(endY - startY) < 8) {
                    if (btn != null) {
                        btn.dispatchTouchEvent(ev);
                    }
                } else {
                    if (btn != null) {
                        btn.setPressed(false);
                    }
                    onTouchEvent(ev);
                    return true;
                }
                break;
        }

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //处理拦截到的ontouch事件
        mDragHelper.processTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                System.out.println("正在移动。。。");
                break;
            case MotionEvent.ACTION_UP:
                System.out.println("已经抬起。。。");
                break;

        }
        return true;
    }

    /**
     * 判断当前的事件是否响应在子view里
     */
    private boolean isEventWithinView(MotionEvent e, View child) {
        Rect viewRect = new Rect();
        int[] childPosition = new int[2];
        child.getLocationOnScreen(childPosition);
        int left = childPosition[0];
        int right = left + child.getWidth();
        int top = childPosition[1];
        int bottom = top + child.getHeight();
        viewRect.set(left, top, right, bottom);
        return viewRect.contains((int) e.getRawX(), (int) e.getRawY());
    }
}
