package pete.and.rob.application;

import com.squareup.moshi.Moshi;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import pete.and.rob.common.PRRestAdapter;
import pete.and.rob.models.Video;
import pete.and.rob.models.VideoList;
import retrofit.MoshiConverterFactory;
import retrofit.Retrofit;

/**
 * Created by Botz on 30.10.15.
 */
@Module
public class PRAppModule {

    private final PRApp mApp;

    public PRAppModule(PRApp app) {
        mApp = app;
    }

    @Provides @Singleton public PRRestAdapter provideRestAdapter() {
        Moshi moshi = new Moshi.Builder().build();
        moshi.adapter(Video.class);
        moshi.adapter(VideoList.class);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.peteandrob.com/")
                .addConverterFactory(MoshiConverterFactory.create())
                .build();

        return retrofit.create(PRRestAdapter.class);
    }
}
