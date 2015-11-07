package pete.and.rob.videoList;

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
public class VideoListInteractor implements VideoListInteractorInput, VideoLIstInteractorOutput {
    private VideoListPresenterInput mPresenter;
    private PRRestAdapter mRestAdapter;

    public VideoListInteractor(PRRestAdapter restAdapter) {
        mRestAdapter = restAdapter;
    }

    @Override
    public void setInteractorOutput(VideoLIstPresenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void loadVideos(int page) {
        int limit = 10;
        int start = limit * (page - 1);
        Call<VideoList> videoListCall = mRestAdapter.getVideoList(start, limit);
        videoListCall.enqueue(new Callback<VideoList>() {
            @Override
            public void onResponse(Response<VideoList> response, Retrofit retrofit) {
                ArrayList<VideoListDisplayData> videosDisplaydata = new ArrayList<>();
                for (Video v:response.body().videos) {
                    videosDisplaydata.add(new VideoListDisplayData(v));
                }
                mPresenter.foundVideos(videosDisplaydata);
            }

            @Override
            public void onFailure(Throwable t) {
                Log.d("RETROFIT", t.getMessage());
            }
        });
    }
}
