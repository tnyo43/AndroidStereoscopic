package com.example.ktomoya.androidstereoscopic;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.LinkedList;
import java.util.List;

public class StereoscopicView extends LinearLayout{

    StereoscopicField rightsf, leftsf;
    InputField inputField;
    SeekBar sizeseek, levelseek;
    private Context context;
    private Button reflectBtn;

    public StereoscopicView(Context context) {
        this(context, null);
    }

    public StereoscopicView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StereoscopicView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;

        View layout = LayoutInflater.from(context).inflate(R.layout.stereoscopic_view, this);

        rightsf = (StereoscopicField)findViewById(R.id.ss_right);
        rightsf.isLeft = false;
        leftsf = (StereoscopicField)findViewById(R.id.ss_left);
        leftsf.isLeft = true;
        inputField = (InputField) findViewById(R.id.inputfield);
        inputField.setMinimumWidth(rightsf.getWidth());
        inputField.setStereoscopicField(leftsf, rightsf);

        sizeseek = (SeekBar)findViewById(R.id.textsize);
        levelseek = (SeekBar)findViewById(R.id.textsteroscopic);

        sizeseek.setMax(100);
        sizeseek.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int size = progress/2 + 10;
                    inputField.setSize(size);
                    Log.d("SEEK", String.valueOf(size));
                    //reflect();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {
                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {
                }

            });

        reflectBtn = (Button)findViewById(R.id.reflectbtn);
        reflectBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                List<StescoTextView> list = inputField.list;
                for (StescoTextView textView : list) {
                    Log.d("list", textView.getText().toString());
                    Log.d("left", String.valueOf(textView.getLeft()));
                    Log.d("top", String.valueOf(textView.getTop()));
                    Log.d("size", String.valueOf(textView.getTextSize()));
                    Log.d("level", String.valueOf(textView.level));

                    addText(textView.getText().toString(), textView.getLeft(), textView.getTop(), (int)textView.getTextSize(), (int)(Math.random()*4)-2);
                }
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void addText(String text, int left, int top, int size, int level) {
        /**
         * level : -5 ~ 5 (0 is the nomal view)
         */
        rightsf.addText(context, text, left, top, size, level);
        leftsf.addText(context, text, left, top, size, level);
    }


}
