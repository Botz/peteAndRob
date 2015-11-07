package pete.and.rob.videoList;

import java.util.ArrayList;

/**
 * Created by Botz on 01.11.15.
 */
public interface VideoListPresenterOutput {
    void foundVideos(ArrayList<VideoListDisplayData> videosDisplaydata);
}
