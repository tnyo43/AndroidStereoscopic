package com.example.ktomoya.androidstereoscopic;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class StereoscopicView extends LinearLayout{

    StereoscopicField rightsf, leftsf;

    public StereoscopicView(Context context) {
        this(context, null);
    }

    public StereoscopicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StereoscopicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View layout = LayoutInflater.from(context).inflate(R.layout.stereoscopic_view, this);

        rightsf = (StereoscopicField)findViewById(R.id.ss_right);
        rightsf.isLeft = false;
        leftsf = (StereoscopicField)findViewById(R.id.ss_left);
        leftsf.isLeft = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void addText(Context context, String text, int left, int top, int size, int level) {
        /**
         * level : -5 ~ 5 (0 is the nomal view)
         */
        rightsf.addText(context, text, left, top, size, level);
        leftsf.addText(context, text, left, top, size, level);
    }


}
