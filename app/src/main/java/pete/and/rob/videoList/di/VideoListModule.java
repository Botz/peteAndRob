package pete.and.rob.videoList.di;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import pete.and.rob.common.PRRestAdapter;
import pete.and.rob.videoList.VideoLIstPresenter;
import pete.and.rob.videoList.VideoListInteractor;
import pete.and.rob.videoList.VideoListInteractorInput;
import pete.and.rob.videoList.VideoListPresenterInput;
import pete.and.rob.videoList.VideoListPresenterOutput;

/**
 * Created by Botz on 01.11.15.
 */
@Module
public class VideoListModule {

    private final VideoListPresenterOutput mOutput;

    public VideoListModule(VideoListPresenterOutput output) {
        mOutput = output;
    }

    @Provides
    public VideoListInteractorInput providesVideoListInteractor(PRRestAdapter restAdapter) {
        return new VideoListInteractor(restAdapter);
    }

    @Provides
    public VideoListPresenterInput providesVideoListPresenter(VideoListInteractorInput interactor) {
        VideoLIstPresenter presenter = new VideoLIstPresenter(mOutput, interactor);
        interactor.setInteractorOutput(presenter);
        return presenter;
    }

}
