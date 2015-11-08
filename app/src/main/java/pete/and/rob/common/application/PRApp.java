package pete.and.rob.common.application;

import android.app.Application;

/**
 * Created by Botz on 30.10.15.
 */
public class PRApp extends Application {

    private PRAppComponent component;

    @Override public void onCreate() {
        super.onCreate();

        component = DaggerPRAppComponent.builder()
                .pRAppModule(new PRAppModule(this))
                .build();
    }

    public PRAppComponent getComponent() {
        return component;
    }
}
