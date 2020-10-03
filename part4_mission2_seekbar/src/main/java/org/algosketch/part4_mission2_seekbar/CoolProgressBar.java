package org.algosketch.part4_mission2_seekbar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

public class CoolProgressBar extends View {
    Context context;
    int progress;

    ArrayList<OnChangeListener> listeners;

    public CoolProgressBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }

    private void init(AttributeSet attrs) {
        progress = 80;
        listeners = new ArrayList<>();
    }

    public void onChange(int progress) {
        this.progress = progress;
        invalidate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        int strokeSize = getResources().getDimensionPixelSize(R.dimen.dount_stroke_size);

        if(MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST) {
            width = strokeSize + getResources().getDimensionPixelSize(R.dimen.dount_size);
        }
        if(MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST) {
            height = strokeSize + getResources().getDimensionPixelSize(R.dimen.dount_size);
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.alpha(Color.CYAN));

        int size = getResources().getDimensionPixelSize(R.dimen.dount_size);
        int strokeSize = getResources().getDimensionPixelSize(R.dimen.dount_stroke_size);
        RectF arcRect = new RectF(strokeSize/2, strokeSize/2, strokeSize/2 + size, strokeSize/2 + size);

        // progress bar 그리기
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(getResources().getDimensionPixelSize(R.dimen.dount_stroke_size));

        paint.setColor(getResources().getColor(R.color.gray));
        canvas.drawArc(arcRect, -90, 360, false, paint);
        paint.setColor(getResources().getColor(R.color.barColor));
        canvas.drawArc(arcRect, -90, progress, false, paint);

        // text 그리기
        paint.setTextSize(getResources().getDimensionPixelSize(R.dimen.dount_textSize));
        paint.setStrokeWidth(8);
        String txt = String.valueOf(progress);
        canvas.drawText(txt, getWidth()/2 - (int)(paint.measureText(txt)/2), (int)(getHeight()/2 - ((paint.descent() + paint.ascent()) / 2)), paint);
    }
}
