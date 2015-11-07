package pete.and.rob.common;

import android.os.Bundle;
import android.widget.AbsListView;
import android.widget.ListView;

/**
 * Created by Botz on 06.11.15.
 */
public abstract class EndlessScrollListener implements AbsListView.OnScrollListener {
    /**
     * The minimum amount of items to have below your current scroll position
     * before mLoading more.
     */
    private static final int VISIBLE_THRESHOLD = 2;
    /**
     * Sets the starting page index
     */
    private static final int STARTING_PAGE_INDEX = 1;


    /**
     * The current offset index of data you have loaded
     */
    private int mCurrentPage;

    /**
     * The total number of items in the dataset after the last load
     */
    private int mPreviousTotalItemCount;

    /**
     * True if we are still waiting for the last set of data to load.
     */
    private boolean mLoading;

    /**
     * True if we are still before the list items has been restored.
     */
    private boolean mIsRestoringPreviousItems = false;

    public EndlessScrollListener() {
        mCurrentPage = STARTING_PAGE_INDEX;
        mPreviousTotalItemCount = 0;
        mLoading = true;
    }

    private static final String STATE_EXTRA_PREVIOUS_TOTAL_COUNT = "previousTotalItemCount";
    private static final String STATE_EXTRA_CURRENT_PAGE = "currentPage";

    public void restoreState(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mCurrentPage = savedInstanceState.getInt(STATE_EXTRA_CURRENT_PAGE, 1);
            mPreviousTotalItemCount = savedInstanceState.getInt(STATE_EXTRA_PREVIOUS_TOTAL_COUNT, 0);
            mIsRestoringPreviousItems = mPreviousTotalItemCount > 0;
        }
    }

    public void saveState(Bundle outState) {
        outState.putInt(STATE_EXTRA_CURRENT_PAGE, mCurrentPage);
        outState.putInt(STATE_EXTRA_PREVIOUS_TOTAL_COUNT, mPreviousTotalItemCount);
    }

    public void resetLoadingState() {
        mLoading = false;
    }

    // This happens many times a second during a scroll, so be wary of the code you place here.
    // We are given a few useful parameters to help us work out if we need to load some more data,
    // but first we check if we are waiting for the previous load to finish.
    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // Excluding any loading footer views
        if (view instanceof ListView) {
            totalItemCount -= ((ListView) view).getFooterViewsCount();
        }

        // Before the listview has restored its previous items
        // we should not change the restored page or the restored previous items count
        if (mIsRestoringPreviousItems) {
            if (totalItemCount == mPreviousTotalItemCount) {
                mIsRestoringPreviousItems = false;
                mLoading = false;
            }
            return;
        }

        // If the total item count is zero and the previous isn't, assume the
        // list is invalidated and should be reset back to initial state
        if (totalItemCount < mPreviousTotalItemCount) {
            mCurrentPage = STARTING_PAGE_INDEX;
            mPreviousTotalItemCount = totalItemCount;
            if (totalItemCount == 0) {
                this.mLoading = true;
            }
        }

        // If it’s still mLoading, we check to see if the dataset count has
        // changed, if so we conclude it has finished mLoading and update the current page
        // number and total item count.
        if (mLoading && (totalItemCount > mPreviousTotalItemCount)) {
            mLoading = false;
            mPreviousTotalItemCount = totalItemCount;
            mCurrentPage++;
        }

        // If it isn’t currently mLoading, we check to see if we have reached
        // the VISIBLE_THRESHOLD and need to reload more data.
        // If we do need to reload some more data, we execute onLoadMore to fetch the data.
        if (!mLoading && (totalItemCount - visibleItemCount) <= (firstVisibleItem + VISIBLE_THRESHOLD)) {
            onLoadMore(mCurrentPage, totalItemCount);
            mLoading = true;
        }
    }

    // Defines the process for actually mLoading more data based on page
    public abstract void onLoadMore(int page, int totalItemsCount);

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        // Don't take any action on changed
    }
}
