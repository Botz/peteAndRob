package pete.and.rob.modules.videoList.di;

import dagger.Component;
import pete.and.rob.ActivityModule;
import pete.and.rob.common.application.PRAppComponent;
import pete.and.rob.PerActivity;
import pete.and.rob.modules.videoList.VideoLIstFragment;
import pete.and.rob.modules.videoList.VideoListInteractorInput;
import pete.and.rob.modules.videoList.VideoListPresenterInput;

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
