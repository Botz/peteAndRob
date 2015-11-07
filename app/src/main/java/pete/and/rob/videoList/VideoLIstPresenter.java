package pete.and.rob.videoList;

import java.util.ArrayList;

import pete.and.rob.common.EndlessScrollListener;

/**
 * Created by Botz on 01.11.15.
 */
public class VideoLIstPresenter implements VideoListPresenterOutput, VideoListPresenterInput {
    private final VideoListPresenterOutput mOutput;
    private final VideoListInteractorInput mInteractor;

    public VideoLIstPresenter(VideoListPresenterOutput output, VideoListInteractorInput interactor) {
        mOutput = output;
        mInteractor = interactor;
    }

    @Override
    public void loadVideos() {
        mInteractor.loadVideos(1);
    }

    @Override
    public void foundVideos(ArrayList<VideoListDisplayData> videosDisplaydata) {
        mOutput.foundVideos(videosDisplaydata);
    }

    @Override
    public EndlessScrollListener getEndlessScrollListener() {
        return mEndlessScrollListener;
    }

    private EndlessScrollListener mEndlessScrollListener = new EndlessScrollListener() {
        @Override
        public void onLoadMore(int page, int totalItemsCount) {
            mInteractor.loadVideos(page);
        }
    };
}
