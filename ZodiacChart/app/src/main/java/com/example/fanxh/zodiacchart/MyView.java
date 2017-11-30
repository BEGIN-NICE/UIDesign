package com.example.fanxh.zodiacchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fanxh on 2017/11/30.
 */

public class MyView extends View {
    private Paint mPaint;
    private Paint mPaintText;
    private float widthSize;
    private float heightSize;
    private Canvas mCancas;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaintText = new Paint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthmode = MeasureSpec.getMode(widthMeasureSpec);
        heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightmode = MeasureSpec.getMode(heightMeasureSpec);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
        canvas.translate(widthSize/2,heightSize/2);

        RectF ovel = new RectF(-widthSize/3,-widthSize/3,widthSize/3,widthSize/3);
        RectF ovel1 = new RectF(-widthSize/3+50,-widthSize/3+50,widthSize/3-50,widthSize/3-50);
        canvas.drawArc(ovel,135,270,false,mPaint);
        canvas.drawArc(ovel1,135,270,false,mPaint);
        RectF rectF = new RectF(widthSize/3-40,0,widthSize/3-10,13);
        canvas.rotate(131);
        mPaint.setStyle(Paint.Style.FILL);
        for(int i = 0;i<270;i+=5) {
            canvas.rotate(5);
            canvas.drawRect(rectF, mPaint);
        }
    }
}
