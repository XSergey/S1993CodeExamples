package com.vermilionsynergy.magadiskileton;



import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

public class MagadiBoldTextView extends TextView {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MagadiBoldTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }
    public MagadiBoldTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs);
    }
    public MagadiBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }
    public MagadiBoldTextView(Context context) {
        super(context);
        init(null);
    }
    private void init(AttributeSet attrs) {
        if (attrs != null) {
            try {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/segoeuib.ttf");
                setTypeface(myTypeface);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}