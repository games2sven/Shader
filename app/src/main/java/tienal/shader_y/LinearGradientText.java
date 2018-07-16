package tienal.shader_y;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lzy on 2017/4/13.
 */
public class LinearGradientText extends TextView {
    Paint mPaint;
    LinearGradient mLinearGradient;
    private Matrix mMatrix;
    private int mX;

    public LinearGradientText(Context context) {
        super(context);
        init();
    }

    public LinearGradientText(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        //注意这里必须是TextView的Paint，因为绘制文字就是用这个Paint
        mPaint = getPaint();
        mMatrix = new Matrix();
    }

    private void initAnimtor(int width) {
        ValueAnimator animator = ValueAnimator.ofInt(0, width);  //我们设置value的值为0-getMeasureWidth的3 倍
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                mX = (Integer) animation.getAnimatedValue();
                postInvalidate();
            }
        });
        animator.setRepeatMode(ValueAnimator.RESTART);   //重新播放
        animator.setRepeatCount(ValueAnimator.INFINITE);   //无限循环
        animator.setDuration(2000);
        animator.start();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //设置LinearGradient,绘制的范围这里设置的是-w到w，相当于两个宽度，然后把Shader向右移动实现了效果
        mLinearGradient = new LinearGradient(0, 0, w, 0, new int[]{getCurrentTextColor(), Color.RED, Color.YELLOW, Color.BLUE, getCurrentTextColor(),}
                , null, Shader.TileMode.CLAMP);
        mPaint.setShader(mLinearGradient);

        initAnimtor(w);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mMatrix.reset();
        mMatrix.preTranslate(mX, 0);
        mLinearGradient.setLocalMatrix(mMatrix);
    }
}
