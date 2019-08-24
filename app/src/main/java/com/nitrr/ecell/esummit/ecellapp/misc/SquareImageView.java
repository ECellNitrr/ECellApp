package com.nitrr.ecell.esummit.ecellapp.misc;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import com.nitrr.ecell.esummit.ecellapp.R;

public class SquareImageView extends androidx.appcompat.widget.AppCompatImageView {

    String heightSquare;

    public SquareImageView(Context context) {
        super(context);
    }

    public SquareImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.SquareImageView);
        heightSquare = a.getString(R.styleable.SquareImageView_heightSquare);
        a.recycle();
    }

    public SquareImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        if(heightSquare.equals("true"))
            setMeasuredDimension(height, height);
        else
            setMeasuredDimension(width, width);
    }
}
