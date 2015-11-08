package pete.and.rob.videoList;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

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

    @Bind(R.id.videoListView)
    ListView videoListView;
    @Bind(R.id.videoListSwipeContainer)
    SwipeRefreshLayout refreshLayout;
    @Bind(R.id.videoListProgressBar)
    ProgressBar progressBar;

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
        setupComponent();
        setupListView();
        setupRefresh();

        presenter.onViewCreated(null, savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.loadVideos();
    }

    @Override
    public void onDestroyView() {
        ButterKnife.unbind(this);
        presenter.onViewDestroy();
        super.onDestroyView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        presenter.onViewSaveState(outState);
    }

    @Override
    public void foundVideos(ArrayList<VideoListDisplayData> videosDisplaydata) {
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }
        mAdapter.addAll(videosDisplaydata);
    }

    @Override
    public void clearVideoList() {
        mAdapter.clear();
    }

    @Override
    public void showProgressBar() {
        videoListView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        videoListView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    private void setupListView() {
        mAdapter = new VideoListAdapter(getContext());
        videoListView.setAdapter(mAdapter);
        videoListView.setOnScrollListener(presenter.getEndlessScrollListener());
    }

    private void setupRefresh() {
        refreshLayout.setOnRefreshListener(presenter);
    }

    private void setupComponent() {
        VideoListComponent component = DaggerVideoListComponent.builder()
                .pRAppComponent(((PRApp)getActivity().getApplicationContext()).getComponent())
                .activityModule(new ActivityModule(getActivity()))
                .videoListModule(new VideoListModule(this)).build();
        component.inject(this);
    }
}
