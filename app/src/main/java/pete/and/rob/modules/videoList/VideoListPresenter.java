package pete.and.rob.modules.videoList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import pete.and.rob.common.EndlessScrollListener;
import pete.and.rob.models.Video;

/**
 * Created by Botz on 01.11.15.
 */
public class VideoListPresenter implements VideoLIstInteractorOutput, VideoListPresenterInput {
    private final VideoListPresenterOutput mOutput;
    private final VideoListInteractorInput mInteractor;

    public VideoListPresenter(VideoListPresenterOutput output, VideoListInteractorInput interactor) {
        mOutput = output;
        mInteractor = interactor;
    }

    @Override
    public void loadVideos() {
        mOutput.showProgressBar();
        mInteractor.loadVideos(1);
    }

    @Override
    public void foundVideos(List<Video> videos) {
        ArrayList<VideoListDisplayData> videosDisplaydata = new ArrayList<>();
        for (Video v:videos) {
            videosDisplaydata.add(new VideoListDisplayData(v));
        }

        mOutput.foundVideos(videosDisplaydata);
        mOutput.hideProgressBar();
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

    @Override
    public void onRefresh() {
        mOutput.clearVideoList();
        mInteractor.clearVideoList();
        mInteractor.loadVideos(1);
    }



    @Override
    public void onViewSaveState(@NonNull Bundle savedState) {
        mInteractor.onViewSaveState(savedState);
    }

    @Override
    public void onViewDestroy() {
        mInteractor.onViewDestroy();
    }

    @Override
    public void onViewCreated(@Nullable Bundle initialExtras, @Nullable Bundle savedState) {
        mInteractor.onViewCreated(initialExtras, savedState);
    }
}
