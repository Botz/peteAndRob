package pete.and.rob;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Botz on 01.11.15.
 */
@Module
public class ActivityModule {
    private final Activity activity;


    public ActivityModule(Activity activity) {
        this.activity = activity;
    }


    /**
     * Expose the activity to dependents in the graph.
     */
    @Provides
    @PerActivity
    Activity activity() {
        return this.activity;
    }
}