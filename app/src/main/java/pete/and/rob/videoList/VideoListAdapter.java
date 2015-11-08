package pete.and.rob.videoList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


import butterknife.Bind;
import butterknife.ButterKnife;
import pete.and.rob.R;

/**
 * Created by Botz on 01.11.15.
 */
public class VideoListAdapter extends ArrayAdapter<VideoListDisplayData> {
    public VideoListAdapter(Context context) {
        super(context, R.layout.li_video_list);
        setNotifyOnChange(true);
    }

    protected LayoutInflater getLayoutInflater() {
        return (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = getLayoutInflater().inflate(R.layout.li_video_list, parent, false);
            new VideoListItemViewHolder(convertView);
        }

        VideoListItemViewHolder vh = (VideoListItemViewHolder) convertView.getTag();
        VideoListDisplayData dd = getItem(position);
        vh.title.setText(dd.title);
        Picasso.with(getContext()).load(dd.videoImageUrl).into(vh.image);
        return convertView;
    }

    static class VideoListItemViewHolder {
        @Bind(R.id.videoListImage)
        ImageView image;
        @Bind(R.id.videoListTitle)
        TextView title;

        @Bind(R.id.videoListViewContainer)
        LinearLayout container;

        public VideoListItemViewHolder(View view) {
            ButterKnife.bind(this, view);
            view.setTag(this);
        }
    }
}
