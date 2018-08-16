package id.co.flashcome.introandroidstudio.ui;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by didik on 20/05/16.
 * Item Decor
 */
public class SpacesItemDecoration extends RecyclerView.ItemDecoration {
    private int mItemOffset;

    public SpacesItemDecoration(int itemOffset) {
        mItemOffset = itemOffset;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
                               RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
    }
}
