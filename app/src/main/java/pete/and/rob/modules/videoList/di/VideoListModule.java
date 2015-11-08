package pete.and.rob.modules.videoList.di;

import dagger.Module;
import dagger.Provides;
import pete.and.rob.common.PRRestAdapter;
import pete.and.rob.modules.videoList.VideoListPresenter;
import pete.and.rob.modules.videoList.VideoListInteractor;
import pete.and.rob.modules.videoList.VideoListInteractorInput;
import pete.and.rob.modules.videoList.VideoListPresenterInput;
import pete.and.rob.modules.videoList.VideoListPresenterOutput;

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
        VideoListPresenter presenter = new VideoListPresenter(mOutput, interactor);
        interactor.setInteractorOutput(presenter);
        return presenter;
    }

}
