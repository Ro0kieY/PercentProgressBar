package com.ro0kiey.percentprogressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * PercentProgressBar
 * Created by Ro0kieY on 2017/7/25.
 */

public class PercentProgressBar extends View {

    private final int PROGRESS_BAR_CIRCULAR = 10;

    //Circular状态的默认宽高
    private final int default_layout_radius;
    //Horizontal状态的默认宽
    private final int default_layout_width;
    //Horizontal状态的默认高
    private final int default_layout_height;
    //外部圆环的默认宽度
    private final int default_outer_width;
    //外部圆环的默认颜色
    private final int default_outer_color;
    //外部圆环的默认透明度
    private final int default_outer_alpha;
    //ProgressBar的默认宽度
    private final int default_progress_bar_width;
    //ProgressBar的默认颜色
    private final int default_progress_bar_color;
    //ProgressBar的默认透明度
    private final int default_progress_bar_alpha;
    //文字默认大小
    private final int default_text_size;
    //文字默认颜色
    private final int default_text_color;
    //文字默认透明度
    private final int default_text_alpha;
    //底部小圆环的默认颜色
    private final int default_little_circle_color;
    //底部小圆环的默认透明度
    private final int default_little_circle_alpha;
    //进度最大值的默认值
    private final int default_max_progress;
    //当前进度的默认值
    private final int default_current_progress;

    //外层圆环的画笔
    private Paint mOuterPaint;
    //ProgressBar的画笔
    private Paint mProgressBarPaint;
    //底部小圆的画笔
    private Paint mLittleCirclePaint;
    //中间百分比的画笔
    private Paint mTextPaint;

    //外层圆环的路径
    private Path mOuterPath = new Path();
    //ProgressBar的路径
    private Path mProgressBarPath = new Path();

    //外部圆环的宽度
    private float mOuterWidth;
    //外部圆环的颜色
    private int mOuterColor;
    //外部圆环的透明度
    private int mOuterAlpha;
    //ProgressBar的宽度
    private float mProgressBarWidth;
    //ProgressBar的颜色
    private int mProgressBarColor;
    //ProgressBar的透明度
    private int mProgressBarAlpha;
    //文字大小
    private float mTextSize;
    //文字颜色
    private int mTextColor;
    //文字透明度
    private int mTextAlpha;
    //文字的长度
    private float mTextWidth;
    //底部小圆环的颜色
    private int mLittleCircleColor;
    //底部小圆环的透明度
    private int mLittleCircleAlpha;
    //进度最大值
    private int mMaxProgress;
    //进度当前值
    private int mCurrentProgress;
    //进度条样式
    private int mProgressBarStyle;

    //View的宽和高
    private float width, height;
    //View的Padding参数
    private float paddingLeft, paddingRight, paddingTop, paddingBottom;

    public PercentProgressBar(Context context) {
        this(context, null);
    }

    public PercentProgressBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PercentProgressBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        //初始化默认参数
        default_layout_radius = 300;
        default_layout_width = 500;
        default_layout_height = 50;
        default_outer_width = 34;
        default_outer_color = 0XFFFFFF;
        default_outer_alpha = 50;
        default_progress_bar_width = default_outer_width - 12;
        default_progress_bar_color = 0X5DD4CD;
        default_progress_bar_alpha = 255;
        default_text_size = 50;
        default_text_color = 0XFFFFFF;
        default_text_alpha = 255;
        default_little_circle_color = 0XFFFFFF;
        default_little_circle_alpha = 255;
        default_max_progress = 100;
        default_current_progress = 0;

