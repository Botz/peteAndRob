package pete.and.rob.videoList;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import pete.and.rob.ActivityModule;
import pete.and.rob.application.PRApp;
import pete.and.rob.R;
import pete.and.rob.videoList.di.DaggerVideoListComponent;
import pete.and.rob.videoList.di.VideoListComponent;
import pete.and.rob.videoList.di.VideoListModule;

/**
 * Created by Botz on 01.11.15.
 */
public class VideoLIstFragment extends Fragment implements VideoListPresenterOutput{

    @Inject
    VideoListPresenterInput presenter;

    @Bind(R.id.videoListView) ListView videoListView;

    private VideoListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        VideoListComponent component = DaggerVideoListComponent.builder()
                .pRAppComponent(((PRApp)getActivity().getApplicationContext()).getComponent())
                .activityModule(new ActivityModule(getActivity()))
                .videoListModule(new VideoListModule(this)).build();
        component.inject(this);

        mAdapter = new VideoListAdapter(getContext());
        setupListView();

        presenter.loadVideos();
    }

    @Override public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void foundVideos(ArrayList<VideoListDisplayData> videosDisplaydata) {
        mAdapter.addAll(videosDisplaydata);
    }

    public void setupListView() {
        videoListView.setAdapter(mAdapter);
        videoListView.setOnScrollListener(presenter.getEndlessScrollListener());
    }
}
