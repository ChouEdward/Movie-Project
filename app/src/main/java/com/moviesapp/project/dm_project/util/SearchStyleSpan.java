package com.moviesapp.project.dm_project.util;

import android.os.Parcel;
import android.text.TextPaint;
import android.text.style.StyleSpan;

public class SearchStyleSpan extends StyleSpan {

    public SearchStyleSpan(int style) {
        super(style);
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setFakeBoldText(true);
        //FIXME 这里还可以做其他差异性设置（修改文字大小等）
        super.updateDrawState(ds);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        paint.setFakeBoldText(true);
        super.updateMeasureState(paint);
    }
}
