package pete.and.rob.videoList;

import pete.and.rob.common.ViewStateCallbacks;

/**
 * Created by Botz on 01.11.15.
 */
public interface VideoListInteractorInput extends ViewStateCallbacks {
    void setInteractorOutput(VideoListPresenter presenter);

    void loadVideos(int page);

    void clearVideoList();
}
