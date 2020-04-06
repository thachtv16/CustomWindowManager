package com.tv.customwindowmanager.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;

import com.tv.customwindowmanager.R;

public class FullView extends RelativeLayout {
    public FullView(Context context) {
        super(context);
//        init();
    }

    public FullView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.layout_full_view, this);
    }
}
