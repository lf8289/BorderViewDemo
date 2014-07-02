package com.example.borderviewdemo.View;

import com.example.borderviewdemo.Utils.DensityUtil;
import com.example.tvborderviewdemo.R;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

public class FocusBorderView extends ImageView {
    private static int X_BORDER_SIZE = 0;
    private static int Y_BORDER_SIZE = 0;
    private static int TRAN_DUR_ANIM = 250;

    // private AnimationDrawable mBoxBgAnim;
    // private static Animation mBoxAnimNormal;

    public FocusBorderView(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.setBackgroundResource(R.drawable.focus_bound);
        this.setVisibility(View.INVISIBLE);
        // this.setBackgroundResource(R.anim.box_normal);
        // mBoxAnimNormal = android.view.animation.AnimationUtils.loadAnimation(
        // context, R.anim.box_alpha);
        // mBoxBgAnim = (AnimationDrawable) this.getBackground();
    }

    public void runTranslateAnimation(View toView) {
        runTranslateAnimation(toView, 1.0F, 1.0F);
    }

    public void runTranslateAnimation(View toView, float scaleX, float scaleY) {
/*        ViewGroup root = (ViewGroup) this.getParent();
        Rect fromRect = new Rect();
        Rect toRect = new Rect();

        root.offsetDescendantRectToMyCoords(this, fromRect);
        root.offsetDescendantRectToMyCoords(toView, toRect);
        int x = toRect.left - fromRect.left;
        int y = toRect.top - fromRect.top;*/

        /*
         * int x = toView.getLeft() - this.getLeft(); int y = toView.getTop() -
         * this.getTop();
         */
        
        Rect fromRect = findLocationWithView(this);
        Rect toRect = findLocationWithView(toView);
        
        int x = toRect.left - fromRect.left;
        int y = toRect.top - fromRect.top;

        int deltaX = (toView.getWidth() - this.getWidth()) / 2;
        int deltaY = (toView.getHeight() - this.getHeight()) / 2;
        x = DensityUtil.dip2px(this.getContext(), x + deltaX);
        y = DensityUtil.dip2px(this.getContext(), y + deltaY);

        float toWidth = toView.getWidth() * scaleX;
        float toHeight = toView.getHeight() * scaleY;
        Log.d("LIF",
                "width = " + toView.getWidth() + ", height = "
                        + toView.getHeight());
        float targetScaleX = (float) toWidth
                / (float) (this.getWidth() - 2 * X_BORDER_SIZE);
        float targetScaleY = (float) toHeight
                / (float) (this.getHeight() - 2 * Y_BORDER_SIZE);
        int width = (int) (toWidth + 2 * X_BORDER_SIZE * targetScaleX);
        int height = (int) (toHeight + 2 * Y_BORDER_SIZE * targetScaleY);

        flyWhiteBorder(width, height, x, y);
    }

    /**
     * 白色焦点框飞动、移动、变大
     * 
     * @param width
     *            白色框的宽(非放大后的)
     * @param height
     *            白色框的高(非放大后的)
     * @param paramFloat1
     *            x坐标偏移量，相对于初始的白色框的中心点
     * @param paramFloat2
     *            y坐标偏移量，相对于初始的白色框的中心点
     * */
    @SuppressLint("NewApi")
    private void flyWhiteBorder(int width, int height, float x, float y) {
        int mWidth = this.getWidth();
        int mHeight = this.getHeight();

        float scaleX = (float) width / (float) mWidth;
        float scaleY = (float) height / (float) mHeight;

        Log.d("LIF", "mWidth = " + mWidth + ", mHeight = " + mHeight);
        Log.d("LIF", "width = " + width + ", height = " + height);
        Log.d("LIF", "x = " + x + ", y = " + y);
        Log.d("LIF", "scaleX = " + scaleX + ", scaleY = " + scaleY);

        animate().translationX(x).translationY(y).setDuration(TRAN_DUR_ANIM)
                .scaleX(scaleX).scaleY(scaleY)
                .setInterpolator(new DecelerateInterpolator())
                .setListener(flyListener).start();
    }

    /**
     * 获取View的位置
     * 
     * @param view
     *            获取的控件
     * @return 位置
     */
    public Rect findLocationWithView(View view) {
        /*
         * int[] location = new int[2]; view.getLocationOnScreen(location);
         * return new ViewLocation(location[0], location[1]);
         */

        ViewGroup root = (ViewGroup) this.getParent();
        Rect rect = new Rect();

        root.offsetDescendantRectToMyCoords(view, rect);
        return rect;
    }

    @SuppressLint("NewApi")
    private Animator.AnimatorListener flyListener = new Animator.AnimatorListener() {

        @Override
        public void onAnimationCancel(Animator arg0) {
        }

        @Override
        public void onAnimationEnd(Animator arg0) {
            FocusBorderView.this.setVisibility(View.VISIBLE);
        }

        @Override
        public void onAnimationRepeat(Animator arg0) {
        }

        @Override
        public void onAnimationStart(Animator arg0) {
        }

    };
}
