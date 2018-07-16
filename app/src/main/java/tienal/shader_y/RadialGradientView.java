package tienal.shader_y;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lzy on 2017/4/12.
 */
public class RadialGradientView extends View {
    private RadialGradient mRadialGradient;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mWidth;
    int mHeight;

    public RadialGradientView(Context context) {
        super(context);
    }

    public RadialGradientView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getMeasuredWidth();
        mHeight = getMeasuredHeight();
        int[] colors = new int[]{0xffff0000, 0xff00ff00, 0xff0000ff, 0xffffff00};
        float[] stops = new float[]{0f, 0.3f, 0.7f, 1f};
        mRadialGradient = new RadialGradient(mWidth / 2, mHeight / 2, 20, colors, stops, Shader.TileMode.REPEAT);
        mPaint.setShader(mRadialGradient);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(new Rect(0, 0, mWidth, mHeight), mPaint);
    }
}
