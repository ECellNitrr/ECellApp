package com.nitrr.ecell.esummit.ecellapp.misc;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

public class MySnapHelper extends SnapHelper {

    @Override
    public void attachToRecyclerView(@Nullable RecyclerView recyclerView) throws IllegalStateException {
        super.attachToRecyclerView(recyclerView);
    }

    @Nullable
    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        if (layoutManager.canScrollHorizontally()) {
            out[0] = getDistance(layoutManager, targetView, OrientationHelper.createHorizontalHelper(layoutManager));
        } else {
            out[0] = 0;
        }

        if (layoutManager.canScrollVertically()) {
            out[1] = getDistance(layoutManager, targetView, OrientationHelper.createVerticalHelper(layoutManager));
        } else {
            out[1] = 0;
        }
        return out;
    }

    private int getDistance(RecyclerView.LayoutManager layoutManager, View targetView, OrientationHelper helper) {
        final int childStart = helper.getDecoratedStart(targetView);
        final int containerStart = helper.getStartAfterPadding();
        return childStart - containerStart;
    }

    @Nullable
    @Override
    public View findSnapView(RecyclerView.LayoutManager layoutManager) {
        return null;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int i, int i1) {
        return 0;
    }


}
