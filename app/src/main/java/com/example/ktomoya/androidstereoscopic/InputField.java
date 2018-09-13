package com.example.ktomoya.androidstereoscopic;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class InputField extends RelativeLayout {

    StescoTextView textView, textViewLeft, textViewRight;
    List<StescoTextView> list, left, right;
    private InputField inputfield;
    Context context;

    StereoscopicField stescoLeft, stescoRight;

    public InputField(Context context) {
        this(context, null);
    }

    public InputField(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InputField(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        list = new LinkedList<StescoTextView>();
        left = new LinkedList<StescoTextView>();
        right = new LinkedList<StescoTextView>();
        inputfield = this;


        addText("ほげ");
        addText("ばー");
        addText("ふー");

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
            Log.d("TouchEvent", "X:" + event.getX() + ",Y:" + event.getY());
            float x = event.getX(), y = event.getY();
            int xx = (int)x, yy = (int)y;
            if (event.getAction() == MotionEvent.ACTION_DOWN) {
                textView = null;
                for (int i = 0; i < list.size(); i++) {
                    StescoTextView tv = list.get(i);
                    if (tv.getX() <= x && x <= tv.getX()+tv.getWidth() && tv.getY() <= y && y <= tv.getY()+tv.getHeight()) {
                        textView = tv;
                        //textViewLeft = left.get(i);
                        //textViewRight = right.get(i);
                        break;
                    }
                }
            } else if (textView != null && event.getAction() == MotionEvent.ACTION_MOVE) {
                int l, t, r, b;
                l = xx-textView.getWidth()/2; t = yy-textView.getHeight()/2;
                r = xx+textView.getWidth()/2; b = yy+textView.getHeight()/2;
                textView.layout(l, t, r, b);

            } else if (textView != null && event.getAction() == MotionEvent.ACTION_UP){
                RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                lp.topMargin = (int) event.getY()-textView.getHeight()/2;
                lp.leftMargin = (int) event.getX()-textView.getWidth()/2;
                inputfield.removeView(textView);
                inputfield.addView(textView, lp);
            }
            return true;
            }
        });
    }

    public void setStereoscopicField(StereoscopicField l, StereoscopicField r) {
        stescoLeft = l;
        stescoRight = r;
    }

    private void addText(String text) {
        StescoTextView textView = new StescoTextView(context);
        textView.setText(text);
        addView(textView);
        list.add(textView);
    }

    public void setSize(int size) {
        if (textView != null) {
            int dw = textView.getWidth(), dh = textView.getHeight();
            textView.setTextSize(size);
            dw -= textView.getWidth();
            dh -= textView.getHeight();

            RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
            lp.topMargin = textView.getTop()+dh/2;
            lp.leftMargin = textView.getLeft()+dw/2;
            inputfield.removeView(textView);
            inputfield.addView(textView, lp);
        }
    }
}


