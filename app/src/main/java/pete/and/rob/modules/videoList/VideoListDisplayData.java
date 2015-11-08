package pete.and.rob.modules.videoList;

import pete.and.rob.models.Video;

/**
 * Created by Botz on 06.11.15.
 */
public class VideoListDisplayData {
    public String videoImageUrl;
    public String title;

    public VideoListDisplayData(Video video) {
        videoImageUrl = video.image550;
        title = video.title;
    }
}
