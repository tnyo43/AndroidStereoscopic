package com.example.ktomoya.androidstereoscopic;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;

        addText("ほげ", 10, 0, 10, -5);
        addText("ふー", 20, 50, 20, 0);
        addText("ばー", 20, 100, 30, 5);
    }

    public void addText(String text, int left, int top, int size, int level) {
        /**
         * level : -5 ~ 5 (0 is the nomal view)
         */
        ((StereoscopicView)findViewById(R.id.stereoscopicview)).addText(this, text, left, top, size, level);
    }
}
