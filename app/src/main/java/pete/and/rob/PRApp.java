package pete.and.rob;

import android.app.Application;
import android.content.Context;

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


    public static PRAppComponent getComponent(Context context) {
        return ((PRApp) context.getApplicationContext()).component;
    }
}
