package com.nitrr.ecell.esummit.ecellapp.misc;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SnapHelper;
import android.view.View;

public class MySnapHelper extends SnapHelper {

    @Nullable
    private OrientationHelper mHorizontalHelper;

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
        return this.findCenterView(layoutManager, this.getHorizontalHelper(layoutManager));
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velX, int velY) {
        int currentPos = layoutManager.getPosition(this.findSnapView(layoutManager));
        int max = layoutManager.getChildCount();
        int targetPos = currentPos;

        if(velX!=0) {
            if(currentPos == max - 1) {
                if(velX > 0) {
                    targetPos = max - 1;
                }
            }

            else if(currentPos == 0) {
                if(velX < 0) {
                    targetPos = 1;
                }
            }

            else {
                if(velX > 0) {
                    targetPos = currentPos - 1;
                }
                else if(velX < 0) {
                    targetPos = currentPos + 1;
                }

            }
        }

        return targetPos;
    }

    @Nullable
    private View findCenterView(RecyclerView.LayoutManager layoutManager, OrientationHelper helper) {
        int childCount = layoutManager.getChildCount();
        if (childCount == 0) {
            return null;
        } else {
            View closestChild = null;
            int center;
            if (layoutManager.getClipToPadding()) {
                center = helper.getStartAfterPadding() + helper.getTotalSpace() / 2;
            } else {
                center = helper.getEnd() / 2;
            }

            int absClosest = 2147483647;

            for(int i = 0; i < childCount; ++i) {
                View child = layoutManager.getChildAt(i);
                int childCenter = helper.getDecoratedStart(child) + helper.getDecoratedMeasurement(child) / 2;
                int absDistance = Math.abs(childCenter - center);
                if (absDistance < absClosest) {
                    absClosest = absDistance;
                    closestChild = child;
                }
            }
            return closestChild;
        }
    }

    @NonNull
    private OrientationHelper getHorizontalHelper(@NonNull RecyclerView.LayoutManager layoutManager) {
        if (this.mHorizontalHelper == null /*|| this.mHorizontalHelper.mLayoutManager != layoutManager*/) {
            this.mHorizontalHelper = OrientationHelper.createHorizontalHelper(layoutManager);
        }

        return this.mHorizontalHelper;
    }
    //The commented region in the () above showing an error. the error is not shown in LinearSnapHelper.java class where the
    //same method is there.
}
