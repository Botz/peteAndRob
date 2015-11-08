package pete.and.rob.common;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

/**
 * Created by Botz on 08.11.15.
 */
public interface ViewStateCallbacks {

    void onViewSaveState(@NonNull Bundle savedState);

    void onViewDestroy();

    void onViewCreated(@Nullable Bundle initialExtras, @Nullable Bundle savedState);
}