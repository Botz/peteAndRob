package pete.and.rob.common.application;

import javax.inject.Singleton;

import dagger.Component;
import pete.and.rob.MainActivity;
import pete.and.rob.common.PRRestAdapter;

/**
 * Created by Botz on 30.10.15.
 */
//@PerApp
@Singleton
@Component(
        modules = {
                PRAppModule.class
        }
)
public interface PRAppComponent {
        void inject(MainActivity mainActivity);

        PRRestAdapter restAdapter();
}
