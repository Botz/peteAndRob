package pete.and.rob.videoList;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;

import pete.and.rob.common.PRRestAdapter;
import pete.and.rob.models.Video;
import pete.and.rob.models.VideoList;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;


/**
 * Created by Botz on 01.11.15.
 */
public class VideoListInteractor implements VideoListInteractorInput, Callback<VideoList> {
    private static final String STATE_EXTRA_VIDEOS = "videoListExtra";
    private static final int VIDEO_LIMIT = 10;

    private VideoLIstInteractorOutput mPresenter;
    private PRRestAdapter mRestAdapter;
    private ArrayList<Video> mVideoList = new ArrayList<>();

    public VideoListInteractor(PRRestAdapter restAdapter) {
        mRestAdapter = restAdapter;
    }

    @Override
    public void setInteractorOutput(VideoListPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadVideos(int page) {
        if (page == 1 && mVideoList.size() > 0) {
            mPresenter.foundVideos(mVideoList);
            return;
        }

        Call<VideoList> videoListCall = mRestAdapter.getVideoList(VIDEO_LIMIT * (page - 1), VIDEO_LIMIT);
        videoListCall.enqueue(this);
    }

    @Override
    public void clearVideoList() {
        mVideoList.clear();
    }


    @Override
    public void onViewSaveState(@NonNull Bundle savedState) {
        savedState.putSerializable(STATE_EXTRA_VIDEOS, mVideoList);
    }

    @Override
    public void onViewDestroy() {

    }

    @Override
    public void onViewCreated(@Nullable Bundle initialExtras, @Nullable Bundle savedState) {
        if (savedState != null) {
            mVideoList = (ArrayList<Video>) savedState.getSerializable(STATE_EXTRA_VIDEOS);
        }
    }

    @Override
    public void onResponse(Response<VideoList> response, Retrofit retrofit) {
        mVideoList.addAll(response.body().videos);
        mPresenter.foundVideos(response.body().videos);
    }

    @Override
    public void onFailure(Throwable t) {

    }

}
