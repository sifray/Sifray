package com.develop.p2s.sifray;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Rinov on 7/2/2016.
 */
public class FeedListRowHolder extends RecyclerView.ViewHolder {
    protected ImageView thumbnail;
    protected TextView title;

    public FeedListRowHolder(View view) {
        super(view);
        this.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        this.title = (TextView) view.findViewById(R.id.title);
    }

}
