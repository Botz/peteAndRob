package pete.and.rob.common;

import pete.and.rob.models.VideoList;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * Created by Botz on 03.11.15.
 */
public interface PRRestAdapter {
    @GET("/rss/videos.php")
    Call<VideoList> getVideoList(@Query("start") int start, @Query("limit") int limit);
}
