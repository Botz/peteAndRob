package pete.and.rob;

import dagger.Component;

/**
 * Created by Botz on 30.10.15.
 */
//@PerApp
@Component(
        modules = {
                PRAppModule.class
        }
)
public interface PRAppComponent {
        void inject(MainActivity mainActivity);
}
