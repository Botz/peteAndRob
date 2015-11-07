package pete.and.rob.videoList;

/**
 * Created by Botz on 01.11.15.
 */
public interface VideoListInteractorInput {
    void setInteractorOutput(VideoLIstPresenter presenter);

    void loadVideos(int page);
}
