package com.example.ktomoya.androidstereoscopic;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class StereoscopicField extends LinearLayout {

    private List<TextView> textViewList;
    public boolean isLeft;

    public StereoscopicField(Context context) {
        this(context, null);
    }

    public StereoscopicField(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StereoscopicField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        textViewList = new LinkedList<TextView>();
        isLeft = false;
    }

    protected void addText(Context context, String text, int left, int top, int size, int level) {
        /**
         * level : -5 ~ 5 (0 is the nomal view)
         */
        TextView textView = new TextView(context);
        textView.setText(text);
        textView.setTextSize(size);

        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        level = Math.max(Math.min(level, 5), -5);
        lp.topMargin = top;

        lp.leftMargin = left + level*6 * (isLeft ? 1 : -1);
        this.addView(textView, lp);

        textViewList.add(textView);
    }


}
