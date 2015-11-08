package pete.and.rob.modules.videoList;

import android.support.v4.widget.SwipeRefreshLayout;
import pete.and.rob.common.EndlessScrollListener;
import pete.and.rob.common.ViewStateCallbacks;

/**
 * Created by Botz on 01.11.15.
 */
public interface VideoListPresenterInput extends SwipeRefreshLayout.OnRefreshListener, ViewStateCallbacks {
    void loadVideos();

    EndlessScrollListener getEndlessScrollListener();
}
