package pete.and.rob.videoList.di;

import dagger.Component;
import pete.and.rob.ActivityModule;
import pete.and.rob.application.PRAppComponent;
import pete.and.rob.PerActivity;
import pete.and.rob.videoList.VideoLIstFragment;
import pete.and.rob.videoList.VideoListInteractorInput;
import pete.and.rob.videoList.VideoListPresenterInput;

/**
 * Created by Botz on 01.11.15.
 */

@PerActivity
@Component(
        dependencies = PRAppComponent.class,
        modules = {
                VideoListModule.class,
                ActivityModule.class
        }
)
public interface VideoListComponent {
        void inject(VideoLIstFragment fragment);

        VideoListInteractorInput interactor();
        VideoListPresenterInput presenter();
}
