package pete.and.rob;

import android.app.Activity;

import dagger.Component;
import pete.and.rob.application.PRAppComponent;

/**
 * Created by Botz on 01.11.15.
 */
@PerActivity
@Component(dependencies = PRAppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity activity();
}