        //必须用四个参数的方法
        TypedArray ta = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PercentProgressBar, defStyleAttr, 0);

        mOuterWidth = ta.getDimension(R.styleable.PercentProgressBar_outer_width, default_outer_width);
        mOuterColor = ta.getColor(R.styleable.PercentProgressBar_outer_color, default_outer_color);
        mOuterAlpha = ta.getInt(R.styleable.PercentProgressBar_outer_alpha, default_outer_alpha);
        mProgressBarWidth = ta.getDimension(R.styleable.PercentProgressBar_progress_bar_width, default_progress_bar_width);
        mProgressBarColor = ta.getColor(R.styleable.PercentProgressBar_progress_bar_color, default_progress_bar_color);
        mProgressBarAlpha = ta.getInt(R.styleable.PercentProgressBar_progress_bar_alpha, default_progress_bar_alpha);
        mTextSize = ta.getFloat(R.styleable.PercentProgressBar_text_size, default_text_size);
        mTextColor = ta.getColor(R.styleable.PercentProgressBar_text_color, default_text_color);
        mTextAlpha = ta.getInt(R.styleable.PercentProgressBar_text_alpha, default_text_alpha);
        mLittleCircleColor = ta.getColor(R.styleable.PercentProgressBar_little_circle_color, default_little_circle_color);
        mLittleCircleAlpha = ta.getInt(R.styleable.PercentProgressBar_little_circle_alpha, default_little_circle_alpha);
        mMaxProgress = ta.getInt(R.styleable.PercentProgressBar_max_progress, default_max_progress);
        mCurrentProgress = ta.getInt(R.styleable.PercentProgressBar_current_progress, default_current_progress);
        mProgressBarStyle = ta.getInt(R.styleable.PercentProgressBar_progress_bar_style, PROGRESS_BAR_CIRCULAR);
        //Log.d("TypedArray ", "YPercentBarView: " + mProgressBarStyle + "PROGRESS_BAR_CIRCULAR " + PROGRESS_BAR_CIRCULAR);

        ta.recycle();

        initPaint();

    }

    /**
     * 初始化画笔
     */
    private void initPaint() {

        mOuterPaint = new Paint();
        mOuterPaint.setAntiAlias(true);
        mOuterPaint.setStrokeWidth(mOuterWidth);
        mOuterPaint.setStrokeCap(Paint.Cap.ROUND);
        mOuterPaint.setStyle(Paint.Style.STROKE);
        mOuterPaint.setColor(mOuterColor);
        mOuterPaint.setAlpha(mOuterAlpha);

        mProgressBarPaint = new Paint();
        mProgressBarPaint.setAntiAlias(true);
        mProgressBarPaint.setStrokeWidth(mProgressBarWidth);
        mProgressBarPaint.setStrokeCap(Paint.Cap.ROUND);
        mProgressBarPaint.setStyle(Paint.Style.STROKE);
        //mProgressBarPaint.setDither(true);
        mProgressBarPaint.setColor(mProgressBarColor);
        mProgressBarPaint.setAlpha(mProgressBarAlpha);
        //mProgressBarPaint.setShadowLayer(50, 0, 0, Color.RED);

        mLittleCirclePaint = new Paint();
        mLittleCirclePaint.setAntiAlias(true);
        mLittleCirclePaint.setStrokeWidth(3f);
        mLittleCirclePaint.setStrokeCap(Paint.Cap.ROUND);
        mLittleCirclePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mLittleCirclePaint.setColor(mLittleCircleColor);
        mLittleCirclePaint.setAlpha(mLittleCircleAlpha);

        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setTypeface(Typeface.DEFAULT_BOLD);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setAlpha(mTextAlpha);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        
        int width;
        int height;

        if (widthSpecMode == MeasureSpec.AT_MOST){
            if (mProgressBarStyle == PROGRESS_BAR_CIRCULAR){
                width = default_layout_radius;
            } else {
                width = default_layout_width;
            }
        } else {
            width = widthSpecSize;
        }
        if (heightSpecMode == MeasureSpec.AT_MOST){
            if (mProgressBarStyle == PROGRESS_BAR_CIRCULAR){
                height = default_layout_radius;
            } else {
                height = default_layout_height;
            }
        } else {
            height = heightSpecSize;
        }

        //Log.d("Y", "onMeasure " + widthSpecMode + ": " + widthSpecSize);
        //Log.d("Y", "onMeasure " + heightSpecMode + ": " +heightSpecSize);

        if (mProgressBarStyle == PROGRESS_BAR_CIRCULAR){
            int minSize = Math.min(width, height);
            setMeasuredDimension(minSize, minSize);
        } else {
            setMeasuredDimension(width, height);
        }
        //Log.d("PercentProgressBar", "onMeasure: ");

        /*if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(default_width_and_height, default_width_and_height);
        } else if (widthSpecMode == MeasureSpec.EXACTLY && heightSpecMode == MeasureSpec.EXACTLY){
            setMeasuredDimension(width, height);
        }*/
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //Log.d("PercentProgressBar", "onSizeChanged: ");
        //考虑padding参数
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();
        width = getWidth() - paddingLeft - paddingRight;
        height = getHeight() - paddingTop - paddingBottom;

    }

    /**
     * 计算整个进度条外部的路径
     */
    private void calculateOuterPath() {
        int offset = 30;
        if (mProgressBarStyle == PROGRESS_BAR_CIRCULAR){
            mOuterPath.addCircle(paddingLeft + (width / 2), paddingTop + (height / 2), width / 2 - mOuterWidth, Path.Direction.CW);
        } else {
            mOuterPath.moveTo(paddingLeft + mTextWidth + offset, (paddingTop + height + paddingBottom) / 2);
            mOuterPath.lineTo(paddingLeft + width - mOuterWidth, (paddingTop + height + paddingBottom) / 2);
        }

    }

    /**
     * 计算当前进度条的路径
     */
    private void calculateProgressBarPath() {
        int offset = 30;
        float percent = (mCurrentProgress / (float)mMaxProgress);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (mProgressBarStyle == PROGRESS_BAR_CIRCULAR){
                mProgressBarPath.addArc(paddingLeft + mOuterWidth, paddingTop + mOuterWidth, paddingLeft + width - mOuterWidth, paddingTop + height - mOuterWidth, 90, - 360 * percent);
            } else {
                mProgressBarPath.moveTo(paddingLeft + mTextWidth + offset, (paddingTop + height + paddingBottom) / 2);
                mProgressBarPath.lineTo(paddingLeft + mTextWidth + offset + percent * (width - mTextWidth - offset - mOuterWidth), (paddingTop + height + paddingBottom) / 2);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawText(canvas);
        calculateOuterPath();
        calculateProgressBarPath();
        drawOuter(canvas);
        drawProgressBar(canvas);
        drawLittleCircle(canvas);
    }

    /**
     * 画文字
     * @param canvas
     */
    private void drawText(Canvas canvas) {
        mTextPaint.setTextSize(mOuterWidth);
        mTextWidth = mTextPaint.measureText("100%");
        float textWidth = mTextPaint.measureText(mCurrentProgress + "%");
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        float x, y;
        if (mProgressBarStyle == PROGRESS_BAR_CIRCULAR){
            x = (paddingLeft + paddingRight + width) / 2 - textWidth / 2;
            y = (height + paddingTop + paddingBottom) / 2 - (fm.top + fm.bottom) / 2;
        } else {
            x = paddingLeft;
            y = (height + paddingTop + paddingBottom) / 2 - (fm.top + fm.bottom) / 2;
        }
        canvas.drawText(mCurrentProgress + "%", x, y, mTextPaint);
    }

    /**
     * 画进度条起点小圆环
     * @param canvas
     */
    private void drawLittleCircle(Canvas canvas) {
        canvas.save();
        if (mProgressBarStyle == PROGRESS_BAR_CIRCULAR) {
            canvas.translate(paddingLeft + width / 2, paddingTop + height - mOuterWidth);
        } else {
            canvas.translate(paddingLeft + mTextWidth + 40, (paddingTop + height + paddingBottom) / 2);
        }
        canvas.drawCircle(0, 0, 5, mLittleCirclePaint);
        canvas.restore();
    }

    /**
     * 画进度条
     * @param canvas
     */
    private void drawProgressBar(Canvas canvas) {
        canvas.drawPath(mProgressBarPath, mProgressBarPaint);
    }

    /**
     * 画进度条外部的框
     * @param canvas
     */
    private void drawOuter(Canvas canvas) {
        canvas.drawPath(mOuterPath, mOuterPaint);
    }



    /* --------- Below are getter and setters --------- */

    public float getOuterWidth() {
        return mOuterWidth;
    }

    public void setOuterWidth(float OuterWidth) {
        this.mOuterWidth = OuterWidth;
        mOuterPaint.setStrokeWidth(mOuterWidth);
        //进度条的宽度默认比外框小12
        mProgressBarPaint.setStrokeWidth(mOuterWidth - 12);
        invalidate();
    }

    public int getOuterColor() {
        return mOuterColor;
    }

    public void setOuterColor(int OuterColor) {
        this.mOuterColor = OuterColor;
        mOuterPaint.setColor(mOuterColor);
        invalidate();
    }

    public int getOuterAlpha() {
        return mOuterAlpha;
    }

    public void setOuterAlpha(int OuterAlpha) {
        this.mOuterAlpha = OuterAlpha;
        mOuterPaint.setAlpha(mOuterAlpha);
        invalidate();
    }

    public float getProgressBarWidth() {
        return mProgressBarWidth;
    }

    public void setProgressBarWidth(float ProgressBarWidth) {
        this.mProgressBarWidth = ProgressBarWidth;
        mProgressBarPaint.setStrokeWidth(mProgressBarWidth);
        mOuterPaint.setStrokeWidth(mProgressBarWidth + 12);
        invalidate();
    }

    public int getProgressBarColor() {
        return mProgressBarColor;
    }

    public void setProgressBarColor(int ProgressBarColor) {
        this.mProgressBarColor = ProgressBarColor;
        mProgressBarPaint.setColor(mProgressBarColor);
        invalidate();
    }

    public int getProgressBarAlpha() {
        return mProgressBarAlpha;
    }

    public void setProgressBarAlpha(int ProgressBarAlpha) {
        this.mProgressBarAlpha = ProgressBarAlpha;
        mProgressBarPaint.setAlpha(mProgressBarAlpha);
        invalidate();
    }

    public float getTextSize() {
        return mTextSize;
    }

    public void setTextSize(float TextSize) {
        this.mTextSize = TextSize;
        mTextPaint.setTextSize(mTextSize);
        invalidate();
    }

    public int getTextColor() {
        return mTextColor;
    }

    public void setTextColor(int TextColor) {
        this.mTextColor = TextColor;
        mTextPaint.setColor(mTextColor);
        invalidate();
    }

    public int getTextAlpha() {
        return mTextAlpha;
    }

    public void setTextAlpha(int TextAlpha) {
        this.mTextAlpha = TextAlpha;
        mTextPaint.setAlpha(mTextAlpha);
        invalidate();
    }

    public int getLittleCircleColor() {
        return mLittleCircleColor;
    }

    public void setLittleCircleColor(int LittleCircleColor) {
        this.mLittleCircleColor = LittleCircleColor;
        mLittleCirclePaint.setColor(mLittleCircleColor);
        invalidate();
    }

    public int getLittleCircleAlpha() {
        return mLittleCircleAlpha;
    }

    public void setLittleCircleAlpha(int LittleCircleAlpha) {
        this.mLittleCircleAlpha = LittleCircleAlpha;
        mLittleCirclePaint.setAlpha(mLittleCircleAlpha);
        invalidate();
    }

    public int getMaxProgress() {
        return mMaxProgress;
    }

    public void setMaxProgress(int MaxProgress) {
        this.mMaxProgress = MaxProgress;
        invalidate();
    }

    public int getCurrentProgress() {
        return mCurrentProgress;
    }

    public void setCurrentProgress(int CurrentProgress) {
        if (CurrentProgress >= mMaxProgress){
            this.mCurrentProgress = mMaxProgress;
        } else {
            this.mCurrentProgress = CurrentProgress;
        }
        invalidate();
    }

    public void progressIncreasedBy(int interval){
        if (mCurrentProgress + interval <= mMaxProgress){
            setCurrentProgress(mCurrentProgress + interval);
        } else {
            setCurrentProgress(mMaxProgress);
        }
    }
}
