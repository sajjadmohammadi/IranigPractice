package ir.s_mohammadi.iranigpractice.CustomViews;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

import ir.s_mohammadi.iranigpractice.Utilities.G;

/**
 * Created by john on 11/9/2016.
 */
public class TextViewLight extends TextView {
    public TextViewLight(Context context) {
        super(context);
        initfont();
    }

    public TextViewLight(Context context, AttributeSet attrs) {
        super(context, attrs);
        initfont();
    }

    public TextViewLight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initfont();
    }

    private void initfont() {
        setTypeface(G.typeFace_Light);
    }
}
