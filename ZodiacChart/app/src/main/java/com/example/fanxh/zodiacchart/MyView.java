package com.example.fanxh.zodiacchart;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by fanxh on 2017/11/30.
 */

public class MyView extends View {
    /**
     * 弧线画笔
     */
    private Paint mPaint;
    private Paint mRectPaint;
    /**
     * 空气污染标度画笔
     */
    private Paint mTextPaint;
    /**
     * 空气污染程度画笔
     */
    private Paint mStatusPaint;
    /**
     * 空气指数画笔
     */
    private Paint mTextCenterPaint;
    /**
     * 建议画笔
     */
    private Paint mMessagePaint;
    /**
     * 默认进度条
     */
    private Paint mProgressNormalPaint;
    /**
     * 着色部分画笔
     */
    private Paint mProgressPaint;
    private Path mPath;
    private float widthSize;
    private float heightSize;
    private int[] mColors;
    private float[] position;
    /**
     * 外弧背景
     */
    private float mXArc;

    public MyView(Context context) {
        super(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs, 0);
    }

    public MyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typeArray = context.obtainStyledAttributes(attrs, R.styleable.MyView, 0, 0);
//        mStrokeWidth = typeArray.getDimension(R.styleable.MyView_strokeWidth, 12);
//        mTextColor = typeArray.getColor(R.styleable.MyView_textColor, 0xffffff);
//        mTextSize = typeArray.getDimension(R.styleable.MyView_textSize, 180);
        typeArray.recycle();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initVariable() {
        mColors = new int[]{
                Color.parseColor("#a21482"),
                Color.parseColor("#8ad00e"),
                Color.parseColor("#8ad00e"),
                Color.parseColor("#fed012"),
                Color.parseColor("#ffa000"),
                Color.parseColor("#f5633b"),
                Color.parseColor("#d32795")
        };
        position = new float[]{0.125f,0.375f, 0.5f, 0.625f, 0.75f, 0.875f, 1f};
        mPath = new Path();
        //弧线画笔
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(getContext().getColor(R.color.colorWhite));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2);
        //小正方形画笔
        mRectPaint = new Paint();
        mRectPaint.setAntiAlias(true);
        mRectPaint.setColor(getContext().getColor(R.color.colorWhite));
        mRectPaint.setStyle(Paint.Style.FILL);
        mRectPaint.setStrokeWidth(2);
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
        //默认进度条画笔
        mProgressNormalPaint = new Paint();
        mProgressNormalPaint.setAntiAlias(true);
        mProgressNormalPaint.setStyle(Paint.Style.STROKE);
        mProgressNormalPaint.setStrokeWidth(40);
        //进度条画笔
        mProgressPaint = new Paint();
        mProgressPaint.setAntiAlias(true);
        mProgressPaint.setStyle(Paint.Style.STROKE);
        mProgressPaint.setStrokeWidth(40);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        widthSize = w;
        heightSize = h;
        initVariable();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mXArc = Math.min(widthSize, heightSize) / 3;
        canvas.translate(widthSize / 2, heightSize / 2);
        drawArc(canvas);
        canvas.rotate(45);
        drawScale(canvas);
        drawProgress(canvas);
        drawDescribeText(canvas);
    }

    /**
     * 绘制双弧线
     */
    public void drawArc(Canvas canvas) {
        if (mXArc > 80 && canvas != null) {
            RectF ovel = new RectF(-mXArc, -mXArc, mXArc, mXArc);
            RectF ovel1 = new RectF(-mXArc + 80, -mXArc + 80, mXArc - 80, mXArc - 80);
            canvas.drawArc(ovel, 135, 270, false, mPaint);
            canvas.drawArc(ovel1, 135, 270, false, mPaint);
        }
    }

    /**
     * 绘制刻度
     */
    public void drawScale(Canvas canvas) {
        if (canvas != null) {
            for (int i = 0; i <= 270; i += 45) {
                canvas.drawLine(0, mXArc - 100, 0, mXArc - 85, mRectPaint);
                canvas.rotate(45);
            }
            canvas.save();
        }
    }

    /**
     * 绘制进度条
     */
    public void drawProgress(Canvas canvas) {
        if (canvas != null) {
            RectF rectF = new RectF(-mXArc + 40, -mXArc + 40, mXArc - 40, mXArc - 40);
            mPath.addArc(rectF, 135, 270);
            DashPathEffect dashPathEffect = new DashPathEffect(new float[]{18, 15}, 0);
            mProgressNormalPaint.setPathEffect(dashPathEffect);
            canvas.drawPath(mPath, mProgressNormalPaint);

            mPath.reset();
            RectF rectF1 = new RectF(-mXArc + 40, -mXArc + 40, mXArc - 40, mXArc - 40);
            mPath.addArc(rectF1, 135, 270);
            Shader s = new SweepGradient(0, 0, mColors, position);
            mProgressPaint.setShader(s);
            DashPathEffect dashPathEffect1 = new DashPathEffect(new float[]{18, 15}, 0);
            mProgressPaint.setPathEffect(dashPathEffect1);
            canvas.drawPath(mPath, mProgressPaint);
        }
    }

    /**
     * 绘制空气质量描述文本
     */
    public void drawDescribeText(Canvas canvas) {
        if (canvas != null) {
            Rect rectCenter = new Rect();
            String stringCenter = "137";
            mTextCenterPaint.getTextBounds(stringCenter, 0, stringCenter.length(), rectCenter);
            float xCenter = -rectCenter.width() / 2;
            float yCenter = rectCenter.height() / 3;
            canvas.drawText(stringCenter, xCenter, yCenter, mTextCenterPaint);

            Rect rect = new Rect();
            String string = "空气优";
            mTextCenterPaint.setTextSize(60);
            mTextCenterPaint.getTextBounds(string, 0, string.length(), rect);
            canvas.drawText(string, -rect.width() / 2, yCenter + 100, mTextCenterPaint);

            Rect rectOneStatus = new Rect();
            String stringOneStatus = "健康";
            mStatusPaint.getTextBounds(stringOneStatus, 0, stringOneStatus.length(), rectOneStatus);
            float xOneStatus = (float) ((mXArc + 30) * Math.cos(3 * Math.PI / 4) - rectOneStatus.width());
            float yOneStatus = (float) ((mXArc + 30) * Math.sin(3 * Math.PI / 4));
            canvas.drawText(stringOneStatus, xOneStatus, yOneStatus, mStatusPaint);

            Rect rectOne = new Rect();
            String stringOne = "0";
            mTextPaint.getTextBounds(stringOne, 0, stringOne.length(), rectOne);
            float xOne = (float) ((mXArc + 30) * Math.cos(3 * Math.PI / 4) - rectOne.width() / 2 - rectOneStatus.width() / 2);
            float yOne = (float) ((mXArc + 30) * Math.sin(3 * Math.PI / 4) - rectOneStatus.height() - 3);
            canvas.drawText(stringOne, xOne, yOne, mTextPaint);

            paint("50", "优", Math.PI, canvas);
            paint("100", "良", Math.PI * 5 / 4, canvas);
            paint("200", "中度", Math.PI * 7 / 4, canvas);
            paint("300", "重度", Math.PI * 2, canvas);

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
            float xSevenStatus = (float) ((mXArc + 40) * Math.cos(Math.PI / 4));
            float ySevenStatus = (float) ((mXArc + 40) * Math.sin(Math.PI / 4));
            canvas.drawText(stringSevenStatus, xSevenStatus, ySevenStatus, mStatusPaint);

            Rect rectSeven = new Rect();
            String stringSeven = "500";
            mTextPaint.getTextBounds(stringSeven, 0, stringSeven.length(), rectSeven);
            float xSeven = (float) ((mXArc + 40) * Math.cos(Math.PI / 4) - rectSeven.width() / 2 + rectSevenStatus.width() / 2);
            float ySeven = (float) ((mXArc + 40) * Math.sin(Math.PI / 4) - rectSevenStatus.height() - 3);
            canvas.drawText(stringSeven, xSeven, ySeven, mTextPaint);

            Rect rectMessage = new Rect();
            String stringMessage = "空气很好，快去呼吸清新空气吧！";
            mMessagePaint.getTextBounds(stringMessage, 0, stringMessage.length(), rectMessage);
            float yMessage = 450;
            canvas.drawText(stringMessage, -rectMessage.width() / 2, yMessage, mMessagePaint);
        }
    }

    /**
     * 绘制刻度值和状态
     *
     * @param textString
     * @param statusString
     * @param angle
     * @param canvas
     */
    public void paint(String textString, String statusString, double angle, Canvas canvas) {
        float textX = 0;
        float textY = 0;
        float statusX = 0;
        float statusY = 0;
        Rect rectText = new Rect();
        String stringText = textString;
        mTextPaint.getTextBounds(stringText, 0, stringText.length(), rectText);
        if (Math.cos(angle) > 0) {
            textX = (float) ((mXArc + 20) * Math.cos(angle));
        } else {
            textX = (float) ((mXArc + 20) * Math.cos(angle) - rectText.width());
        }
        textY = (float) ((mXArc + 20) * Math.sin(angle));
        canvas.drawText(stringText, textX, textY, mTextPaint);
        Rect rectStatus = new Rect();
        String stringStatus = statusString;
        mStatusPaint.getTextBounds(stringStatus, 0, stringStatus.length(), rectStatus);
        if (Math.cos(angle) > 0) {
            statusX = (float) ((mXArc + 20) * Math.cos(angle) + rectText.width() / 2 - rectStatus.width() / 2);
        } else {
            statusX = (float) ((mXArc + 20) * Math.cos(angle) - rectText.width() / 2 - rectStatus.width() / 2);
        }
        statusY = (float) ((mXArc + 20) * Math.sin(angle) + rectStatus.height() + 3);
        canvas.drawText(stringStatus, statusX, statusY, mStatusPaint);
    }
}
