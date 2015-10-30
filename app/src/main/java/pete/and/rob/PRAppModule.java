package pete.and.rob;

import dagger.Module;

/**
 * Created by Botz on 30.10.15.
 */
@Module
public class PRAppModule {

    private final PRApp mApp;

    public PRAppModule(PRApp app) {
        mApp = app;
    }
}
