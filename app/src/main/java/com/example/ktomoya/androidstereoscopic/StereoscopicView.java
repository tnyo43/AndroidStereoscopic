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
import android.widget.EditText;
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
    private Button addBtn, reflectBtn;
    EditText newText;

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
        inputField.setStereoscopicView(this);

        sizeseek = (SeekBar)findViewById(R.id.textsize);
        levelseek = (SeekBar)findViewById(R.id.textsteroscopic);

        sizeseek.setMax(50);
        sizeseek.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int size = progress + 10;
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

        levelseek.setMax(10);
        levelseek.setOnSeekBarChangeListener(
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    inputField.setLevel(progress-5);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            }
        );

        newText = (EditText)findViewById(R.id.newtext);

        addBtn = (Button)findViewById(R.id.addbtn);
        addBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = newText.getText().toString();
                Log.d("new text", text);
                inputField.addText(text);
                newText.setText("");
            }
        });

        reflectBtn = (Button)findViewById(R.id.reflectbtn);
        reflectBtn.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                clear();

                List<StescoTextView> list = inputField.list;
                for (StescoTextView textView : list)
                    addText(textView.getText().toString(), textView.getLeft(), textView.getTop(), textView.size, textView.level);
            }
        });
    }

    public void setSeekbar(int size, int level) {
        sizeseek.setProgress(size-10);
        levelseek.setProgress(level+5);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void clear() {
        rightsf.removeAllViews();
        leftsf.removeAllViews();
    }

    public void addText(String text, int left, int top, int size, int level) {
        /**
         * level : -5 ~ 5 (0 is the nomal view)
         */
        rightsf.addText(context, text, left, top, size, level);
        leftsf.addText(context, text, left, top, size, level);
    }


}
