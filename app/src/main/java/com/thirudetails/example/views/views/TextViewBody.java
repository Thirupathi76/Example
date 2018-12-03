package com.thirudetails.example.views.views;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class TextViewBody extends TextView {

    public TextViewBody(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public TextViewBody(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public TextViewBody(Context context) {
        super(context);
        init();
    }

    private void init() {
        Typeface tf = Typeface.createFromAsset(getContext().getAssets(),
                "Painter_PERSONAL_USE_ONLY.ttf");
        setTypeface(tf);
    }

}
