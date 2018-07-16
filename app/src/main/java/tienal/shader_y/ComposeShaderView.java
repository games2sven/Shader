package tienal.shader_y;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ComposeShader;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by lzy on 2017/5/16.
 */

public class ComposeShaderView extends View {
	Bitmap mBitmap;
	BitmapShader mBitmapShader;
	Paint mPaint;

	LinearGradient mLinearGradient;
	ComposeShader mComposeShader;

	int mWidth;
	int mHeight;

	public ComposeShaderView(Context context) {
		super(context);
		init();
	}

	public ComposeShaderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		mBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.test_3);
		mBitmapShader = new BitmapShader(mBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = w;
		mHeight = h;
		mLinearGradient = new LinearGradient(0, 0, w, h, new int[] {
				Color.WHITE, Color.LTGRAY, Color.TRANSPARENT, Color.GREEN }, null, Shader.TileMode.CLAMP);
		mComposeShader = new ComposeShader(mBitmapShader, mLinearGradient, PorterDuff.Mode.MULTIPLY);
		mPaint.setShader(mComposeShader);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		canvas.drawOval(0, 0, mWidth, mHeight, mPaint);
	}
}
