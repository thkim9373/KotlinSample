package com.hoony.kotlinsample.custom_view.layout_contain_view_pager_example;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

abstract public class AbsItemView extends FrameLayout {
    public AbsItemView(@NonNull Context context) {
        super(context);
        init();
    }

    public AbsItemView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    abstract int getLayoutId();

    void init() {
        if(getLayoutId() > 0) {
            inflate(getContext(), getLayoutId(), this);
        }
    }
}
