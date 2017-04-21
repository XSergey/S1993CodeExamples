package com.vermilionsynergy.magadiskileton;



import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Button;

public class MagadiButton extends Button {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MagadiButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public MagadiButton(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }
    public MagadiButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public MagadiButton(Context context) {
        super(context);
        init(null);
    }
    private void init(AttributeSet attrs) {
        try {
            Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/segoeui.ttf");
            setTypeface(myTypeface);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}