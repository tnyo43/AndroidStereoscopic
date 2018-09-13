package com.example.ktomoya.androidstereoscopic;

import android.content.Context;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.TextView;

public class StescoTextView extends AppCompatTextView {

    public int level;
    Context context;

    public StescoTextView(Context context) {
        this(context, null);
    }

    public StescoTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StescoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        level = 0;
    }
}
