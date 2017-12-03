package com.example.fanxh.zodiacchart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fanxh on 2017/11/30.
 */

public class MyView extends View {

    private Paint mPaint;
    private Paint mTextPaint;
    private Paint mStatusPaint;
    private Paint mTextCenterPaint;
    private Paint mMessagePaint;
    private float widthSize;
    private float heightSize;

    private float mStrokeWidth;
    private int mTextColor;
    private float mTextSize;
    //外弧背景
    private float mXArc;
    private float mYArc;


    public MyView(Context context) {
        super(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.MyView, defStyleAttr, 0);
        mStrokeWidth = typeArray.getDimension(R.styleable.MyView_strokeWidth, 12);
        mTextColor = typeArray.getColor(R.styleable.MyView_textColor, 0xffffff);
        mTextSize = typeArray.getDimension(R.styleable.MyView_textSize, 180);
        typeArray.recycle();
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.MyView, 0, 0);
        mStrokeWidth = typeArray.getDimension(R.styleable.MyView_strokeWidth, 12);
        mTextColor = typeArray.getColor(R.styleable.MyView_textColor, 0xffffff);
        mTextSize = typeArray.getDimension(R.styleable.MyView_textSize, 180);
        typeArray.recycle();
    }

    private void initVariable() {

        mPaint = new Paint();
        mPaint.setColor(Color.WHITE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(1);
//空气指数画笔
        mTextCenterPaint = new Paint();
        mTextCenterPaint.setAntiAlias(true);
        mTextCenterPaint.setStyle(Paint.Style.FILL);
        mTextCenterPaint.setColor(mTextColor);
        mTextCenterPaint.setTextSize(mTextSize);
        //text颜色
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);

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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //弧线画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getContext().getColor(R.color.colorWhite));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        //空气指数画笔
        mTextCenterPaint = new Paint();
        mTextCenterPaint.setAntiAlias(true);
        mTextCenterPaint.setStyle(Paint.Style.FILL);
        mTextCenterPaint.setColor(getContext().getColor(R.color.colorWhite));
        mTextCenterPaint.setTextSize(180);
        //标度画笔
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setColor(getContext().getColor(R.color.colorWhite));
        mTextPaint.setTextSize(35);
        //标度状态
        mStatusPaint = new Paint();
        mStatusPaint.setAntiAlias(true);
        mStatusPaint.setStyle(Paint.Style.FILL);
        mStatusPaint.setColor(getContext().getColor(R.color.colorWhite));
        mStatusPaint.setTextSize(30);
        //建议画笔
        mMessagePaint = new Paint();
        mMessagePaint.setAntiAlias(true);
        mMessagePaint.setStyle(Paint.Style.FILL);
        mMessagePaint.setColor(getContext().getColor(R.color.colorWhite));
        mMessagePaint.setTextSize(60);

        mXArc = Math.min(widthSize,heightSize)/3;


        canvas.translate(widthSize / 2, heightSize / 2);

        RectF ovel = new RectF(-mXArc, -mXArc, mXArc, mXArc);
        RectF ovel1 = new RectF(-mXArc + 80, -mXArc + 80, mXArc - 80, mXArc - 80);
        canvas.drawArc(ovel, 135, 270, false, mPaint);
        canvas.drawArc(ovel1, 135, 270, false, mPaint);

        RectF rectF = new RectF(mXArc - 60, 0, mXArc - 20, 17);
        canvas.rotate(131);
        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 270; i += 5) {
            canvas.rotate(5);
            canvas.drawRect(rectF, mPaint);
        }
        canvas.rotate(4);
        for (int i = 0; i <= 270; i += 45) {

            canvas.drawLine(0, mXArc - 100, 0, mXArc - 80, mPaint);
            canvas.rotate(45);
        }


        Rect rectCenter = new Rect();
        String stringCenter = "137";
        mTextCenterPaint.getTextBounds(stringCenter, 0, stringCenter.length(), rectCenter);
        float xCenter = -rectCenter.width() / 2;
        float yCenter = rectCenter.height() / 3;
        canvas.drawText(stringCenter, xCenter, yCenter, mTextCenterPaint);

        Rect rect = new Rect();
        String string = "空气优";
        mPaint.setTextSize(60);
        mPaint.getTextBounds(string, 0, string.length(), rect);
        canvas.drawText(string, -rect.width() / 2, yCenter + 100, mPaint);


        Rect rectOneStatus = new Rect();
        String stringOneStatus = "健康";
        mStatusPaint.getTextBounds(stringOneStatus, 0, stringOneStatus.length(), rectOneStatus);
        float xOneStatus = (float) ((mXArc + 30) * Math.cos(3 * 3.14 / 4) - rectOneStatus.width());
        float yOneStatus = (float) ((mXArc + 30) * Math.sin(3 * 3.14 / 4));
        canvas.drawText(stringOneStatus, xOneStatus, yOneStatus, mStatusPaint);

        Rect rectOne = new Rect();
        String stringOne = "0";
        mTextPaint.getTextBounds(stringOne, 0, stringOne.length(), rectOne);
        float xOne = (float) ((mXArc + 30) * Math.cos(3 * 3.14 / 4) - rectOne.width() / 2 - rectOneStatus.width() / 2);
        float yOne = (float) ((mXArc + 30) * Math.sin(3 * 3.14 / 4) - rectOneStatus.height() - 3);
        canvas.drawText(stringOne, xOne, yOne, mTextPaint);

        paint("50","优",3.14,canvas);
        paint("100","良",3.14*5/4,canvas);
        paint("200","中度",3.14*7/4,canvas);
        paint("300","重度",3.14*2,canvas);

        Rect rectFourStatus = new Rect();
        String stringFourStatus = "轻度";
        mStatusPaint.getTextBounds(stringFourStatus, 0, stringFourStatus.length(), rectFourStatus);
        float yFourStatus = -mXArc - 20;
        canvas.drawText(stringFourStatus, -rectFourStatus.width() / 2, yFourStatus, mStatusPaint);

        Rect rectFour = new Rect();
        String stringFour = "150";
        mTextPaint.getTextBounds(stringFour, 0, stringFour.length(), rectFour);
        float yFour = -mXArc - rectFourStatus.height() - 23;
        canvas.drawText(stringFour, -rectFour.width() / 2, yFour, mTextPaint);

        Rect rectSevenStatus = new Rect();
        String stringSevenStatus = "严重";
        mStatusPaint.getTextBounds(stringSevenStatus, 0, stringSevenStatus.length(), rectSevenStatus);
        float xSevenStatus = (float) ((mXArc + 40) * Math.cos(3.14 / 4));
        float ySevenStatus = (float) ((mXArc + 40) * Math.sin(3.14 / 4));
        canvas.drawText(stringSevenStatus, xSevenStatus, ySevenStatus, mStatusPaint);

        Rect rectSeven = new Rect();
        String stringSeven = "500";
        mTextPaint.getTextBounds(stringSeven, 0, stringSeven.length(), rectSeven);
        float xSeven = (float) ((mXArc + 40) * Math.cos(3.14 / 4) - rectSeven.width() / 2 + rectSevenStatus.width() / 2);
        float ySeven = (float) ((mXArc + 40) * Math.sin(3.14 / 4) - rectSevenStatus.height() - 3);
        canvas.drawText(stringSeven, xSeven, ySeven, mTextPaint);

        Rect rectMessage = new Rect();
        String stringMessage = "空气很好，快去呼吸清新空气吧！";
        mMessagePaint.getTextBounds(stringMessage, 0, stringMessage.length(), rectMessage);
        float yMessage = 450;
        canvas.drawText(stringMessage, -rectMessage.width() / 2, yMessage, mMessagePaint);

    }

    public void paint(String textString,String statusString,double angle,Canvas canvas){
        float textX = 0;
        float textY = 0;
        float statusX = 0;
        float statusY = 0;
        Rect rectText = new Rect();
        String stringText = textString;
        mTextPaint.getTextBounds(stringText, 0, stringText.length(), rectText);
        if (Math.cos(angle)>0) {
             textX = (float) ((mXArc + 20) * Math.cos(angle));
        }else {
            textX = (float) ((mXArc+20)*Math.cos(angle)-rectText.width());
        }
         textY = (float) ((mXArc + 20) * Math.sin(angle));
        canvas.drawText(stringText, textX, textY, mTextPaint);

        Rect rectStatus = new Rect();
        String stringStatus = statusString;
        mStatusPaint.getTextBounds(stringStatus, 0, stringStatus.length(), rectStatus);
        if (Math.cos(angle)>0) {
            statusX = (float) ((mXArc + 20) * Math.cos(angle) + rectText.width() / 2 - rectStatus.width() / 2);
        }else {
            statusX = (float) ((mXArc + 20) * Math.cos(angle) - rectText.width() / 2 - rectStatus.width() / 2);
        }
        statusY = (float) ((mXArc + 20) * Math.sin(angle) + rectStatus.height() + 3);
        canvas.drawText(stringStatus, statusX, statusY, mStatusPaint);
    }


    public static int dp2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }
}
