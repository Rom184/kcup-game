package utils;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Transformation;
import android.widget.LinearLayout;

public class MyRotateLayout extends LinearLayout {
    public MyRotateLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public MyRotateLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyRotateLayout(Context context) {
        super(context);
        init();
    }

    private void init() {
        setStaticTransformationsEnabled(true);
    }

    @Override
    protected boolean getChildStaticTransformation(View child, Transformation t) {
        t.setTransformationType(Transformation.TYPE_MATRIX);
        Matrix m = t.getMatrix();
        m.reset();
        m.postRotate(180, child.getWidth() / 2.0f, child.getHeight() / 2.0f);
        return true;
    }
}
