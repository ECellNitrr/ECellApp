package com.nitrr.ecell.esummit.ecellapp.misc;

import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class Event_GridRV_ItemDecoration extends RecyclerView.ItemDecoration {
    private int mItemOffset;

    public Event_GridRV_ItemDecoration(int itemOffset) {

        mItemOffset = itemOffset;

    }


    public Event_GridRV_ItemDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {

        this(context.getResources().getDimensionPixelSize(itemOffsetId));

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {

        outRect.left = 0;
        outRect.right = 0;
        outRect.bottom = 0;
        outRect.top = 0;

        /*super.getItemOffsets(outRect, view, parent, state);

        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);*/

    }

}
