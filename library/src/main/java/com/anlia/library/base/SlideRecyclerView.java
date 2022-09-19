package com.anlia.library.base;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * @author ljm
 * @date 2019/3/25
 * 侧滑菜单栏RecyclerView,交互流畅
 */
public class SlideRecyclerView extends RecyclerView {

    /**最小速度*/
    private static final int MINIMUM_VELOCITY = 500;

    /**滑动的itemView*/
    private ViewGroup mMoveView;

    /**末次滑动的itemView*/
    private ViewGroup mLastView;

    /**itemView中菜单控件宽度*/
    private int mMenuWidth;

    private VelocityTracker mVelocity;

    /**触碰时的首个横坐标*/
    private int mFirstX;

    /**触碰时的首个纵坐标*/
    private int mFirstY;

    /**触碰末次的横坐标*/
    private int mLastX;

    /**最小滑动距离*/
    private int mTouchSlop;

    private Scroller mScroller;

    /**是否正在水平滑动*/
    private boolean mMoving;


    public SlideRecyclerView(Context context) {
        super(context);
        init();
    }

    public SlideRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SlideRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init(){
        mTouchSlop = ViewConfiguration.get(getContext()).getScaledTouchSlop();
        mScroller = new Scroller(getContext());
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int x = (int) e.getX();
        int y = (int) e.getY();
        addVelocityEvent(e);
        switch (e.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(!mScroller.isFinished()){
                    mScroller.abortAnimation();
                }
                mFirstX = x;
                mFirstY =y;
                mLastX = x;
                //获取点击区域所在的itemView
                mMoveView = (ViewGroup) findChildViewUnder(x, y);
                if(mMoveView != mLastView && mLastView != null && mLastView.getScrollX() >0){
                    closeMenu();
                }
                if(mMoveView != null && mMoveView.getChildCount() == 2){
                    mMenuWidth = mMoveView.getWidth();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                mVelocity.computeCurrentVelocity(1000);
                int velocityX = (int) Math.abs(mVelocity.getXVelocity());
                int velocityY = (int) Math.abs(mVelocity.getYVelocity());
                int moveX = Math.abs(x - mFirstX);
                int moveY = Math.abs(y - mFirstY);
                //根据水平滑动条件判断，是否让itemView跟随手指滑动
                //这里重新判断是避免itemView中不拦截ACTION_DOWN事件，则后续ACTION_MOVE并不会走onInterceptTouchEvent()方法
                boolean isHorizontalMove = (Math.abs(velocityX) >= MINIMUM_VELOCITY && velocityX > velocityY
                        || moveX > moveY && moveX > mTouchSlop) && mMenuWidth > 0 && getScrollState() == 0;
                if(isHorizontalMove){
                    mMoving = true;
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if(mLastView != null && mLastView.getScrollX() != 0){
                    mLastView.scrollTo(0,0);
                }
                break;
        }
        return super.onInterceptTouchEvent(e);
    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        addVelocityEvent(e);
        int x = (int) e.getX();
        int y = (int) e.getY();
        switch (e.getAction()){
            case MotionEvent.ACTION_MOVE:
                if(mMoving){
                    int dx = mLastX - x;
                    if(dx + mMoveView.getScrollX() >0 && dx + mMoveView.getScrollX() <=mMenuWidth){
                        mMoveView.scrollBy(dx,0);
                    }
                    mLastX = x;
                    return true;
                }else{
                    mVelocity.computeCurrentVelocity(1000);
                    int velocityX = (int) Math.abs(mVelocity.getXVelocity());
                    int velocityY = (int) Math.abs(mVelocity.getYVelocity());
                    int moveX = Math.abs(x - mFirstX);
                    int moveY = Math.abs(y - mFirstY);
                    //根据水平滑动条件判断，是否让itemView跟随手指滑动
                    //这里重新判断是避免itemView中不拦截ACTION_DOWN事件，则后续ACTION_MOVE并不会走onInterceptTouchEvent()方法
                    boolean isHorizontalMove = (Math.abs(velocityX) >= MINIMUM_VELOCITY && velocityX > velocityY
                            || moveX > moveY && moveX > mTouchSlop) && mMenuWidth > 0 && getScrollState() == 0;
                    if(isHorizontalMove){
                        int dx = mLastX - x;
                        if(dx + mMoveView.getScrollX() >0 && dx + mMoveView.getScrollX() <=mMenuWidth){
                            mMoveView.scrollBy(dx,0);
                        }
                        mLastX = x;
                        mMoving = true;
                        return true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return super.onTouchEvent(e);
    }


    @Override
    public void computeScroll() {
        if (mScroller.computeScrollOffset()) {
            if (isInWindow(mLastView)){
                mLastView.scrollTo(mScroller.getCurrX(), 0);
                invalidate();
            }else {
                //若处于动画的itemView滑出屏幕，则终止动画，并让其到达结束点位置
                mScroller.abortAnimation();
                mLastView.scrollTo(mScroller.getFinalX(),0);
            }
        }
    }

    /**
     * 使用Scroller关闭菜单栏
     */
    public void closeMenu(){
        mScroller.startScroll(mLastView.getScrollX(),0, -mLastView.getScrollX(), 0 ,500);
        invalidate();
    }

    /**
     * 即刻关闭菜单栏
     */
    public void closeMenuNow(){
        if (mLastView != null && mLastView.getScrollX() != 0) {
            mLastView.scrollTo(0, 0);
        }
    }

    /**
     * 获取VelocityTracker实例，并为其添加事件
     * @param e 触碰事件
     */
    private void addVelocityEvent(MotionEvent e){
        if (mVelocity == null){
            mVelocity = VelocityTracker.obtain();
        }
        mVelocity.addMovement(e);
    }

    /**
     * 释放VelocityTracker
     */
    private void releaseVelocity(){
        if (mVelocity != null){
            mVelocity.clear();
            mVelocity.recycle();
            mVelocity = null;
        }
    }

    /**
     * 判断该itemView是否显示在屏幕内
     * @param view itemView
     * @return isInWindow
     */
    private boolean isInWindow(View view){
        if (getLayoutManager() instanceof LinearLayoutManager) {
            LinearLayoutManager manager = (LinearLayoutManager) getLayoutManager();
            int firstPosition = manager.findFirstVisibleItemPosition();
            int lastPosition = manager.findLastVisibleItemPosition();
            int currentPosition = manager.getPosition(view);
            return currentPosition >= firstPosition && currentPosition <= lastPosition;
        }
        return true;
    }

}
