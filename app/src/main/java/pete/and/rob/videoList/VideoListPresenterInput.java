package pete.and.rob.videoList;

import android.widget.AbsListView;

import java.util.ArrayList;

import pete.and.rob.common.EndlessScrollListener;

/**
 * Created by Botz on 01.11.15.
 */
public interface VideoListPresenterInput {
    void loadVideos();
    void foundVideos(ArrayList<VideoListDisplayData> videosDisplaydata);

    EndlessScrollListener getEndlessScrollListener();
}
